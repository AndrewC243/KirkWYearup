package org.example.delicious;

import org.example.delicious.enums.Cheese;
import org.example.delicious.enums.Meat;
import org.example.delicious.enums.SandwichSize;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Order {
    private List<Meal> meals;
    private static final BigDecimal chipPrice = BigDecimal.valueOf(1.5);
//    The prices of extra premium toppings are done on a proportional basis to their original price.
//    The BigDecimals below represent the percentage of the original price that they cost.
    private static final Map<Class<? extends Enum<?>>, BigDecimal> extraPremToppingModifier = Map.of(
            Meat.class, BigDecimal.valueOf(0.5),
            Cheese.class, BigDecimal.valueOf(0.6)
    );

    public void addMeal(Meal meal) {
        meals.add(meal);
    }

    public void removeMeal(int i) {
        meals.remove(i);
    }

    public boolean isEmpty() { return meals.isEmpty(); }

    public Order() {
        this.meals = new ArrayList<>();
    }

    public BigDecimal getPrice() {
        BigDecimal total = BigDecimal.ZERO;
        for (Meal meal : meals) {
            total = total.add(getPrice(meal));
        }
        return total;
    }

    public static BigDecimal getPrice(Meal meal) {
        SandwichSize size = meal.getSandwichSize();
        BigDecimal total = meal.getSandwichSize().getPrice()
                .add(Meat.getPrice(size))
                .add(Cheese.getPrice(size))
                .add(meal.getDrinkSize().getPrice());
        if (meal.getMeats().size() > 1)
            total = total.add(extraPremToppingModifier.get(Meat.class)
                    .multiply(Meat.getPrice(size))
                    .multiply(BigDecimal.valueOf(meal.getMeats().size() - 1)));
        if (meal.getCheeses().size() > 1)
            total = total.add(extraPremToppingModifier.get(Cheese.class)
                    .multiply(Cheese.getPrice(size))
                    .multiply(BigDecimal.valueOf(meal.getMeats().size() - 1)));
        if (meal.getChips())
            total = total.add(chipPrice);
        return total;
    }

    public void saveOrder() {
        String filename = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd-hhmmss"));
        try (BufferedWriter bf = new BufferedWriter(new FileWriter( "src/main/resources/" + filename + ".txt"))) {
            int i = 1;
            for (Meal meal : meals) {
                bf.write(i++ + ":\n");
                bf.write(meal.toString());
                bf.write("\n\nTotal price: " + getPrice().toString());
                bf.newLine();
                bf.newLine();
            }
        } catch (IOException ignored) {}
    }
}
