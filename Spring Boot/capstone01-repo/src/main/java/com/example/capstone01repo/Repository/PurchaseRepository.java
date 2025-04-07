package com.example.capstone01repo.Repository;

import com.example.capstone01repo.Model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
}
