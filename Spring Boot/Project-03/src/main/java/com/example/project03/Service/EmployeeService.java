package com.example.project03.Service;

import com.example.project03.DTO_In.EmployeeDTO_in;
import com.example.project03.Model.Employee;
import com.example.project03.Model.User;
import com.example.project03.Repository.AuthRepository;
import com.example.project03.Repository.EmployeeRepository;
import com.example.project03.Api.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AuthRepository authRepository;

    // Authentication: Employee
    public Employee getEmployee(Integer employee_id) {
        Employee employee = employeeRepository.findEmployeeById(employee_id);
        if (employee == null)
            throw new ApiException("Employee not found");
        return employee;
    }

    // Authentication: Admin
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Authentication: Permit-All
    public void registerEmployee(EmployeeDTO_in employeeDTO_in) {
        employeeDTO_in.setRole("EMPLOYEE");
        String hashPassword = new BCryptPasswordEncoder().encode(employeeDTO_in.getPassword());
        User user = new User(null, employeeDTO_in.getUsername(), hashPassword, employeeDTO_in.getName(), employeeDTO_in.getEmail(), employeeDTO_in.getRole(), null,null);
        Employee employee = new Employee(null, employeeDTO_in.getPosition(), employeeDTO_in.getSalary(), user);
        authRepository.save(user);
        employeeRepository.save(employee);
    }

    // Authentication: Employee
    public void updateEmployeeProfile(Integer employeeId, EmployeeDTO_in employeeDTO_in) {
        Employee employee = employeeRepository.findEmployeeById(employeeId);
        if (employee == null) {
            throw new ApiException("Employee not found");
        }

        User user = employee.getUser();
        user.setName(employeeDTO_in.getName());
        user.setEmail(employeeDTO_in.getEmail());
        user.setUsername(employeeDTO_in.getUsername());

        String hashedPassword = new BCryptPasswordEncoder().encode(employeeDTO_in.getPassword());
        user.setPassword(hashedPassword);

        employee.setPosition(employeeDTO_in.getPosition());
        employee.setSalary(employeeDTO_in.getSalary());

        authRepository.save(user);
        employeeRepository.save(employee);
    }

    // Authentication: Admin
    public void deleteEmployee(Integer employeeId) {
        Employee employee = employeeRepository.findEmployeeById(employeeId);
        if (employee == null) {
            throw new ApiException("Employee not found");
        }

        User employeeUser = employee.getUser();
        authRepository.delete(employeeUser);
        employeeRepository.delete(employee);
    }

}
