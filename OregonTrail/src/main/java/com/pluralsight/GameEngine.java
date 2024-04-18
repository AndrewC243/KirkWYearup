package com.pluralsight;

import java.util.Scanner;

public class GameEngine {
    private Person[] persons;

    public Person[] getPersons() {
        return persons;
    }

    public void setPersons(Person[] persons) {
        this.persons = persons;
    }

    public void setupTeam() {
//        Welcome message
        System.out.println("Welcome to the Oreogn Trail game!");
//        Create array to hold each player as they get made
        Person[] personBuilder = new Person[4];
        Scanner scanner = new Scanner(System.in);
//        Create for loop to loop 4 times
//        Prompt user to specify name and occupation of each player
//        Take in inputs for each
        for (int i = 0; i < persons.length; i++) {
            System.out.println("What is player "  + i + 1 + "'s name?");
            String name = scanner.nextLine();
            System.out.println("What is player " + i + 1 + "'s occupation??");
            String occupation = scanner.nextLine();
            personBuilder[i] = new Person(100, 100, name, occupation);
        }
        setPersons(personBuilder);
    }
}
