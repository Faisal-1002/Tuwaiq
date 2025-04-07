package com.example.capstone01repo.Service;

import com.example.capstone01repo.Model.Merchant;
import com.example.capstone01repo.Model.MerchantStock;
import com.example.capstone01repo.Model.Product;
import com.example.capstone01repo.Repository.MerchantRepository;
import com.example.capstone01repo.Repository.MerchantStockRepository;
import com.example.capstone01repo.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantStockService {

    private final MerchantStockRepository merchantStockRepository;
    private final ProductRepository productRepository;
    private final MerchantRepository merchantRepository;

    public List<MerchantStock> getAllMerchantStocks() {
        return merchantStockRepository.findAll();
    }

    public Boolean addMerchantStock(MerchantStock merchantStock) {
        if (!productExists(merchantStock.getProductId()) || !merchantExists(merchantStock.getMerchantId())) {
            return false;
        }

        for (MerchantStock existing : merchantStockRepository.findAll()) {
            if (existing.getProductId().equals(merchantStock.getProductId()) &&
                    existing.getMerchantId().equals(merchantStock.getMerchantId())) {
                return false;
            }
        }

        merchantStockRepository.save(merchantStock);
        return true;
    }

    public Boolean updateMerchantStock(Integer id, MerchantStock updatedStock) {
        if (!productExists(updatedStock.getProductId()) || !merchantExists(updatedStock.getMerchantId())) {
            return false;
        }

        for (MerchantStock existing : merchantStockRepository.findAll()) {
            if (existing.getId().equals(id)) {
                updatedStock.setId(id); // preserve ID
                merchantStockRepository.save(updatedStock);
                return true;
            }
        }
        return false;
    }

    public Boolean deleteMerchantStock(Integer id) {
        for (MerchantStock stock : merchantStockRepository.findAll()) {
            if (stock.getId().equals(id)) {
                merchantStockRepository.delete(stock);
                return true;
            }
        }
        return false;
    }

    public Boolean addProductStock(String productId, String merchantId, Integer quantity) {
        if (quantity == null || quantity <= 0) return false;

        for (MerchantStock stock : merchantStockRepository.findAll()) {
            if (stock.getProductId().equals(productId) && stock.getMerchantId().equals(merchantId)) {
                stock.setStock(stock.getStock() + quantity);
                merchantStockRepository.save(stock);
                return true;
            }
        }
        return false;
    }

    private Boolean productExists(String productId) {
        for (Product p : productRepository.findAll()) {
            if (p.getId().toString().equals(productId)) return true;
        }
        return false;
    }

    private Boolean merchantExists(String merchantId) {
        for (Merchant m : merchantRepository.findAll()) {
            if (m.getId().toString().equals(merchantId)) return true;
        }
        return false;
    }
}
