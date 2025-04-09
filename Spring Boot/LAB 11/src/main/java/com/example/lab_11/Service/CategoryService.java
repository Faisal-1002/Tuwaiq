package com.example.lab_11.Service;

import com.example.lab_11.Model.Category;
import com.example.lab_11.Repositrory.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public void addCategory(Category category){
        categoryRepository.save(category);
    }

    public Boolean updateCategory(Integer id, Category category){
        Category oldCategory = categoryRepository.findCategoryById(id);
        if (oldCategory == null)
            return false;
        oldCategory.setName(category.getName());
        categoryRepository.save(oldCategory);
        return true;
    }

    public Boolean deleteCategory(Integer id){
        Category category = categoryRepository.findCategoryById(id);
        if (category == null)
            return false;
        categoryRepository.delete(category);
        return true;
    }

}
