package com.pluralsight;

public class Person {
    private int health;
    private int hunger;
    private String name;
    private Occupation occupation;

    public Person(int health, int hunger, String name, Occupation occupation) {
        this.health = health;
        this.hunger = hunger;
        this.name = name;
        this.occupation = occupation;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public int getHealth() {
        return health;
    }

    public int getHunger() {
        return hunger;
    }

    public String getName() {
        return name;
    }

    public Occupation getOccupation() {
        return occupation;
    }
}
