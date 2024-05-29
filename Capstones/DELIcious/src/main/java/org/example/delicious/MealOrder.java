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
import java.util.Map;

public class MealOrder {
    private Meal meal;
    private static final BigDecimal chipPrice = BigDecimal.valueOf(1.5);
//    The prices of extra premium toppings are done on a proportional basis to their original price.
//    The BigDecimals below represent the percentage of the original price that they cost.
    private static final Map<Class<? extends Enum<?>>, BigDecimal> extraPremToppingModifier = Map.of(
            Meat.class, BigDecimal.valueOf(0.5),
            Cheese.class, BigDecimal.valueOf(0.6)
    );

    public MealOrder(Meal meal) {
        this.meal = meal;
    }

    public BigDecimal getPrice() {
        SandwichSize size = meal.getSandwichSize();
        BigDecimal total = meal.getSandwichSize().getPrice()
                .add(Meat.getPrice(size))
                .add(Cheese.getPrice(size))
                .add(meal.getDrinkSize().getPrice());
        if (meal.getMeats().size() > 1)
            total = total.add(extraPremToppingModifier.get(Meat.class).multiply(BigDecimal.valueOf(meal.getMeats().size() - 1)));
        if (meal.getCheeses().size() > 1)
            total = total.add(extraPremToppingModifier.get(Cheese.class).multiply(BigDecimal.valueOf(meal.getMeats().size() - 1)));
        if (meal.getChips())
            total = total.add(chipPrice);
        return total;
    }

    public void saveOrder() {
        String filename = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd-hhmmss"));
        try (BufferedWriter bf = new BufferedWriter(new FileWriter( "src/main/resources/" + filename + ".txt"))) {
            bf.write(meal.toString());
            bf.write("\n\nTotal price: " + getPrice().toString());
        } catch (IOException ignored) {}
    }
}
