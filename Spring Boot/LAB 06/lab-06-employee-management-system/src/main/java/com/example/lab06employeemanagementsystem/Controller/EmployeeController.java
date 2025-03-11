package com.example.lab06employeemanagementsystem.Controller;

import com.example.lab06employeemanagementsystem.Api.ApiResponse;
import com.example.lab06employeemanagementsystem.Model.Employee;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    ArrayList<Employee> employees = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Employee> getEmployees() {
        return employees;
    }
    @PostMapping("/add")
    public ResponseEntity addEmployee(@RequestBody @Valid Employee employee, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        employees.add(employee);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Employee added successfully"));
    }
    @PutMapping("/update/{index}")
    public ResponseEntity updateEmployee(@PathVariable int index, @RequestBody @Valid Employee employee, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        employees.set(index, employee);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Employee updated successfully"));
    }
    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEmployee(@PathVariable int index) {
        if (index < 0 || index >= employees.size()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid index");
        }
        employees.remove(index);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Employee deleted successfully"));
    }
    @GetMapping("/search/{position}")
    public Object searchEmployeesByPosition(@PathVariable String position) {
        if (!(position.equalsIgnoreCase("supervisor"))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid position");
        }
        ArrayList<Employee> employeesByPosition = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getPosition().equalsIgnoreCase(position)) {
                employeesByPosition.add(employee);
            }
        }
        if (employeesByPosition.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No employees found");
        }
        return employeesByPosition;
    }
    @GetMapping("/get/{minAge}/{maxAge}")
    public Object getEmployeesByAgeRange(@PathVariable int minAge, @PathVariable int maxAge) {
        ArrayList<Employee> employeesByAgeRange = new ArrayList<>();
        if (minAge > maxAge || minAge < 26 || maxAge > 99) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid age range");
        }
        for (Employee employee : employees) {
            if (employee.getAge() >= minAge && employee.getAge() <= maxAge) {
                employeesByAgeRange.add(employee);
            }
        }
        return employeesByAgeRange;
    }
    @PutMapping("/updateAnnualLeave/{id}")
    public ResponseEntity employeeAnnualLeaveById(@PathVariable String id) {
        Employee e = null;
        for (Employee employee : employees) {
            if (employee.getId().equalsIgnoreCase(id)) {
                e = employee;
            }
        }
        if (e == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employee not found");
        }
        if ((!e.isOnLeave()) && e.getAnnualLeave()>0) {
            e.setAnnualLeave(e.getAnnualLeave()-1);
            e.setOnLeave(true);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Employee annual leave successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employee annual leave is less than 0 or is on leave");
    }
    @GetMapping("/getemployeeswithnoannualleave")
    public Object getEmployeesWithNoAnnualLeave() {
        if (employees.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employees list is empty");
        }
        ArrayList<Employee> employeesWithNoAnnualLeave = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getAnnualLeave()==0) {
                employeesWithNoAnnualLeave.add(employee);
            }
        }
        return employeesWithNoAnnualLeave;
    }
    @PutMapping("/promote/{supervisorId}/{employeeToBePromotedId}")
    public ResponseEntity promoteEmployee(@PathVariable String supervisorId, @PathVariable String employeeToBePromotedId) {
        if (employees.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employees list is empty");
        } else {
            if (supervisorId == null || employeeToBePromotedId == null || supervisorId.equalsIgnoreCase(employeeToBePromotedId)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employees id are not valid");
            } else {
                Employee superviosr = null;
                Employee employeeToBePromoted = null;
                for (Employee employee : employees) {
                    if (employee.getId().equalsIgnoreCase(supervisorId)) {
                        superviosr = employee;
                    }
                    if (employee.getId().equalsIgnoreCase(employeeToBePromotedId)) {
                        employeeToBePromoted = employee;
                    }
                }
                if (superviosr == null || employeeToBePromoted == null) {
                    return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("One of the ID is not valid"));
                }
                if (superviosr.getPosition().equalsIgnoreCase("supervisor") && !employeeToBePromoted.isOnLeave() && employeeToBePromoted.getAge() >= 30) {
                    employeeToBePromoted.setPosition("supervisor");
                    return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Employee promoted successfully"));
                }
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employee did not promote");
            }
        }
    }
}
