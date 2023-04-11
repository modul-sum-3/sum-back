package com.fitness.fitnessBack.empolyee.service;


import com.fitness.fitnessBack.empolyee.model.Employee;
import com.fitness.fitnessBack.empolyee.repository.EmployeeRepository;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Value
public class EmployeeService {
    EmployeeRepository employeeRepository;

    public List<Employee> getAll() {
        List<Employee> result = new ArrayList<>();
        result.addAll(employeeRepository.findAll());

        return result;
    }
    public Employee getOne(Long id) {
        return employeeRepository.findById(id).orElseThrow();
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee deleteEmployee(Long id){
        Employee result = employeeRepository.findById(id).orElseThrow();
        employeeRepository.delete(result);
        return result;
    }
}
