package com.pluralsight;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//      Book instantiations sourced from ChatGPT
        Book[] books = new Book[3];
        books[0] = new Book(0, "978-3-16-148410-0", "Java Programming");
        books[1] = new Book(1, "978-1-23-456789-0", "Introduction to Algorithms");
        books[2] = new Book(2, "978-0-12-345678-9", "Data Structures and Algorithms");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                        Welcome to the library.
                            Show (a)vailable books
                            Show (c)hecked out books
                            (E)xit
                    """);
            String input = scanner.nextLine();
            switch (input.toLowerCase()) {
                case "a":
                    for (Book book : books)
                        System.out.println(book);
                    System.out.println("Enter a book to check it out, or enter anything else to return to main screen");
                    input = scanner.nextLine();
                    for (Book book : books)
                        if (book.getTitle().equalsIgnoreCase(input)) {
                            System.out.println("What is your name");
                            String name = scanner.nextLine();
                            book.setCheckedOut(true);
                            book.setCheckedOutTo(name);
                        }
                    break;
                case "c":
                    while (true) {
                        for (Book book : books)
                            if (book.isCheckedOut())
                                System.out.println(book);
                        System.out.println("Enter 'C' to check in a book or 'X' to return to the main menu.");
                        input = scanner.nextLine();
                        if (input.equalsIgnoreCase("X")) break;
                        if (input.equalsIgnoreCase("C")) {
                            System.out.println("Please enter the ID of the book you would like to check in.");
                            int id = scanner.nextInt();
                            books[id].setCheckedOut(false);
                            books[id].setCheckedOutTo("");
                        }
                    }
                    break;
                case "e":
                    return;
            }
        }
    }
}