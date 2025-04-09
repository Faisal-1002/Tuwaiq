package com.example.lab_11.Service;

import com.example.lab_11.Model.User;
import com.example.lab_11.Repositrory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        user.setRegistration_date(LocalDate.now());
        userRepository.save(user);
    }

    public Boolean updateUser(Integer id, User user){
        User oldUser = userRepository.findUserById(id);
        if (oldUser == null)
            return false;
        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        oldUser.setEmail(user.getEmail());
        userRepository.save(oldUser);
        return true;
    }

    public Boolean deleteUser(Integer id){
        User user = userRepository.findUserById(id);
        if (user == null)
            return false;
        userRepository.delete(user);
        return true;
    }

    public List<User> getAllUsersByRegistrationDate(LocalDate date){
        return userRepository.getAllUsersByRegistrationDate(date);
    }

    public List<User> findByPasswordLengthLessThan(Integer threshold){
        return userRepository.findByPasswordLengthLessThan(threshold);
    }

}
