package com.example.project03.Service;

import com.example.project03.DTO_In.CustomerDTO_in;
import com.example.project03.Model.Customer;
import com.example.project03.Model.User;
import com.example.project03.Repository.AuthRepository;
import com.example.project03.Repository.CustomerRepository;
import com.example.project03.Api.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;

    // Authentication: Customer
    public Customer getCustomer(Integer customerId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        return customer;
    }

    // Authentication: Admin
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Authentication: Permit-All
    public void registerCustomer(CustomerDTO_in customerDTO_in) {
        customerDTO_in.setRole("CUSTOMER");
        String hashPassword = new BCryptPasswordEncoder().encode(customerDTO_in.getPassword());
        User user = new User(null, customerDTO_in.getUsername(), hashPassword, customerDTO_in.getName(), customerDTO_in.getEmail(), customerDTO_in.getRole(), null,null);
        Customer customer = new Customer(null, customerDTO_in.getPhone_number(), user, null);
        authRepository.save(user);
        customerRepository.save(customer);
    }

    // Authentication: Customer
    public void updateCustomerProfile(Integer customerId, CustomerDTO_in customerDTO_in) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }

        User user = customer.getUser();
        user.setName(customerDTO_in.getName());
        user.setEmail(customerDTO_in.getEmail());
        user.setUsername(customerDTO_in.getUsername());

        String hashedPassword = new BCryptPasswordEncoder().encode(customerDTO_in.getPassword());
        user.setPassword(hashedPassword);

        customer.setPhone_number(customerDTO_in.getPhone_number());

        authRepository.save(user);
        customerRepository.save(customer);
    }

    // Authentication: Admin
    public void deleteCustomer(Integer customerId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }

        User customerUser = customer.getUser();
        authRepository.delete(customerUser);
        customerRepository.delete(customer);
    }
}
