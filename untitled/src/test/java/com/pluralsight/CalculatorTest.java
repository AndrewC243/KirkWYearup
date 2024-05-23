package com.pluralsight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private Calculator calc;

    @BeforeEach
    public void setUp() {
        calc = new Calculator();
    }

    @Test
    public void addOneAndTwoReturnThree() {
//        Act
        var result = calc.add(1, 2);
//        Asset
        assertEquals(3, result);
    }

    @Test
    public void subtractOneFromTwoReturnOne() {
        var result = calc.subtract(2, 1);
        assertEquals(1, result);
    }

    @Test
    public void divideByZeroThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> calc.divide(0, 0));
    }

    @ParameterizedTest
    @CsvSource({"1,2,3", "10,5,15", "20,30,50"})
    public void testAdd(double a, double b, double c) {
        assertEquals(c, calc.add(a, b));
    }
}