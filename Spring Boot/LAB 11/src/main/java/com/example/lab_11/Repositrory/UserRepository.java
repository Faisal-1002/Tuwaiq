package com.example.lab_11.Repositrory;

import com.example.lab_11.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(Integer id);
    @Query("select u from User u where u.registration_date=?1")
    List<User> getAllUsersByRegistrationDate(LocalDate date);
    @Query("select u from User u where length(u.password) < ?1 ")
    List<User> findByPasswordLengthLessThan(Integer threshold);


}
