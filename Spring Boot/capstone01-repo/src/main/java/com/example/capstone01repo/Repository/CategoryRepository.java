package com.example.capstone01repo.Repository;

import com.example.capstone01repo.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
