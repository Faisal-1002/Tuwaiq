package com.example.capstone02.Controller;

import com.example.capstone02.Model.User;
import com.example.capstone02.Service.UserService;
import com.example.capstone02.Api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        Boolean success = userService.addUser(user);
        if (success)
            return ResponseEntity.status(200).body(new ApiResponse("User added successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Failed to add user"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        Boolean success = userService.updateUser(id, user);
        if (success)
            return ResponseEntity.status(200).body(new ApiResponse("User updated successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("User not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        Boolean success = userService.deleteUser(id);
        if (success)
            return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("User not found"));
    }

    @PutMapping("/subscribe/{userId}")
    public ResponseEntity subscribeUser(@PathVariable Integer userId) {
        Boolean subscribed = userService.subscribeUser(userId);
        if (subscribed)
            return ResponseEntity.status(200).body(new ApiResponse("User subscribed to premium successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("User not found or subscription failed"));
    }
}
