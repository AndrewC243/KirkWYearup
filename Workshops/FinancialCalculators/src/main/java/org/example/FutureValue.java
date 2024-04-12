package org.example;

public class FutureValue {
    private int deposit;
    private double rate;
    private int years;

    public FutureValue(int deposit, double rate, int years) {
        this.deposit = deposit;
        this.rate = rate;
        this.years = years;
    }

    public double futureValue() {
        double dailyRate = rate / 100 / 365;
        return deposit * Math.pow(1 + dailyRate, years * 365);
    }

    public double interestEarend() {
        return futureValue() - deposit;
    }

    public int getDeposit() {
        return deposit;
    }
    public double getRate() {
        return rate;
    }
    public int getYears() {
        return years;
    }
}
