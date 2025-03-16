package com.example.capstone01.Service;

import com.example.capstone01.Model.Category;
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
            if (product1.getId().equals(product.getId()) || product1.getCategoryId().equals(product.getCategoryId()))
                return false;
        }
        products.add(product);
        return true;
    }

    public boolean updateProduct(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(product.getId())){
                products.set(i, product);
                return true;
            }
        }
        return false;
    }

}
