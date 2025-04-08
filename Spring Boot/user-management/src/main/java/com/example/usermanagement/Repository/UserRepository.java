package com.example.usermanagement.Repository;

import com.example.usermanagement.Model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(Integer id);
    User findUserByUsername(String username);
    User findUserByUsernameAndPassword(String username, String password);
    User findUserByEmail(String email);

    @Query("select u from User u where u.role=?1")
    List<User> getUsersByRole(String role);
    @Query("select u from User u where u.age>=?1")
    List<User> getUsersByAgeAbove(Integer age);

}
