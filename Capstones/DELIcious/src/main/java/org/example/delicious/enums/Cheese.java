package org.example.delicious.enums;

import java.math.BigDecimal;

public enum Cheese {
    AMERICAN, PROVOLONE, CHEDDAR, SWISS;

    public static BigDecimal getPrice(SandwichSize size) {
        return switch (size) {
            case FOUR_INCH -> BigDecimal.valueOf(0.75);
            case EIGHT_INCH -> BigDecimal.valueOf(1.5);
            case TWELVE_INCH -> BigDecimal.valueOf(2.25);
        };
    }
}
