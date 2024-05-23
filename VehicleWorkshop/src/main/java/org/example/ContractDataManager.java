package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractDataManager {
    public static void saveContract(Contract contract) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/contracts.csv", true))) {
            if (contract instanceof SalesContract)
                bw.append((SalesContract)contract + "\n");
            else bw.append((LeaseContract)contract + "\n");
        } catch (IOException e) {
            System.out.println("Unable to save contract");
        }
    }
}