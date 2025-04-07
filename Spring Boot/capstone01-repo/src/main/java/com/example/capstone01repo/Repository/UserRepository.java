package com.example.capstone01repo.Repository;

import com.example.capstone01repo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
