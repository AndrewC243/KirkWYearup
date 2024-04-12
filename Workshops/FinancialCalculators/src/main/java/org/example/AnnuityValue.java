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
        // P = PMT * (1 - (1 / (1 + r)^n)) / r
//        Present Value (PV) of Annuity = (A ÷ r) (1 – (1 ÷ (1 + r) ^ t))
//        C * (1 - (1 + i)^-n) / i

//        return monthlyPayout
//                * (1 - (1 / Math.pow(1 + (expectedInterest / 100), 12 * years))) / (expectedInterest / 100);
//        return (monthlyPayout / r) * (1 - (1 / Math.pow(1 + r, years * 12)));
        return monthlyPayout * (1 - Math.pow(1 + r / 12, years * -12)) / (r / 12);
    }

    public double getMonthlyPayout() {
        return monthlyPayout;
    }
    public void setMonthlyPayout(double monthlyPayout) {
        this.monthlyPayout = monthlyPayout;
    }
    public double getExpectedInterest() {
        return expectedInterest;
    }
}
