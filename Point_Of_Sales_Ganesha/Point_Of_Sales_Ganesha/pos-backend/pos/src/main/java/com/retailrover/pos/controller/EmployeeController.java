package com.retailrover.pos.controller;

import com.retailrover.pos.entity.Employee;
import com.retailrover.pos.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/employee")
    public Employee postemployee(@RequestBody Employee employee) {
        return employeeService.post(employee);
    }

    @GetMapping("/employee")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        try{
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>( "Employee with ID" + id + " deleted successfully", HttpStatus.OK);

        }catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<?> getEmployeeByID(@PathVariable Long id){
        Employee user = employeeService.getEmployeeByID(id);
        if(user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/employee/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        Employee updatedEmployee = employeeService.updateEmployee(id,employee);

        if(updatedEmployee == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(updatedEmployee);
    }





}
