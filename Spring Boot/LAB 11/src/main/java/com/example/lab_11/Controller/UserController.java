package com.example.lab_11.Controller;

import com.example.lab_11.Api.ApiResponse;
import com.example.lab_11.Model.User;
import com.example.lab_11.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
        return ResponseEntity.status(200).body(new ApiResponse("Added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody User user, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        if (userService.updateUser(id, user))
            return ResponseEntity.status(200).body(new ApiResponse("updated"));
        return ResponseEntity.status(400).body(new ApiResponse("not updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        if (userService.deleteUser(id))
            return ResponseEntity.status(200).body(new ApiResponse("deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("not deleted"));
    }

    @GetMapping("/get-by-date/{date}")
    public ResponseEntity getAllUsersByRegistrationDate(@PathVariable LocalDate date){
        return ResponseEntity.status(200).body(userService.getAllUsersByRegistrationDate(date));
    }

    @GetMapping("/get-by-password/{threshold}")
    public ResponseEntity findByPasswordLengthLessThan(@PathVariable Integer threshold){
        return ResponseEntity.status(200).body(userService.findByPasswordLengthLessThan(threshold));
    }

}
