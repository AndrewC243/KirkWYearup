package org.example;

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog("Charlie", 3, "Husky");
        System.out.println(dog.getAge());
        dog.setAge(4);
        System.out.println(dog.getAge());
        System.out.println(dog.getBreed());
        dog.setBreed("Pomeranian");
        System.out.println(dog.getBreed());
    }
}