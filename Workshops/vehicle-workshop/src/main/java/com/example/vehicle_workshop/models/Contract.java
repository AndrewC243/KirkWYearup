package com.example.vehicle_workshop.models;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class Contract {
    private String date;
    private String customerName;
    private String custoemrEmail;
    @OneToOne
    @JoinColumn(referencedColumnName = "vin", name = "vin")
    private Vehicle vehicleSold;

    public abstract double getTotalPrice();
    public abstract double getMonthlyPayment();
}


