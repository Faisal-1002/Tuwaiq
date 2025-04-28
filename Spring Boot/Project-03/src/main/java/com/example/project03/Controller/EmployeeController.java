package com.example.project03.Controller;

import com.example.project03.DTO_In.EmployeeDTO_in;
import com.example.project03.Model.Employee;
import com.example.project03.Model.User;
import com.example.project03.Service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.example.project03.Api.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    // Authentication: Employee
    @GetMapping("/profile")
    public ResponseEntity<?> getEmployee(@AuthenticationPrincipal User user) {
        Employee employee = employeeService.getEmployee(user.getId());
        return ResponseEntity.status(200).body(employee);
    }

    // Authentication: Admin
    @GetMapping("/admin/all")
    public ResponseEntity<?> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.status(200).body(employees);
    }

    // Authentication: Permit-All
    @PostMapping("/register")
    public ResponseEntity<?> registerEmployee(@RequestBody EmployeeDTO_in employeeDTO_in) {
        employeeService.registerEmployee(employeeDTO_in);
        return ResponseEntity.status(200).body(new ApiResponse("Employee registered successfully"));
    }

    // Authentication: Employee
    @PutMapping("/update")
    public ResponseEntity<?> updateEmployeeProfile(@AuthenticationPrincipal User user, @RequestBody EmployeeDTO_in employeeDTO_in) {
        employeeService.updateEmployeeProfile(user.getId(), employeeDTO_in);
        return ResponseEntity.status(200).body(new ApiResponse("Profile updated successfully"));
    }

    // Authentication: Admin
    @DeleteMapping("/admin/delete/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.status(200).body(new ApiResponse("Employee deleted successfully"));
    }

}
