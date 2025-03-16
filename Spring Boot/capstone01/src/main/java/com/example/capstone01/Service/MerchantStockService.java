package com.example.capstone01.Service;

import com.example.capstone01.Model.Merchant;
import com.example.capstone01.Model.MerchantStock;
import com.example.capstone01.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
@RequiredArgsConstructor
public class MerchantStockService {

    ArrayList<MerchantStock> merchantStocks = new ArrayList<MerchantStock>();
    private final ProductService productService;
    private final MerchantService merchantService;

    public ArrayList<MerchantStock> getAllMerchantStocks() {
        return merchantStocks;
    }

    public boolean addMerchantStock(MerchantStock merchantStock) {
        Product product = productService.getProductById(merchantStock.getProductId());
        if (product == null) {
            return false;
        }
        Merchant merchant = merchantService.getMerchantById(merchantStock.getMerchantId());
        if (merchant == null) {
            return false;
        }
        for (MerchantStock existingStock : merchantStocks) {
            if (existingStock.getMerchantId().equals(merchantStock.getMerchantId()) &&
                    existingStock.getProductId().equals(merchantStock.getProductId())) {
                return false;
            }
        }
        merchantStocks.add(merchantStock);
        return true;
    }

    public boolean updateMerchantStock(String id, MerchantStock merchantStock) {
        Product product = productService.getProductById(merchantStock.getProductId());
        if (product == null) {
            return false;
        }
        Merchant merchant = merchantService.getMerchantById(merchantStock.getMerchantId());
        if (merchant == null) {
            return false;
        }
        for (int i = 0; i < merchantStocks.size(); i++) {
            MerchantStock existingStock = merchantStocks.get(i);
            if (existingStock.getId().equals(id)) {
                merchantStocks.set(i, merchantStock);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchantStock(String id) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId().equals(id)) {
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean addProductStock(String productId, String merchantId, int quantity) {
        if (productId == null || merchantId == null || quantity <= 0) {
            return false;
        }
        for (MerchantStock merchantStock : merchantStocks) {
            if (merchantStock.getProductId().equals(productId) && merchantStock.getMerchantId().equals(merchantId)) {
                merchantStock.setStock(merchantStock.getStock() + quantity);
                return true;
            }
        }
        return false;
    }

}
