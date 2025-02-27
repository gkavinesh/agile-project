package com.retailrover.pos.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import com.retailrover.pos.entity.Employee;
import com.retailrover.pos.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee post(Employee employee){

        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();

    }

    public void deleteEmployee(Long id){
        if(!employeeRepository.existsById(id)){
            throw new EntityNotFoundException("Employee with ID " + id + " not found");
        }

        employeeRepository.deleteById(id);
    }

    public Employee getEmployeeByID(Long id){
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee updateEmployee(Long id,Employee user){
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isPresent()){
            Employee existingEmployee = optionalEmployee.get();

            existingEmployee.setFirstName(user.getFirstName());
            existingEmployee.setLastName(user.getLastName());
            existingEmployee.setSalary(user.getSalary());

            return employeeRepository.save(existingEmployee);

        }
        return null;
    }

}
