package org.example;

import java.util.Date;

public class TransactionFilter {
    private Date startDate;
    private Date endDate;
    private String description;
    private String vendor;
    private double amountMin;
    private double amountMax;

    public TransactionFilter() {
        this.startDate = new Date(Long.MIN_VALUE);
        this.endDate = new Date(Long.MAX_VALUE);
        this.description = "";
        this.vendor = "";
        this.amountMin = -Double.MAX_VALUE;
        this.amountMax = Double.MAX_VALUE;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmountMin() {
        return amountMin;
    }

    public void setAmountMin(double amountMin) {
        this.amountMin = amountMin;
    }

    public double getAmountMax() {
        return amountMax;
    }

    public void setAmountMax(double amountMax) {
        this.amountMax = amountMax;
    }
}
