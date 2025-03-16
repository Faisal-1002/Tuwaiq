package com.example.exam02.Service;

import com.example.exam02.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getAllUsers() {
        return users;
    }

    public boolean addUser(User user) {
        for (User user1 : users){
            if (user1.getId().equals(user.getId()))
                return false;
        }
        users.add(user);
        return true;
    }

    public boolean updateUser(String id, User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)){
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(String id) {
        for (User user1 : users) {
            if (user1.getId().equals(id)) {
                users.remove(user1);
                return true;
            }
        }
        return false;
    }

    public ArrayList<User> printAboveBalance(double balance) {
        ArrayList<User> aboveBalance = new ArrayList<>();
        for (User user1 : users){
            if (user1.getBalance()>=balance)
                aboveBalance.add(user1);
        }
        return aboveBalance;
    }

    public ArrayList<User> printAboveAge(int age) {
        ArrayList<User> aboveAge = new ArrayList<>();
        for (User user1 : users){
            if (user1.getAge()>=age)
                aboveAge.add(user1);
        }
        return aboveAge;
    }

}
