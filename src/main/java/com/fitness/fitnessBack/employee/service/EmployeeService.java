package com.fitness.fitnessBack.employee.service;


import com.fitness.fitnessBack.employee.model.Employee;
import com.fitness.fitnessBack.employee.model.EmployeePass;
import com.fitness.fitnessBack.employee.repository.EmployeeRepository;
import com.fitness.fitnessBack.user.model.Role;
import com.fitness.fitnessBack.user.model.User;
import com.fitness.fitnessBack.user.repository.UserRepository;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@Value
public class EmployeeService {
    EmployeeRepository employeeRepository;
    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    public List<Employee> getAll() {
        List<Employee> result = new ArrayList<>();
        result.addAll(employeeRepository.findAll());
        return result;
    }
    public Employee getOne(Long id) {
        return employeeRepository.findById(id).orElseThrow();
    }

    public Employee saveEmployee(EmployeePass employee) {
        if(userRepository.findByEmail(employee.getEmployee().getEmail()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Already Exists!");
        }
        Employee saved = employeeRepository.save(employee.getEmployee());
        User user = User.builder()
                .id(saved.getId())
                .role(Role.EMPLOYEE)
                .password(passwordEncoder.encode(employee.getPassword()))
                .email(employee.getEmployee().getEmail())
                .build();
        userRepository.save(user);
        return saved;
    }

    public Employee deleteEmployee(Long id){
        Employee result = employeeRepository.findById(id).orElseThrow();
        employeeRepository.delete(result);
        return result;
    }
}
