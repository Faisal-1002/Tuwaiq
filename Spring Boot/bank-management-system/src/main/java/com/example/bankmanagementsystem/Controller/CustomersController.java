package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.Api.ApiResponse;
import com.example.bankmanagementsystem.Model.Customers;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomersController {

    ArrayList<Customers> customers = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Customers> getCustomers() {
        return customers;
    }
    @PostMapping("/add")
    public ApiResponse addCustomer(@RequestBody Customers customers) {
        this.customers.add(customers);
        return new ApiResponse("Customer added successfully");
    }
    @PutMapping("/update/{index}")
    public ApiResponse updateCustomer(@PathVariable int index, @RequestBody Customers customers) {
        this.customers.set(index, customers);
        return new ApiResponse("Customer updated successfully");
    }
    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteCustomer(@PathVariable int index) {
        this.customers.remove(index);
        return new ApiResponse("Customer deleted successfully");
    }
    @PutMapping("/deposit/{index}/{amount}")
    public ApiResponse deposit(@PathVariable int index, @PathVariable double amount) {
        customers.get(index).setBalance(customers.get(index).getBalance() + amount);
        return new ApiResponse("Customer deposit successfully");
    }
    @PutMapping("/withdraw/{index}/{amount}")
    public ApiResponse withdraw(@PathVariable int index, @PathVariable double amount) {
        if (customers.get(index).getBalance() >= amount) {
            customers.get(index).setBalance(customers.get(index).getBalance() - amount);
            return new ApiResponse("Customer withdrawal successfully");
        } else {
            return new ApiResponse("Customer withdrawal failed, insufficient balance");
        }
    }
}
