package org.example;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

// Comparable implementation idea sourced from https://stackoverflow.com/questions/5927109/sort-objects-in-arraylist-by-date
public class Transaction implements Comparable<Transaction> {
    public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss");
    private Date date;
    private String description;
    private String vendor;
    private double amount;

    public Transaction(Date date, String description, String vendor, double amount) {
        this.date = date;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public String toCSV() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return "\n" + (df.format(cal.getTime()))
                + "|" + description + "|" + vendor + "|" + String.format("%.2f", amount);
    }

    public String toString() {
        String[] dateTimeSeparated = df.format(date).split("\\|");
        return dateTimeSeparated[0] + " -- " +
                dateTimeSeparated[1] + " -- " +
                description + " -- " +
                vendor + " -- " +
                String.format("%.2f", amount);
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public int compareTo(Transaction o) {
        return date.compareTo(o.date);
    }
}
