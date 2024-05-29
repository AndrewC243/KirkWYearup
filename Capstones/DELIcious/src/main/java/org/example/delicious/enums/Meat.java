package org.example.delicious.enums;

import java.math.BigDecimal;

public enum Meat {
    STEAK, HAM, SALAMI, ROAST_BEEF, CHICKEN, BACON;

    public static BigDecimal getPrice(SandwichSize size) {
        return switch (size) {
            case FOUR_INCH -> BigDecimal.ONE;
            case EIGHT_INCH -> BigDecimal.valueOf(2);
            case TWELVE_INCH -> BigDecimal.valueOf(3);
        };
    }
}
