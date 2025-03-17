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
        return ResponseEntity.status(200).body(userService.getAllUsers());
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
    @GetMapping("/searchbyid/{id}")
    public ResponseEntity searchUserById(@PathVariable String id) {
        if (userService.getUserById(id) == null)
            return ResponseEntity.status(400).body(new ApiResponse("User could not be found"));
        return ResponseEntity.status(200).body(userService.getUserById(id));
    }
    @PutMapping("/buyproduct/{userId}/{productId}/{merchantId}")
    public ResponseEntity buyProduct(@PathVariable String userId, @PathVariable String productId, @PathVariable String merchantId){
        if (userService.buyProduct(userId, productId, merchantId))
            return ResponseEntity.status(200).body(new ApiResponse("Product has been purchased successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Product could not be purchased"));
    }
    @PutMapping("/refundproduct/{userId}/{productId}/{merchantId}")
    public ResponseEntity refundProduct(@PathVariable String userId, @PathVariable String productId, @PathVariable String merchantId){
        if (userService.refundProduct(userId, productId, merchantId))
            return ResponseEntity.status(200).body(new ApiResponse("Product has been refunded successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Product could not be refunded"));
    }
    @GetMapping("/orderhistory/{id}/{ascending}")
    public ResponseEntity orderHistory(@PathVariable String id, @PathVariable boolean ascending){
        if (userService.getUserOrderHistory(id, ascending) == null)
            return ResponseEntity.status(400).body(new ApiResponse("Order History could not be found"));
        return ResponseEntity.status(200).body(userService.getUserOrderHistory(id, ascending));
    }
    @PostMapping("/addreview/{userId}/{productId}/{rating}/{review}")
    public ResponseEntity addReview(@PathVariable String userId, @PathVariable String productId, @PathVariable double rating, @PathVariable String review){
        if (userService.addReview(userId, productId, rating, review))
            return ResponseEntity.status(200).body(new ApiResponse("Review added successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Review could not be added"));
    }
}
