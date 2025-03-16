package com.example.capstone01.Service;

import com.example.capstone01.Model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {

    ArrayList<Category> categories = new ArrayList<>();

    public ArrayList<Category> getAllCategories() {
        return categories;
    }

}
