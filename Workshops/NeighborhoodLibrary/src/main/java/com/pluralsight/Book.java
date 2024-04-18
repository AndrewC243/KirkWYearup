package com.pluralsight;

public class Book {
    private int id;
    private String ISBN;
    private String title;
    private boolean isCheckedOut;
    private String checkedOutTo;

    public Book(int id, String ISBN, String title) {
        this.id = id;
        this.ISBN = ISBN;
        this.title = title;
        this.isCheckedOut = false;
        checkedOutTo = "";
    }

    public void checkOut(String name) {
        isCheckedOut = true;
        checkedOutTo = name;
    }

    public void checkIn() {
        isCheckedOut = false;
        checkedOutTo = "";
    }

    public String toString() {
        return  id + " - " + ISBN + "\n" + title + "\nChecked out: " + isCheckedOut + "\nChecked out to: " + checkedOutTo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        isCheckedOut = checkedOut;
    }

    public String getCheckedOutTo() {
        return checkedOutTo;
    }

    public void setCheckedOutTo(String checkedOutTo) {
        this.checkedOutTo = checkedOutTo;
    }
}
