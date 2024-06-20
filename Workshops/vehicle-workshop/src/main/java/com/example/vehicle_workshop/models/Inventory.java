package com.example.vehicle_workshop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Inventory {
    @Id
    private String vin;
    private long dealershipId;
    @OneToOne
    @MapsId
    @JoinColumn(name = "dealershipId")
    private Dealership dealership;
    @OneToOne
    @MapsId
    @JoinColumn(name = "vin")
    private Vehicle vehicle;
}
