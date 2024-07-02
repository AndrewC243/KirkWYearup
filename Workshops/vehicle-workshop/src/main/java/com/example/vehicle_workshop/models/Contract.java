package com.example.vehicle_workshop.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class Contract {
    private String date;
    private String customerName;
    private String customerEmail;
    @OneToOne
    @PrimaryKeyJoinColumn(name = "vin")
    private Vehicle vehicleSold;

    public abstract double getTotalPrice();
    public abstract double getMonthlyPayment();
}