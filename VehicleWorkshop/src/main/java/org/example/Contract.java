package org.example;

public abstract class Contract {
    private String date;
    private String customerName;
    private String custoemrEmail;
    private Vehicle vehicleSold;

    public Contract(String date, String customerName, String custoemrEmail, Vehicle vehicleSold) {
        this.date = date;
        this.customerName = customerName;
        this.custoemrEmail = custoemrEmail;
        this.vehicleSold = vehicleSold;
    }

    public abstract double getTotalPrice();
    public abstract double getMonthlyPayment();

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustoemrEmail() {
        return custoemrEmail;
    }

    public void setCustoemrEmail(String custoemrEmail) {
        this.custoemrEmail = custoemrEmail;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }
}
