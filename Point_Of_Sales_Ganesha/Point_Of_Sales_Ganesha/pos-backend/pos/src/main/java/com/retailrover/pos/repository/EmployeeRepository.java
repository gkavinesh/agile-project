package com.retailrover.pos.repository;

import com.retailrover.pos.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
