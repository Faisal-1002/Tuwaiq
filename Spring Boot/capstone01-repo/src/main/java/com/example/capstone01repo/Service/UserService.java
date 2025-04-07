package com.example.capstone01repo.Service;

import com.example.capstone01repo.Model.Product;
import com.example.capstone01repo.Model.Purchase;
import com.example.capstone01repo.Model.User;
import com.example.capstone01repo.Model.MerchantStock;
import com.example.capstone01repo.Repository.UserRepository;
import com.example.capstone01repo.Repository.ProductRepository;
import com.example.capstone01repo.Repository.PurchaseRepository;
import com.example.capstone01repo.Repository.MerchantStockRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final MerchantStockRepository merchantStockRepository;
    private final PurchaseRepository purchaseRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Boolean addUser(User user) {
        userRepository.save(user);
        return true;
    }

    public Boolean updateUser(Integer id, User updatedUser) {
        List<User> users = userRepository.findAll();
        for (User existingUser : users) {
            if (existingUser.getId().equals(id)) {
                existingUser.setUsername(updatedUser.getUsername());
                existingUser.setPassword(updatedUser.getPassword());
                existingUser.setEmail(updatedUser.getEmail());
                existingUser.setRole(updatedUser.getRole());
                existingUser.setBalance(updatedUser.getBalance());
                userRepository.save(existingUser);
                return true;
            }
        }
        return false;
    }

    public Boolean deleteUser(Integer id) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getId().equals(id)) {
                userRepository.delete(user);
                return true;
            }
        }
        return false;
    }

    public User getUserById(Integer id) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public Boolean buyProduct(Integer userId, Integer productId, Integer merchantId) {
        User user = getUserById(userId);
        Product product = null;
        for (Product p : productRepository.findAll()) {
            if (p.getId().equals(productId)) {
                product = p;
                break;
            }
        }

        if (user == null || product == null || "Admin".equals(user.getRole())) return false;

        MerchantStock merchantStock = null;
        for (MerchantStock ms : merchantStockRepository.findAll()) {
            if (ms.getProductId().equals(productId.toString()) && ms.getMerchantId().equals(merchantId.toString())) {
                merchantStock = ms;
                break;
            }
        }

        if (merchantStock == null || merchantStock.getStock() < 1 || user.getBalance() < product.getPrice()) return false;

        user.setBalance(user.getBalance() - product.getPrice());
        merchantStock.setStock(merchantStock.getStock() - 1);
        product.setStatus("Purchased");
        product.setPurchaseDateTime(LocalDateTime.now());

        userRepository.save(user);
        productRepository.save(product);
        merchantStockRepository.save(merchantStock);

        Purchase purchase = new Purchase(null, userId.toString(), productId.toString(), LocalDateTime.now(), "Purchased");
        purchaseRepository.save(purchase);

        return true;
    }

    public Boolean refundProduct(Integer userId, Integer productId, Integer merchantId) {
        User user = getUserById(userId);
        if (user == null || "Admin".equals(user.getRole())) return false;

        Purchase purchase = null;
        for (Purchase p : purchaseRepository.findAll()) {
            if (p.getUserId().equals(userId.toString()) &&
                    p.getProductId().equals(productId.toString()) &&
                    "Purchased".equals(p.getStatus())) {
                purchase = p;
                break;
            }
        }

        if (purchase == null || ChronoUnit.DAYS.between(purchase.getPurchaseDate(), LocalDateTime.now()) > 15)
            return false;

        Product product = null;
        for (Product p : productRepository.findAll()) {
            if (p.getId().equals(productId)) {
                product = p;
                break;
            }
        }

        MerchantStock merchantStock = null;
        for (MerchantStock ms : merchantStockRepository.findAll()) {
            if (ms.getProductId().equals(productId.toString()) && ms.getMerchantId().equals(merchantId.toString())) {
                merchantStock = ms;
                break;
            }
        }

        if (product == null || merchantStock == null) return false;

        user.setBalance(user.getBalance() + product.getPrice());
        product.setStatus("Refunded");
        merchantStock.setStock(merchantStock.getStock() + 1);
        purchase.setStatus("Refunded");

        userRepository.save(user);
        productRepository.save(product);
        merchantStockRepository.save(merchantStock);
        purchaseRepository.save(purchase);

        return true;
    }

    public List<Purchase> getUserOrderHistory(Integer userId, Boolean ascending) {
        List<Purchase> userPurchases = new ArrayList<>();
        for (Purchase p : purchaseRepository.findAll()) {
            if (p.getUserId().equals(userId.toString())) {
                userPurchases.add(p);
            }
        }
        userPurchases.sort(Comparator.comparing(Purchase::getPurchaseDate));
        if (!ascending) Collections.reverse(userPurchases);
        return userPurchases;
    }

    public Boolean addReview(Integer userId, Integer productId, Double rating, String reviewText) {
        if (rating < 0 || rating > 5) return false;

        Product product = null;
        for (Product p : productRepository.findAll()) {
            if (p.getId().equals(productId)) {
                product = p;
                break;
            }
        }
        if (product == null) return false;

        Boolean hasPurchased = false;
        for (Purchase p : purchaseRepository.findAll()) {
            if (p.getUserId().equals(userId.toString()) &&
                    p.getProductId().equals(productId.toString()) &&
                    "Purchased".equals(p.getStatus())) {
                hasPurchased = true;
                break;
            }
        }
        if (!hasPurchased) return false;

        String userReviewIdentifier = "User-" + userId;
        if (product.getReviews() != null && product.getReviews().contains(userReviewIdentifier)) return false;

        String newReview = userReviewIdentifier + ": " + reviewText;
        String reviews = product.getReviews() == null ? "" : product.getReviews();
        product.setReviews(reviews + (reviews.isEmpty() ? "" : ",") + newReview);

        int totalReviews = reviews.isEmpty() ? 0 : reviews.split(",").length;
        Double newAverage = ((product.getRating() * totalReviews) + rating) / (totalReviews + 1);
        product.setRating(newAverage);

        productRepository.save(product);
        return true;
    }

    public List<Product> getMostAndLeastPurchasedProducts(Integer adminId) {
        User admin = getUserById(adminId);
        if (admin == null || !"Admin".equals(admin.getRole())) return null;

        Map<String, Integer> countMap = new HashMap<>();
        for (Purchase p : purchaseRepository.findAll()) {
            if ("Purchased".equals(p.getStatus())) {
                countMap.merge(p.getProductId(), 1, Integer::sum);
            }
        }

        String mostId = null, leastId = null;
        int max = 0, min = Integer.MAX_VALUE;

        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                mostId = entry.getKey();
            }
            if (entry.getValue() < min && entry.getValue() > 0) {
                min = entry.getValue();
                leastId = entry.getKey();
            }
        }

        List<Product> result = new ArrayList<>();
        for (Product p : productRepository.findAll()) {
            if (p.getId().toString().equals(mostId)) result.add(p);
            if (p.getId().toString().equals(leastId) && !p.getId().toString().equals(mostId)) result.add(p);
        }
        return result;
    }
}
