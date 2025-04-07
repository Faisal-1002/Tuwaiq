package com.example.capstone01repo.Controller;

import com.example.capstone01repo.Api.ApiResponse;
import com.example.capstone01repo.Model.User;
import com.example.capstone01repo.Service.UserService;
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
        if(Boolean.TRUE.equals(userService.addUser(user))){
            return ResponseEntity.status(200).body(new ApiResponse("User added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User could not be added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if(Boolean.TRUE.equals(userService.updateUser(id, user))){
            return ResponseEntity.status(200).body(new ApiResponse("User updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User could not be updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        if(Boolean.TRUE.equals(userService.deleteUser(id))){
            return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User could not be deleted"));
    }

    @GetMapping("/searchbyid/{id}")
    public ResponseEntity searchUserById(@PathVariable Integer id) {
        if (userService.getUserById(id) == null) {
            return ResponseEntity.status(400).body(new ApiResponse("User could not be found"));
        }
        return ResponseEntity.status(200).body(userService.getUserById(id));
    }

    @PutMapping("/buyproduct/{userId}/{productId}/{merchantId}")
    public ResponseEntity buyProduct(@PathVariable Integer userId, @PathVariable Integer productId, @PathVariable Integer merchantId){
        if(Boolean.TRUE.equals(userService.buyProduct(userId, productId, merchantId))){
            return ResponseEntity.status(200).body(new ApiResponse("Product has been purchased successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Product could not be purchased"));
    }

    @PutMapping("/refundproduct/{userId}/{productId}/{merchantId}")
    public ResponseEntity refundProduct(@PathVariable Integer userId, @PathVariable Integer productId, @PathVariable Integer merchantId){
        if(Boolean.TRUE.equals(userService.refundProduct(userId, productId, merchantId))){
            return ResponseEntity.status(200).body(new ApiResponse("Product has been refunded successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Product could not be refunded"));
    }

    @GetMapping("/orderhistory/{id}/{ascending}")
    public ResponseEntity orderHistory(@PathVariable Integer id, @PathVariable Boolean ascending){
        if (userService.getUserOrderHistory(id, ascending) == null) {
            return ResponseEntity.status(400).body(new ApiResponse("Order history not found"));
        }
        return ResponseEntity.status(200).body(userService.getUserOrderHistory(id, ascending));
    }

    @PostMapping("/addreview/{userId}/{productId}/{rating}/{review}")
    public ResponseEntity addReview(@PathVariable Integer userId, @PathVariable Integer productId,
                                    @PathVariable Double rating, @PathVariable String review){
        if(Boolean.TRUE.equals(userService.addReview(userId, productId, rating, review))){
            return ResponseEntity.status(200).body(new ApiResponse("Review added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Review could not be added"));
    }

    @GetMapping("/getpurchasestatics/{id}")
    public ResponseEntity getAllPurchaseStatics(@PathVariable Integer id){
        if (userService.getMostAndLeastPurchasedProducts(id) == null) {
            return ResponseEntity.status(400).body(new ApiResponse("No purchase statistics found"));
        }
        return ResponseEntity.status(200).body(userService.getMostAndLeastPurchasedProducts(id));
    }
}
