package org.example;

public class Product {
    private String SKU;
    private String name;
    private double price;
    private String department;
    private int quantity;

    public Product(String SKU, String name, double price, String department) {
        this.SKU = SKU;
        this.name = name;
        this.price = price;
        this.department = department;
        this.quantity = 1;
    }

    public String toString() {
        return String.format("%s (%s) -- %.2f -- %s", name, SKU, price, department);
    }

    public String toStringWithQuantity() {
        return String.format("%s\t%s (%s) -- %.2f -- %s", quantity, name, SKU, getCost(), department);
    }

    public void addQuantity() {
        quantity++;
    }

    public void removeQuantity() {
        quantity--;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return price * quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getQuantity() {
        return quantity;
    }
}
