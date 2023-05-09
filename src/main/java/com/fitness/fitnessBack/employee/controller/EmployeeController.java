package com.fitness.fitnessBack.employee.controller;

import com.fitness.fitnessBack.employee.model.Employee;
import com.fitness.fitnessBack.employee.model.EmployeePass;
import com.fitness.fitnessBack.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Value
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value="/employee", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {
    EmployeeService employeeService;

    @GetMapping("/secure")
    public String set() {
        return "Congratulation secured endpoint";
    }
    @GetMapping
    public List<Employee> findAll() {
        return employeeService.getAll();
    }
    @GetMapping("/{id}")
    public Employee findOne(@PathVariable(value = "id") UUID id) {
        return employeeService.getOne(id);
    }
    @PostMapping
    public Employee saveEmployee(@Valid @RequestBody EmployeePass employee) {
        return employeeService.saveEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public Employee deleteEmployee(@PathVariable(value = "id") UUID id) {
        return employeeService.deleteEmployee(id);
    }
}
