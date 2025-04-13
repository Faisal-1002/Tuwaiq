package com.example.capstone02.Service;

import com.example.capstone02.Model.User;
import com.example.capstone02.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Boolean addUser(User user) {
        userRepository.save(user);
        return true;
    }

    public Boolean updateUser(Integer id, User updatedUser) {
        User oldUser = userRepository.findUserById(id);
        if (oldUser == null) {
            return false;
        }
        oldUser.setName(updatedUser.getName());
        oldUser.setEmail(updatedUser.getEmail());
        oldUser.setPhone_number(updatedUser.getPhone_number());
        oldUser.setRole(updatedUser.getRole());
        userRepository.save(oldUser);
        return true;
    }

    public Boolean deleteUser(Integer id) {
        User user = userRepository.findUserById(id);
        if (user == null) {
            return false;
        }
        userRepository.delete(user);
        return true;
    }

    public Boolean subscribeUser(Integer userId) {
        User user = userRepository.findUserById(userId);
        if (user == null)
            return false;
        if (user.getMembership().equalsIgnoreCase("premium"))
            return false;
        user.setMembership("premium");
        user.setSubscription_date(LocalDate.now());
        userRepository.save(user);
        return true;
    }
}
