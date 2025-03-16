package com.example.exam02.Controller;

import com.example.exam02.Api.ApiResponse;
import com.example.exam02.Model.Book;
import com.example.exam02.Model.User;
import com.example.exam02.Service.BookService;
import com.example.exam02.Service.UserService;
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
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(userService);
    }
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (userService.addUser(user)){
            return ResponseEntity.status(200).body(new ApiResponse("added"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable String id, @RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (userService.updateUser(id, user)){
            return ResponseEntity.status(200).body(new ApiResponse("updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse(("not updated")));
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) {
        if (userService.deleteUser(id)){
            return ResponseEntity.status(200).body(new ApiResponse("Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not deleted"));
    }
    @GetMapping("/getaboveblalance/{balance}")
    public ResponseEntity getAboveBalance(@PathVariable double balance) {
        if (userService == null){
            return ResponseEntity.status(400).body(new ApiResponse("Null"));
        }
        return ResponseEntity.status(200).body(userService.printAboveBalance(balance));
    }

    @GetMapping("/getaboveage/{age}")
    public ResponseEntity getAboveAge(@PathVariable int age) {
        if (userService == null){
            return ResponseEntity.status(400).body(new ApiResponse("Null"));
        }
        return ResponseEntity.status(200).body(userService.printAboveAge(age));
    }



}
