package com.example.capstone01.Service;

import com.example.capstone01.Model.MerchantStock;
import com.example.capstone01.Model.Product;
import com.example.capstone01.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

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
        Product product = productService.getProductById(productId);
        if (user == null  || user.getRole().equals("Admin") || product == null) {
            return false;
        }
        MerchantStock merchantStock = null;
        for (MerchantStock stock : merchantStockService.getAllMerchantStocks()) {
            if (stock.getProductId().equals(productId) && stock.getMerchantId().equals(merchantId)) {
                merchantStock = stock;
                break;
            }
        }
        if (merchantStock == null || merchantStock.getStock() < 1 || user.getBalance() < product.getPrice()) {
            return false;
        }

        user.setBalance(user.getBalance() - product.getPrice());
        merchantStock.setStock(merchantStock.getStock() - 1);
        product.setPurchaseDateTime(LocalDateTime.now());
        product.setStatus("Purchased");
        user.getPurchasedProducts().add(product);

        return true;
    }

    public boolean refundProduct(String userId, String productId, String merchantId) {
        User user = getUserById(userId);
        if (user == null || user.getRole().equals("Admin")) {
            return false;
        }
        Product product = null;
        for (Product p : user.getPurchasedProducts()) {
            if (p.getId().equals(productId)) {
                product = p;
                break;
            }
        }
        if (product == null) {
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
        if (ChronoUnit.DAYS.between(product.getPurchaseDateTime(), LocalDateTime.now()) > 15) {
            return false;
        }
        merchantStock.setStock(merchantStock.getStock() + 1);
        user.setBalance(user.getBalance() + product.getPrice());
        product.setStatus("Refunded");
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
                        return p1.getPurchaseDateTime().compareTo(p2.getPurchaseDateTime());
                    }
                }
            );
        } else {
            orderHistory.sort(new Comparator<Product>() {
                    @Override
                    public int compare(Product p1, Product p2) {
                        return p2.getPurchaseDateTime().compareTo(p1.getPurchaseDateTime());
                    }
                }
            );
        }
        return orderHistory;
    }

    public boolean addReview(String userId, String productId, double rating, String reviewText) {
        User user = getUserById(userId);
        if (user == null || rating < 0 || rating > 5) {
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

    public ArrayList<Product> getMostAndLeastPurchasedProducts(String adminId) {
        User admin = getUserById(adminId);
        if (admin == null || !admin.getRole().equals("Admin")) {
            return null;
        }

        ArrayList<Product> allProducts = productService.getAllProducts();
        Map<String, Integer> productPurchaseCount = new HashMap<>();

        for (User user : getAllUsers()) {
            for (Product purchased : user.getPurchasedProducts()) {
                productPurchaseCount.merge(purchased.getId(), 1, Integer::sum);
            }
        }

        Product mostPurchased = null;
        Product leastPurchased = null;
        int maxCount = 0;
        int minCount = Integer.MAX_VALUE;

        for (Product product : allProducts) {
            int count = productPurchaseCount.getOrDefault(product.getId(), 0);
            if (count > maxCount) {
                maxCount = count;
                mostPurchased = product;
            }
            if (count < minCount && count > 0) {
                minCount = count;
                leastPurchased = product;
            }
        }

        ArrayList<Product> result = new ArrayList<>();
        if (mostPurchased != null)
            result.add(mostPurchased);
        if (leastPurchased != null)
            result.add(leastPurchased);
        return result;
    }
}
