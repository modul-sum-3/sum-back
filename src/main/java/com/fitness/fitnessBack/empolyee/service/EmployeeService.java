package com.fitness.fitnessBack.empolyee.service;




import com.fitness.fitnessBack.employee.model.Employee;
import com.fitness.fitnessBack.employee.repository.EmployeeRepository;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Value
public class EmployeeService {
    EmployeeRepository employeeRepository;

    public List<Employee> getAll() {
        List<Employee> result = new ArrayList<>();
        result.addAll(employeeRepository.findAll());

        return result;
    }
    public Employee getOne(UUID id) {
        return employeeRepository.findById(id).orElseThrow();
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee deleteEmployee(UUID id){
        Employee result = employeeRepository.findById(id).orElseThrow();
        employeeRepository.delete(result);
        return result;
    }
}
