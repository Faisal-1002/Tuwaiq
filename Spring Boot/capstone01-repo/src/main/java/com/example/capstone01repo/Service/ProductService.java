package com.example.capstone01repo.Service;

import com.example.capstone01repo.Model.Category;
import com.example.capstone01repo.Model.Product;
import com.example.capstone01repo.Repository.CategoryRepository;
import com.example.capstone01repo.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Boolean addProduct(Product product) {
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories) {
            if (category.getId().equals(Integer.valueOf(product.getCategoryId()))) {
                productRepository.save(product);
                return true;
            }
        }
        return false;
    }

    public Boolean updateProduct(Integer id, Product updatedProduct) {
        List<Product> products = productRepository.findAll();
        for (Product existingProduct : products) {
            if (existingProduct.getId().equals(id)) {
                updatedProduct.setId(id); // preserve the ID
                updatedProduct.setPurchaseDateTime(existingProduct.getPurchaseDateTime()); // preserve purchase date
                updatedProduct.setStatus(existingProduct.getStatus());
                updatedProduct.setReviews(existingProduct.getReviews());
                updatedProduct.setRating(existingProduct.getRating());

                productRepository.save(updatedProduct);
                return true;
            }
        }
        return false;
    }

    public Boolean deleteProduct(Integer id) {
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            if (product.getId().equals(id)) {
                productRepository.delete(product);
                return true;
            }
        }
        return false;
    }

    public Product getProductById(Integer id) {
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public List<String> getReviews(Integer productId) {
        Product product = getProductById(productId);
        if (product == null || product.getReviews() == null) return null;

        List<String> reviewList = new ArrayList<>();
        Double rating = product.getRating();
        String[] reviewArray = product.getReviews().split(",");
        for (String review : reviewArray) {
            reviewList.add(rating + " - " + review.trim());
        }

        return reviewList.isEmpty() ? null : reviewList;
    }
}
