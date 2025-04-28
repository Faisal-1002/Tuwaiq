package com.example.project03.Controller;

import com.example.project03.DTO_In.CustomerDTO_in;
import com.example.project03.Model.Customer;
import com.example.project03.Model.User;
import com.example.project03.Service.CustomerService;
import com.example.project03.Api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // Authentication: Customer
    @GetMapping("/profile")
    public ResponseEntity<?> getCustomerProfile(@AuthenticationPrincipal User user) {
        Customer customer = customerService.getCustomer(user.getId());
        return ResponseEntity.status(200).body(customer);
    }

    // Authentication: Admin
    @GetMapping("/admin/all")
    public ResponseEntity<?> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.status(200).body(customers);
    }

    // Authentication: Permit-All
    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody CustomerDTO_in customerDTO_in) {
        customerService.registerCustomer(customerDTO_in);
        return ResponseEntity.status(200).body(new ApiResponse("Customer registered successfully"));
    }

    // Authentication: Customer
    @PutMapping("/update")
    public ResponseEntity<?> updateCustomerProfile(@AuthenticationPrincipal User user, @RequestBody CustomerDTO_in customerDTO_in) {
        customerService.updateCustomerProfile(user.getId(), customerDTO_in);
        return ResponseEntity.status(200).body(new ApiResponse("Profile updated successfully"));
    }

    // Authentication: Admin
    @DeleteMapping("/admin/delete/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.status(200).body(new ApiResponse("Customer deleted successfully"));
    }
}
