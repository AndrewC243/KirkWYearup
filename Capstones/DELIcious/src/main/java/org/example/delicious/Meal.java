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
    }

    private void setSandwichSize(SandwichSize sandwichSize) {
        this.sandwichSize = sandwichSize;
    }
    private void setBread(Bread bread) {
        this.bread = bread;
    }
    private void setMeats(List<Meat> meats) {
        this.meats = meats;
    }
    private void setCheeses(List<Cheese> cheeses) {
        this.cheeses = cheeses;
    }
    private void setSauces(List<Sauce> sauces) {
        this.sauces = sauces;
    }
    private void setToppings(List<RegTopping> toppings) {
        this.toppings = toppings;
    }
    private void setDrinkSize(DrinkSize drinkSize) {
        this.drinkSize = drinkSize;
    }
    private void setToasted(boolean toasted) { this.toasted = toasted; }
    private void setSauceOnSide(boolean sauceOnSide) { this.sauceOnSide = sauceOnSide; }
    private void setAuJus(boolean auJus) { this.auJus = auJus; }

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

        public MealBuilder () {
            meats = new ArrayList<>();
            cheeses = new ArrayList<>();
            sauces = new ArrayList<>();
            toppings = new ArrayList<>();
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
                clone.meats = List.copyOf(meats);
                clone.cheeses = List.copyOf(cheeses);
                clone.sauces = List.copyOf(sauces);
                clone.toppings = List.copyOf(toppings);
                return clone;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }
}
