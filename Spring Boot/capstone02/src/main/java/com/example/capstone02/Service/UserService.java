package com.example.capstone02.Service;

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

    //2. Subscribe the user to a membership
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
