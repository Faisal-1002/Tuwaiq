package com.example.capstone02.Controller;

import com.example.capstone02.Model.User;
import com.example.capstone02.Service.UserService;
import com.example.capstone02.Api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400)
                    .body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        Boolean success = userService.addUser(user);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("User added successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Failed to add user"));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Integer id, @Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400)
                    .body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        Boolean success = userService.updateUser(id, user);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("User updated successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("User not found"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id) {
        Boolean success = userService.deleteUser(id);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("User not found"));
        }
    }

    @PutMapping("/subscribe/{userId}")
    public ResponseEntity<ApiResponse> subscribeUser(@PathVariable Integer userId) {
        Boolean subscribed = userService.subscribeUser(userId);
        if (subscribed) {
            return ResponseEntity.status(200).body(new ApiResponse("User subscribed to premium successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("User not found or subscription failed"));
        }
    }

}
