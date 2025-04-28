package com.example.project03.Controller;

import com.example.project03.Api.ApiResponse;
import com.example.project03.Model.User;
import com.example.project03.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.status(200).body(authService.getAllUsers());
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid User user){
        authService.registerAdmin(user);
        return ResponseEntity.status(200).body(new ApiResponse("User has been register"));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@AuthenticationPrincipal User user, @Valid @RequestBody User myUser){
        authService.updateAdmin(user.getId(), myUser);
        return ResponseEntity.status(200).body(new ApiResponse("User has been updated"));
    }

    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer user_id){
        authService.deleteAdmin(user_id);
        return ResponseEntity.status(200).body(new ApiResponse("Deleted"));
    }
}
