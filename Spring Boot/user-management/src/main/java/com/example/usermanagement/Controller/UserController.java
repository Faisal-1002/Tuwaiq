package com.example.usermanagement.Controller;

import com.example.usermanagement.Api.ApiResponse;
import com.example.usermanagement.Model.User;
import com.example.usermanagement.Service.UserService;
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
        return ResponseEntity.status(200).body(new ApiResponse("added"));
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

    @GetMapping("/check/{username}/{password}")
    public ResponseEntity checkUsernameAndPassword(@PathVariable String username, @PathVariable String password){
        if (userService.checkUsernameAndPassword(username, password))
            return ResponseEntity.status(200).body(new ApiResponse("Username and password are correct"));
        return ResponseEntity.status(400).body(new ApiResponse("Username or password are not correct !!"));
    }

    @GetMapping("/get-by-email/{email}")
    public ResponseEntity getByEmail(@PathVariable String email){
        return ResponseEntity.status(200).body(userService.getByEmail(email));
    }

    @GetMapping("/get-by-role/{role}")
    public ResponseEntity getByRole(@PathVariable String role){
        return ResponseEntity.status(200).body(userService.getByRole(role));
    }

    @GetMapping("/get-by-age-or-above/{age}")
    public ResponseEntity getByAgeOrAbove(@PathVariable Integer age){
        return ResponseEntity.status(200).body(userService.getByAgeOrAbove(age));
    }
}
