package com.pluralsight;

import java.io.*;
import java.text.ParseException;
import java.util.*;

public class TransactionHandler {
    private List<Transaction> transactionList;
    private File transactionFile = new File("src/main/resources/transactions.csv");

    public TransactionHandler() {
        transactionList = readTransactions();
        // Dates automatically sort oldest to newest so the list is reversed to compensate.
        Collections.sort(transactionList);
        Collections.reverse(transactionList);
    }

    // oh boy here we go
    // uses calendar to set filter
    // continues adding to list until the date in the loop is no longer within the specified bounds
    public List<Transaction> filter(boolean payment) {
        List<Transaction> l = new ArrayList<>();
        if (payment) {
            for (Transaction t : transactionList) {
                if (t.getAmount() < 0) l.add(t);
            }
            return l;
        }
        for (Transaction t : transactionList) {
            if (t.getAmount() > 0) l.add(t);
        }
        return l;
    }
    public List<Transaction> filter(Calendar c) {
        List<Transaction> l = new ArrayList<>();

        for (Transaction t : transactionList) {
            if (!(t.getDate().after(c.getTime())) || t.getDate().equals(c.getTime())) return l;
            l.add(t);
        }
        return l;
    }
    public List<Transaction> filter(String vendor) {
        List<Transaction> l = new ArrayList<>();
        for (Transaction t : transactionList) {
            if (t.getVendor().equals(vendor)) l.add(t);
        }
        return l;
    }
    public List<Transaction> monthToDate() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        return filter(c);
    }
    public List<Transaction> previousMonth() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 1);
        return filter(c);
    }
    public List<Transaction> yearToDate() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.MONTH, 1);
        return filter(c);
    }
    public List<Transaction> previousYear() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, c.get(Calendar.YEAR) - 1);
        return filter(c);
    }

    public void writeTransaction(Transaction transaction) {
        transactionList.addFirst(transaction);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(transactionFile, true))) {
            bw.append(transaction.toCSV());
        } catch (IOException e) {
            System.out.println("Could not write transaction file.");
        }
    }
    private List<Transaction> readTransactions() {
        List<Transaction> l = new ArrayList<>();
        if (transactionFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(transactionFile))) {
                String line = br.readLine();
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    Date date = Transaction.df.parse(parts[0] + "|" + parts[1]);
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]);
                    l.add(new Transaction(date, description, vendor, amount));
                }
            } catch (IOException | ParseException e) {
                System.out.println("Could not read transaction file.");
            }
        } else try {
            transactionFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Could not create transaction file.");
        }
        return l;
    }
    public List<Transaction> getTransactionList() { return transactionList; }
}
