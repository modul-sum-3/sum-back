package com.fitness.fitnessBack.employee.repository;

import com.fitness.fitnessBack.empolyee.model.Employee;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,UUID> {
}
