package com.example.capstone01.Service;

import com.example.capstone01.Model.Category;
import com.example.capstone01.Model.MerchantStock;
import com.example.capstone01.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductService {

    ArrayList<Product> products = new ArrayList<>();
    private final CategoryService categoryService;

    public ArrayList<Product> getAllProducts() {
        return products;
    }

    public boolean addProduct(Product product) {
        for (Product product1 : products){
            if (product1.getId().equals(product.getId()))
                return false;
        }
        for (Category category1 : categoryService.getAllCategories()){
            if (category1.getId().equals(product.getCategoryId())){
                products.add(product);
                return true;
            }
        }
        return false;

    }

    public boolean updateProduct(String id, Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                Product existingProduct = products.get(i);

                product.setPurchaseDateTime(existingProduct.getPurchaseDateTime());

                existingProduct.setName(product.getName());
                existingProduct.setPrice(product.getPrice());
                existingProduct.setCategoryId(product.getCategoryId());

                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(String id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)){
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    public Product getProductById(String id) {
        for (Product product1 : products){
            if (product1.getId().equals(id)){
                return product1;
            }
        }
        return null;
    }

    public ArrayList<String> getReviews(String productId) {
        ArrayList<String> mergedReviews = new ArrayList<>();
        Product product = getProductById(productId);
        if (product == null) {
            return null;
        }
        double productRating = product.getRating();
        for (Product product1 : products) {
            if (product1.getId().equals(productId)) {
                for (int i = 0; i < product1.getReviews().size(); i++) {
                    String reviewText = product1.getReviews().get(i);
                    String reviewWithRating = productRating + " - " + reviewText;
                    mergedReviews.add(reviewWithRating);
                }
            }
        }
        if (mergedReviews.isEmpty()) {
            return null;
        }
        return mergedReviews;
    }
}
