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
        for (Product product : productService.getAllProducts()) {
            if (product.getId().equals(merchantStock.getProductId())) {
                for (Merchant merchant : merchantService.getMerchants()) {
                    if (merchant.getId().equals(merchantStock.getMerchantId())) {
                        for (int i = 0; i < merchantStocks.size(); i++) {
                            if (merchantStocks.get(i).getMerchantId().equals(merchantStock.getMerchantId())) {
                                merchantStocks.add(merchantStocks.get(i));
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean updateMerchantStock(String id, MerchantStock merchantStock) {
        for (Product product : productService.getAllProducts()) {
            if (product.getId().equals(merchantStock.getProductId())) {
                for (Merchant merchant : merchantService.getMerchants()) {
                    if (merchant.getId().equals(merchantStock.getMerchantId())) {
                        for (int i = 0; i < merchantStocks.size(); i++) {
                            if (merchantStocks.get(i).getMerchantId().equals(id)) {
                                merchantStocks.set(i, merchantStocks.get(i));
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean deleteMerchantStock(String id) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getMerchantId().equals(id)) {
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }

}
