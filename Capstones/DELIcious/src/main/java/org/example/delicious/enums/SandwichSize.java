package org.example.delicious.enums;

import java.math.BigDecimal;

public enum SandwichSize {
    FOUR_INCH, EIGHT_INCH, TWELVE_INCH;

    public BigDecimal getPrice() {
        return switch (this) {
            case FOUR_INCH -> BigDecimal.valueOf(5.5);
            case EIGHT_INCH -> BigDecimal.valueOf(7);
            case TWELVE_INCH -> BigDecimal.valueOf(8.5);
        };
    }
}
