package org.example.delicious;

import org.example.delicious.enums.SandwichSize;

import java.math.BigDecimal;
import java.util.Map;

public class MealOrder {
    private Meal meal;

    public MealOrder(Meal meal) {
        this.meal = meal;
    }

    public BigDecimal getPrice() {
        return BigDecimal.ZERO;
    }
}
