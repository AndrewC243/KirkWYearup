package com.example.vehicle_workshop.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SalesContract extends Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long salesContractId;
    private boolean financed;

    @Override
    public double getTotalPrice() {
        return getSalesTax() + getRecordingFee() + getProcessingFee() + getVehicleSold().getPrice();
    }

    @Override
    public double getMonthlyPayment() {
        if (!financed) return 0;
        var price = getVehicleSold().getPrice();
        if (price >= 10000)
            return (price * (0.0425 / 12 * Math.pow((0.0425 / 12) + 1, 48))) / (Math.pow(1 + (0.0425 / 12), 48) - 1);
        return (price * (0.0525 / 12 * Math.pow((0.0525 / 12) + 1, 24))) / (Math.pow(1 + (0.0525 / 12), 24) - 1);
    }

    public double getRecordingFee() { return 100; }
    public double getProcessingFee() {
        if (getVehicleSold().getPrice() >= 10000)
            return 495;
        return 295;
    }
    public double getSalesTax() { return 0.05 * getVehicleSold().getPrice(); }

    @Override
    public String toString() {
        return "SALE|"
                + getDate() + "|"
                + getCustomerName() + "|"
                + getCustoemrEmail() + "|"
                + getVehicleSold().getVin() + "|"
                + getVehicleSold().getYear() + "|"
                + getVehicleSold().getMake() + "|"
                + getVehicleSold().getModel() + "|"
                + getVehicleSold().getVehicleType() + "|"
                + getVehicleSold().getColor() + "|"
                + getVehicleSold().getOdometer() + "|"
                + getVehicleSold().getPrice() + "|"
                + getSalesTax() + "|"
                + getRecordingFee() + "|"
                + getProcessingFee() + "|"
                + getTotalPrice() + "|"
                + (isFinanced() ? "YES" : "NO") + "|"
                + getMonthlyPayment();
    }
}
