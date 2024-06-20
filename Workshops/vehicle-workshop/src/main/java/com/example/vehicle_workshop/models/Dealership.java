package com.example.vehicle_workshop.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Dealership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dealershipId;
    private String address;
    private String name;
    private String phone;
}
