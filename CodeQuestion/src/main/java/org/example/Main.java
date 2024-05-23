package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {

    }

    public int largestInt(int[] ints) {
//        int largest
        //        make a for loop
//      if int larger than largest replace largest with int
//        return largest
        int largest = Integer.MIN_VALUE;
        for (int i : ints) {
            if (i > largest) {
                largest = i;
            }
        }
        return largest;
    }
}