package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the employee file to process: ");
        List<Employee> employees = readFile(sc.nextLine());
        System.out.println("Enter the name of the payroll file to process: ");
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/"+sc.nextLine()))) {
            bw.write("id|name|gross pay");
            for (Employee employee : employees) {
                bw.newLine();
                bw.write(String.format("%s|%s|%.2f", employee.getEmployeeId(), employee.getName(), employee.getGrossPay()));
            }
        } catch (IOException e) {}
    }

    private static List<Employee> readFile(String filename) {
        List<Employee> employees = new ArrayList<Employee>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + filename))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                line = line.replace(",", "");
                String[] data = line.split("\\|");
                employees.add(new Employee(
                        Integer.parseInt(data[0]),
                        data[1],
                        Double.parseDouble(data[2]),
                        Double.parseDouble(data[3])
                ));
            }
        } catch (IOException e) {
            System.out.println("Couldn't do it :(");
        }
        return employees;
    }
}