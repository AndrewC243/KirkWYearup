package com.example.first_app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
public class Product {
    private long id;
    private String name;
    private BigDecimal price;
    private boolean available;
}
