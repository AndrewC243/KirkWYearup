package org.example;

public class Main {
    public static void main(String[] args) {
        FixedList<String> list = new FixedList<>(3);
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        for (String s : list.getItems())
            System.out.println(s);
    }
}