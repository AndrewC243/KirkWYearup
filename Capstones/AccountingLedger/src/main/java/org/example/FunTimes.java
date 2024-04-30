package org.example;

public class FunTimes {
    public static void rollForDexterity() {
        if (Math.random() <= 0.05) {
            System.out.println("Dexterity check failed :(");
            System.exit(1);
        }
    }

    public static boolean perceptionCheck() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {}
        return Math.random() <= 0.75;
    }
}
