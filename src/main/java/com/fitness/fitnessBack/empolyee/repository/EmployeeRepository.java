package com.fitness.fitnessBack.empolyee.repository;

import com.fitness.fitnessBack.empolyee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
