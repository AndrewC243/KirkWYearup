package com.pluralsight;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        TransactionHandler th = new TransactionHandler();
        System.out.println(th.filter("Client A"));
    }
}