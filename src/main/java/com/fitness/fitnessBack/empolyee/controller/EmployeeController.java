package com.fitness.fitnessBack.empolyee.controller;

import com.fitness.fitnessBack.empolyee.model.Employee;
import com.fitness.fitnessBack.empolyee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Value
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value="/employee", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {
    EmployeeService employeeService;

    @GetMapping
    public List<Employee> findAll() {
        return employeeService.getAll();
    }
    @GetMapping("/{id}")
    public Employee findOne(@PathVariable(value = "id") Long id) {
        return employeeService.getOne(id);
    }
    @PostMapping
    public Employee saveEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public Employee deleteEmployee(@PathVariable(value = "id") Long id) {
        return employeeService.deleteEmployee(id);
    }
}
