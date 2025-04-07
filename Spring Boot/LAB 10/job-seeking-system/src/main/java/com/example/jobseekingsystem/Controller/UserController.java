package com.example.jobseekingsystem.Controller;

import com.example.jobseekingsystem.Api.ApiResponse;
import com.example.jobseekingsystem.Model.User;
import com.example.jobseekingsystem.Service.UserService;
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
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("user added"));
    }

    @PutMapping("/update/{id}")
    private ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody User user, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        if (userService.updateUser(id, user))
            return ResponseEntity.status(200).body(new ApiResponse("user updated"));
        return ResponseEntity.status(400).body(new ApiResponse("user not updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        if (userService.deleteUser(id))
            return ResponseEntity.status(200).body(new ApiResponse("user deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("user not deleted"));
    }

}
