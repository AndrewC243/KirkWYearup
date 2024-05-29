package org.example.delicious;

import java.math.BigDecimal;

public class MealOrder {
    private Meal meal;

    public MealOrder(Meal meal) {
        this.meal = meal;
    }

    public BigDecimal getPrice() {
        return BigDecimal.ZERO;
    }
}
