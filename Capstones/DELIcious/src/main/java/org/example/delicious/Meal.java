package org.example.delicious;

import org.example.delicious.enums.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Meal {
    public static final Map<String, MealBuilder> SIGNATURE_SANDWICHES = Map.of(
            "BLT", new MealBuilder()
                    .sandwichSize(SandwichSize.EIGHT_INCH)
                    .bread(Bread.WHITE)
                    .addMeat(Meat.BACON)
                    .addCheese(Cheese.CHEDDAR)
                    .addTopping(RegTopping.LETTUCE)
                    .addTopping(RegTopping.TOMATO)
                    .addSauce(Sauce.RANCH)
                    .toasted(true),
            "Philly Cheese Steak", new MealBuilder()
                    .sandwichSize(SandwichSize.EIGHT_INCH)
                    .bread(Bread.WHITE)
                    .addMeat(Meat.STEAK)
                    .addCheese(Cheese.AMERICAN)
                    .addTopping(RegTopping.PEPPER)
                    .addSauce(Sauce.MAYO)
                    .toasted(true)
    );

    private SandwichSize sandwichSize;
    private Bread bread;
    private List<Meat> meats;
    private List<Cheese> cheeses;
    private List<Sauce> sauces;
    private List<RegTopping> toppings;
    private DrinkSize drinkSize;
    private boolean toasted;
    private boolean sauceOnSide;
    private boolean auJus;
    private boolean chips;

    private Meal(MealBuilder builder) {
        this.sandwichSize = builder.sandwichSize;
        this.bread = builder.bread;
        this.meats = builder.meats;
        this.cheeses = builder.cheeses;
        this.sauces = builder.sauces;
        this.toppings = builder.toppings;
        this.drinkSize = builder.drinkSize;
        this.toasted = builder.toasted;
        this.sauceOnSide = builder.sauceOnSide;
        this.auJus = builder.auJus;
        this.chips = builder.chips;
    }

    public SandwichSize getSandwichSize() {
        return sandwichSize;
    }

    public Bread getBread() {
        return bread;
    }
    public List<Meat> getMeats() {
        return meats;
    }
    public List<Cheese> getCheeses() {
        return cheeses;
    }
    public List<Sauce> getSauces() {
        return sauces;
    }
    public List<RegTopping> getToppings() {
        return toppings;
    }
    public DrinkSize getDrinkSize() {
        return drinkSize;
    }
    public boolean isToasted() {
        return toasted;
    }
    public boolean sauceOnSide() {
        return sauceOnSide;
    }
    public boolean auJus() {
        return auJus;
    }
    public boolean getChips() { return chips; }

//    implements cloneable in order to have signature sandwich templates
    static class MealBuilder implements Cloneable {
        private SandwichSize sandwichSize;
        private Bread bread;
        private List<Meat> meats;
        private List<Cheese> cheeses;
        private List<Sauce> sauces;
        private List<RegTopping> toppings;
        private DrinkSize drinkSize;
        private boolean toasted;
        private boolean sauceOnSide;
        private boolean auJus;
        private boolean chips;

        public MealBuilder () {
            meats = new ArrayList<>();
            cheeses = new ArrayList<>();
            sauces = new ArrayList<>();
            toppings = new ArrayList<>();
            drinkSize = DrinkSize.NONE;
        }

        public MealBuilder chips(boolean chips) {
            this.chips = chips;
            return this;
        }

        public MealBuilder toasted(boolean toasted) {
            this.toasted = toasted;
            return this;
        }

        public MealBuilder sauceOnSide(boolean sauceOnSide) {
            this.sauceOnSide = sauceOnSide;
            return this;
        }

        public MealBuilder auJus(boolean auJus) {
            this.auJus = auJus;
            return this;
        }

        public MealBuilder sandwichSize(SandwichSize sandwichSize) {
            this.sandwichSize = sandwichSize;
            return this;
        }

        public MealBuilder bread(Bread bread) {
            this.bread = bread;
            return this;
        }

        public MealBuilder addMeat(Meat meat) {
            meats.add(meat);
            return this;
        }

        public MealBuilder addCheese(Cheese cheese) {
            cheeses.add(cheese);
            return this;
        }

        public MealBuilder addSauce(Sauce sauce) {
            sauces.add(sauce);
            return this;
        }

        public MealBuilder addTopping(RegTopping topping) {
            toppings.add(topping);
            return this;
        }

        public MealBuilder drinkSize(DrinkSize drinkSize) {
            this.drinkSize = drinkSize;
            return this;
        }

        public Meal build() {
            return new Meal(this);
        }

        @Override
        public MealBuilder clone() {
            try {
                MealBuilder clone = (MealBuilder) super.clone();
                clone.meats = new ArrayList<>(meats);
                clone.cheeses = new ArrayList<>(cheeses);
                clone.sauces = new ArrayList<>(sauces);
                clone.toppings = new ArrayList<>(toppings);
                return clone;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }
}
