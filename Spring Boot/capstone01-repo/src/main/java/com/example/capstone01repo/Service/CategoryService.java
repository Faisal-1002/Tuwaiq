package com.example.capstone01repo.Service;

import com.example.capstone01repo.Model.Category;
import com.example.capstone01repo.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Boolean addCategory(Category category) {
        List<Category> categories = categoryRepository.findAll();
        for (Category existing : categories) {
            if (existing.getName().equalsIgnoreCase(category.getName())) {
                return false;
            }
        }
        categoryRepository.save(category);
        return true;
    }

    public Boolean updateCategory(Integer id, Category updatedCategory) {
        List<Category> categories = categoryRepository.findAll();
        for (Category existing : categories) {
            if (existing.getId().equals(id)) {
                updatedCategory.setId(id); // keep the same ID
                categoryRepository.save(updatedCategory);
                return true;
            }
        }
        return false;
    }

    public Boolean deleteCategory(Integer id) {
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories) {
            if (category.getId().equals(id)) {
                categoryRepository.delete(category);
                return true;
            }
        }
        return false;
    }

    public Category getCategoryById(Integer id) {
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories) {
            if (category.getId().equals(id)) {
                return category;
            }
        }
        return null;
    }
}