package org.example;

import java.util.Date;
import java.util.List;

public class Receipt {
    private Date date;
    private List<Product> items;
    private double total;
    private double amountPaid;
    private double changeGiven;

    public Receipt(Date date, List<Product> items, double total, double amountPaid) {
        this.date = date;
        this.items = items;
        this.total = total;
        this.amountPaid = amountPaid;
        if (amountPaid > total)
            changeGiven = amountPaid - total;
        else changeGiven = 0;
    }

    public Receipt(List<Product> items, double total, double amountPaid) {
        this.date = new Date();
        this.items = items;
        this.total = total;
        this.amountPaid = amountPaid;
        if (amountPaid > total)
            changeGiven = amountPaid - total;
        else changeGiven = 0;
    }

    public String toString() {
        String ret = date.toString();
        for (Product p : items) {
            ret += "\n" + p.toStringWithQuantity();
        }
        ret += "\nTotal: " + total;
        ret += "\nAmount paid: " + amountPaid;
        ret += "\nChange given: " + changeGiven;
        return ret;
    }

    public Date getDate() {
        return date;
    }

    public List<Product> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public double getChangeGiven() {
        return changeGiven;
    }
}
