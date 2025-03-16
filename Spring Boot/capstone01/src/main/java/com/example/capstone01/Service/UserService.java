package com.example.capstone01.Service;

import com.example.capstone01.Model.Merchant;
import com.example.capstone01.Model.MerchantStock;
import com.example.capstone01.Model.Product;
import com.example.capstone01.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
@RequiredArgsConstructor
public class UserService {


    ArrayList<User> users = new ArrayList<>();
    private final MerchantStockService merchantStockService;
    private final ProductService productService;
    private final MerchantService merchantService;

    public ArrayList<User> getAllUsers() {
        return users;
    }

    public boolean addUser(User user) {
        for (User u : users) {
            if (u.getId().equals(user.getId())) {
                return false;
            }
        }
        users.add(user);
        return true;
    }

    public boolean updateUser(String id, User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(String id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    public User getUserById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public boolean buyProduct(String userId, String productId, String merchantId) {
        if (userId == null || productId == null || merchantId == null) {
            return false;
        }
        User user = getUserById(userId);
        Product product = productService.getProductById(productId);
        Merchant merchant = merchantService.getMerchantById(merchantId);
        if (user == null || product == null || merchant == null) {
            return false;
        }
        MerchantStock merchantStock = null;
        for (MerchantStock stock : merchantStockService.getAllMerchantStocks()) {
            if (stock.getProductId().equals(productId) && stock.getMerchantId().equals(merchantId)) {
                merchantStock = stock;
                break;
            }
        }
        if (merchantStock == null || merchantStock.getStock() < 1) {
            return false;
        }
        if (user.getBalance() < product.getPrice()) {
            return false;
        }
        merchantStock.setStock(merchantStock.getStock() - 1);
        user.setBalance(user.getBalance() - product.getPrice());
        return true;
    }
}
