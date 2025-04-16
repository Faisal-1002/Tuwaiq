package com.example.capstone02.Service;

import com.example.capstone02.Api.ApiException;
import com.example.capstone02.Model.User;
import com.example.capstone02.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
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

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(Integer id, User updatedUser) {
        User oldUser = userRepository.findUserById(id);
        if (oldUser == null) {
            throw new ApiException("User not found");
        }
        oldUser.setName(updatedUser.getName());
        oldUser.setEmail(updatedUser.getEmail());
        oldUser.setPhone_number(updatedUser.getPhone_number());
        oldUser.setRole(updatedUser.getRole());
        userRepository.save(oldUser);
    }

    public void deleteUser(Integer id) {
        User user = userRepository.findUserById(id);
        if (user == null) {
            throw new ApiException("User not found");
        }
        userRepository.delete(user);
    }

    //2. Subscribe the user to a membership
    public void subscribeUser(Integer userId) {
        User user = userRepository.findUserById(userId);
        if (user == null)
            throw new ApiException("User not found");
        if (user.getMembership().equalsIgnoreCase("premium"))
            throw new ApiException("Premium membership is required");
        user.setMembership("premium");
        user.setSubscription_date(LocalDate.now());
        userRepository.save(user);
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void expireSubscriptions() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if ("premium".equalsIgnoreCase(user.getMembership()) &&
                    user.getSubscription_date() != null &&
                    user.getSubscription_date().plusMonths(1).isBefore(LocalDate.now())) {

                user.setMembership("normal");
                user.setSubscription_date(null);
                userRepository.save(user);
            }
        }
    }

}
