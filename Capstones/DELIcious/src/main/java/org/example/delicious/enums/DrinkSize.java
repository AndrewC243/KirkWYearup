package org.example.delicious.enums;

import java.math.BigDecimal;

public enum DrinkSize {
    NONE, SMALL, MEDIUM, LARGE;

    public BigDecimal getPrice() {
        return switch (this) {
            case NONE -> BigDecimal.ZERO;
            case SMALL -> BigDecimal.valueOf(2);
            case MEDIUM -> BigDecimal.valueOf(2.5);
            case LARGE -> BigDecimal.valueOf(3);
        };
    }
}
