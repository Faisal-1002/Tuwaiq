package com.example.capstone01repo.Service;

import com.example.capstone01repo.Model.Purchase;
import com.example.capstone01repo.Repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    public Boolean addPurchase(Purchase purchase) {
        purchaseRepository.save(purchase);
        return true;
    }

    public Boolean updatePurchase(Integer id, Purchase updatedPurchase) {
        for (Purchase existing : purchaseRepository.findAll()) {
            if (existing.getId().equals(id)) {
                updatedPurchase.setId(id); // preserve the ID
                purchaseRepository.save(updatedPurchase);
                return true;
            }
        }
        return false;
    }

    public Boolean deletePurchase(Integer id) {
        for (Purchase purchase : purchaseRepository.findAll()) {
            if (purchase.getId().equals(id)) {
                purchaseRepository.delete(purchase);
                return true;
            }
        }
        return false;
    }

    public Purchase getPurchaseById(Integer id) {
        for (Purchase purchase : purchaseRepository.findAll()) {
            if (purchase.getId().equals(id)) {
                return purchase;
            }
        }
        return null;
    }

    public List<Purchase> getPurchasesByUserId(String userId) {
        List<Purchase> result = new ArrayList<>();
        for (Purchase purchase : purchaseRepository.findAll()) {
            if (purchase.getUserId().equals(userId)) {
                result.add(purchase);
            }
        }
        return result;
    }

    public List<Purchase> getPurchasesByProductId(String productId) {
        List<Purchase> result = new ArrayList<>();
        for (Purchase purchase : purchaseRepository.findAll()) {
            if (purchase.getProductId().equals(productId)) {
                result.add(purchase);
            }
        }
        return result;
    }
}