package com.example.DataExample.repositories;

import com.example.DataExample.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findByNameContainingIgnoreCase(String name);
    public Product 
}
