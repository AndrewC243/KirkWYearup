package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

record Recipe(String dish, ArrayList<String> ingredients, ArrayList<String> instructions) {
    public String toString() {
        String ret = this.dish + "\n";
        for(String ingredient : ingredients) {
            ret += "\t" + ingredient + "\n";
        }
        ret += "\n";
        for(String instruction : instructions) {
            ret += "\t" + instruction + "\n";
        }
        return ret;
    }
}

public class Main {
    private ArrayList<Recipe> recipes = new ArrayList();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main main = new Main();

        while(true) {
            System.out.println("Welcome to the recipe manager!\n\nPlease select an option:\n    (V)iew your recipes\n    (A)dd a recipe\n    (D)elete a recipe\n    (Q)uit\n");
            String input = sc.next();
            sc.nextLine();
            switch (input.toUpperCase()) {
                case "V":
                    System.out.println(main);
                    System.out.println("Process enter to continue");
                    sc.nextLine();
                    break;
                case "A":
                    System.out.println("What is the name of the recipe?");
                    String name = sc.nextLine();
                    ArrayList<String> ingredients = new ArrayList();
                    while(true) {
                        System.out.println("Please enter an ingredient, or enter \"done\" to finish.");
                        String ingredient = sc.nextLine();
                        if (ingredient.equalsIgnoreCase("done")) {
                            main.addRecipe(new Recipe(name, ingredients));
                            break;
                        }
                        ingredients.add(ingredient);
                    }
                    break;
                case "D":
                    System.out.println(String.valueOf(main) + "\n\nPlease enter the name of the recipe you wish to delete:");
                    if (main.removeRecipe(sc.nextLine()))
                        System.out.println("Recipe deleted.");
                    else
                        System.out.println("Recipe not found.");
                    break;
                case "Q":
                    return;
            }
        }
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }
    public boolean removeRecipe(String dishName) {
        if (!hasRecipe(dishName)) {
            return false;
        }
        else {
            for(int i = 0; i < recipes.size(); ++i) {
                if (recipes.get(i).dish.equals(dishName)) {
                    recipes.remove(i);
                    return true;
                }
            }
            return false;
        }
    }

    public boolean hasRecipe(String dishName) {
        for (Recipe recipe : recipes) {
            if (recipe.dish.equalsIgnoreCase(dishName))
                return true;
        }
        return false;
    }

    public String toString() {
        String ret = "";
        int counter = 1;
        for(Recipe recipe : recipes) {
            ret += (counter++) + ": " + recipe.toString() + "\n";
        }
        return ret;
    }
}
