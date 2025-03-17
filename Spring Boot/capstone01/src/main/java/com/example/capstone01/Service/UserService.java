package com.example.capstone01.Service;

import com.example.capstone01.Model.Merchant;
import com.example.capstone01.Model.MerchantStock;
import com.example.capstone01.Model.Product;
import com.example.capstone01.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
                User existingUser = users.get(i);

                user.setPurchasedProducts(existingUser.getPurchasedProducts());

                existingUser.setUsername(user.getUsername());
                existingUser.setPassword(user.getPassword());
                existingUser.setEmail(user.getEmail());
                existingUser.setRole(user.getRole());
                existingUser.setBalance(user.getBalance());

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
        User user = getUserById(userId);
        Product originalProduct = productService.getProductById(productId);
        Merchant merchant = merchantService.getMerchantById(merchantId);

        if (user == null || user.getRole().equals("Admin")|| originalProduct == null || merchant == null) {
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
        if (user.getBalance() < originalProduct.getPrice()) {
            return false;
        }

        Product purchasedProduct = new Product(
                originalProduct.getId(),
                originalProduct.getName(),
                originalProduct.getPrice(),
                originalProduct.getCategoryId(),
                LocalDateTime.now(),
                originalProduct.getRating(),
                originalProduct.getReviews()
        );

        merchantStock.setStock(merchantStock.getStock() - 1);
        user.setBalance(user.getBalance() - purchasedProduct.getPrice());

        user.getPurchasedProducts().add(purchasedProduct);

        return true;
    }

    public boolean refundProduct(String userId, String productId, String merchantId) {
        User user = getUserById(userId);
        Product product = productService.getProductById(productId);
        Merchant merchant = merchantService.getMerchantById(merchantId);
        if (user == null || user.getRole().equals("Admin") || product == null || merchant == null || !(user.getPurchasedProducts().contains(product))) {
            return false;
        }
        MerchantStock merchantStock = null;
        for (MerchantStock stock : merchantStockService.getAllMerchantStocks()) {
            if (stock.getProductId().equals(productId) && stock.getMerchantId().equals(merchantId)) {
                merchantStock = stock;
                break;
            }
        }
        if (merchantStock == null) {
            return false;
        }
        merchantStock.setStock(merchantStock.getStock() + 1);
        user.setBalance(user.getBalance() + product.getPrice());
        user.getPurchasedProducts().remove(product);
        return true;
    }

    public ArrayList<Product> getUserOrderHistory(String userId, boolean ascending) {
        User user = getUserById(userId);
        if (user == null) {
            return null;
        }
        ArrayList<Product> orderHistory = new ArrayList<>(user.getPurchasedProducts());
        if (ascending) {
            orderHistory.sort(new Comparator<Product>() {
                    @Override
                    public int compare(Product p1, Product p2) {
                        return p1.getPurchaseTime().compareTo(p2.getPurchaseTime());
                    }
                }
            );
        } else {
            orderHistory.sort(new Comparator<Product>() {
                    @Override
                    public int compare(Product p1, Product p2) {
                        return p2.getPurchaseTime().compareTo(p1.getPurchaseTime());
                    }
                }
            );
        }
        return orderHistory;
    }

    public boolean addReview(String userId, String productId, double rating, String reviewText) {
        User user = getUserById(userId);
        if (user == null) {
            return false;
        }

        ArrayList<Product> purchasedProducts = getUserOrderHistory(userId, true);

        Product purchasedProduct = null;
        for (Product p : purchasedProducts) {
            if (p.getId().equals(productId)) {
                purchasedProduct = p;
                break;
            }
        }

        if (purchasedProduct == null) {
            return false;
        }

        String userReviewIdentifier = "User-" + userId;

        for (String review : purchasedProduct.getReviews()) {
            if (review.startsWith(userReviewIdentifier)) {
                return false;
            }
        }

        purchasedProduct.getReviews().add(userReviewIdentifier + ": " + reviewText);
        double totalRatings = purchasedProduct.getRating() * (purchasedProduct.getReviews().size() - 1);
        double newAverageRating = (totalRatings + rating) / purchasedProduct.getReviews().size();
        purchasedProduct.setRating(newAverageRating);
        return true;
    }

}
