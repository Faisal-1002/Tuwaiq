package com.example.springsecurity.Service;

import com.example.springsecurity.Api.ApiException;
import com.example.springsecurity.Model.User;
import com.example.springsecurity.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    public List<User> getAllUser(){
        return authRepository.findAll();
    }

    public void register(User user){
        user.setRole("USER");
        String hashPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashPassword);
        authRepository.save(user);
    }
    public void updateUser(Integer user_id, User user){
        User updatedUser = authRepository.findUserById(user_id);
        if (updatedUser == null)
            throw new ApiException("User not found");
        String hashPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        updatedUser.setUsername(user.getUsername());
        updatedUser.setPassword(hashPassword);
        authRepository.save(updatedUser);
    }

    public void deleteUser(Integer id){
        User user = authRepository.findUserById(id);
        if (user == null)
            throw new ApiException("User not found");
        authRepository.delete(user);
    }

}
