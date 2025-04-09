package com.example.lab_11.Repositrory;

import com.example.lab_11.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findCategoryById(Integer id);
    
}
