package com.example.vehicle_workshop.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LeaseContract extends Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long leaseContractId;

    @Override
    public double getTotalPrice() {
        return getExpectedEndingValue() + getLeaseFee();
    }

    @Override
    public double getMonthlyPayment() {
        return (getExpectedEndingValue() / 36) + ((0.04 / 24) * (getVehicleSold().getPrice() + getExpectedEndingValue()));
    }

    @Override
    public String toString() {
        return "LEASE|"
                + getDate() + "|"
                + getCustomerName() + "|"
                + getCustomerEmail() + "|"
                + getVehicleSold().getVin() + "|"
                + getVehicleSold().getYear() + "|"
                + getVehicleSold().getMake() + "|"
                + getVehicleSold().getModel() + "|"
                + getVehicleSold().getVehicleType() + "|"
                + getVehicleSold().getColor() + "|"
                + getVehicleSold().getOdometer() + "|"
                + getVehicleSold().getPrice() + "|"
                + getExpectedEndingValue() + "|"
                + getLeaseFee() + "|"
                + getTotalPrice() + "|"
                + getMonthlyPayment() + "|";
    }

    public double getExpectedEndingValue() { return getVehicleSold().getPrice() / 2; }
    public double getLeaseFee() { return getVehicleSold().getPrice() * .07; }
}
