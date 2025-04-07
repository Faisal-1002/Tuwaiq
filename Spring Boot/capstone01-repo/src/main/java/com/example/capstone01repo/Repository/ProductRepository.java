package com.example.capstone01repo.Repository;

import com.example.capstone01repo.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
