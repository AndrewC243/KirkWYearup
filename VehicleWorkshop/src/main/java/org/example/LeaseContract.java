package org.example;

public class LeaseContract extends Contract {
    public LeaseContract(String date, String customerName, String custoemrEmail, Vehicle vehicleSold) {
        super(date, customerName, custoemrEmail, vehicleSold);
    }

    @Override
    public double getTotalPrice() {
        return getExpectedEndingValue() + getLeaseFee();
    }

    @Override
    public double getMonthlyPayment() {
        return (getExpectedEndingValue() / 36) + ((0.04 / 24) * (getVehicleSold().getPrice() + getExpectedEndingValue()));
    }

    @Override
    public String toString() {
        return "LEASE|"
                + getDate() + "|"
                + getCustomerName() + "|"
                + getCustoemrEmail() + "|"
                + getVehicleSold().getVin() + "|"
                + getVehicleSold().getYear() + "|"
                + getVehicleSold().getMake() + "|"
                + getVehicleSold().getModel() + "|"
                + getVehicleSold().getVehicleType() + "|"
                + getVehicleSold().getColor() + "|"
                + getVehicleSold().getOdometer() + "|"
                + getVehicleSold().getPrice() + "|"
                + getExpectedEndingValue() + "|"
                + getLeaseFee() + "|"
                + getTotalPrice() + "|"
                + getMonthlyPayment() + "|";
    }

    public double getExpectedEndingValue() { return getVehicleSold().getPrice() / 2; }
    public double getLeaseFee() { return getVehicleSold().getPrice() * .07; }
}