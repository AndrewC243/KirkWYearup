package org.example;

public class AnnuityValue {
    private double monthlyPayout;
    private double expectedInterest;
    private int years;

    public AnnuityValue(double monthlyPayout, double expectedInterest, int years) {
        this.monthlyPayout = monthlyPayout;
        this.expectedInterest = expectedInterest;
        this.years = years;
    }

    public double presentValue() {
        double r = expectedInterest / 100;
//        C * (1 - (1 + i)^-n) / i
        return monthlyPayout * (1 - Math.pow(1 + r / 12, years * -12)) / (r / 12);
    }

    public double getMonthlyPayout() {
        return monthlyPayout;
    }
    public double getExpectedInterest() {
        return expectedInterest;
    }
    public int getYears() {
        return years;
    }
}
