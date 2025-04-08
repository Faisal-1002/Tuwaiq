package com.example.usermanagement.Service;

import com.example.usermanagement.Model.User;
import com.example.usermanagement.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public Boolean updateUser(Integer id, User user){
        User oldUser = userRepository.findUserById(id);
        if (oldUser == null)
            return false;
        oldUser.setName(user.getName());
        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        oldUser.setEmail(user.getEmail());
        oldUser.setRole(user.getRole());
        oldUser.setAge(user.getAge());
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

    public Boolean checkUsernameAndPassword(String username, String password){
        User user = userRepository.findUserByUsernameAndPassword(username, password);
        if (user == null)
            return false;
        return true;
    }

    public User getByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public List<User> getByRole(String role){
        return userRepository.getUsersByRole(role);
    }

    public List<User> getByAgeOrAbove(Integer age){
        return userRepository.getUsersByAgeAbove(age);
    }

}
