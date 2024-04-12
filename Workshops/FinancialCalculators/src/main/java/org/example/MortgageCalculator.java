package org.example;

public class MortgageCalculator {
    private double principal;
    private double rate;
    private int years;

    public MortgageCalculator(double principal, double rate, int years) {
        this.principal = principal;
        this.rate = rate; // This program will assume that rate is given as it would be written/spoken
        this.years = years;
    }

    public double monthlyPayment() {
//        M = P [ I(1 + I)^N ] / [ (1 + I)^N − 1]
        double monthlyRate = rate / 100 / 12; // Monthly rate expressed as a decimal number
        return principal * monthlyRate * Math.pow(1 + monthlyRate, 12 * years)
                    / (Math.pow(1 + monthlyRate, 12 * years) - 1);
    }

    public double totalInterest() {
        return (monthlyPayment() * 12 * years) - principal;
    }

    public double getPrincipal() {
        return principal;
    }
    public double getRate() {
        return rate;
    }
    public int getYears() {
        return years;
    }
}
