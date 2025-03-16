package com.example.capstone01.Controller;

import com.example.capstone01.Api.ApiResponse;
import com.example.capstone01.Model.User;
import com.example.capstone01.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.status(200).body(userService.getUsers());
    }
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if(userService.addUser(user)){
            return ResponseEntity.status(200).body(new ApiResponse("User added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User could not be added"));
    }
    @PutMapping("/update/{id}")
    public  ResponseEntity updateUser(@PathVariable String id, @RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if(userService.updateUser(id, user)){
            return ResponseEntity.status(200).body(new ApiResponse("User updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User could not be updated"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable String id){
        if (userService.deleteUser(id))
            return ResponseEntity.status(200).body(new ApiResponse("User Deleted successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("User could not be deleted"));
    }
}
