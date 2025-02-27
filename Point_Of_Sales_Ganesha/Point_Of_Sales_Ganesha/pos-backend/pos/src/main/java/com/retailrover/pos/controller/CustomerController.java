package com.retailrover.pos.controller;

import com.retailrover.pos.entity.Customer;
import com.retailrover.pos.service.CustomerService;
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
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/customer")
    public Customer postcustomer(@RequestBody Customer customer) {
        return customerService.post(customer);
    }

    @GetMapping("/customer")
    public List<Customer> getAllCustomers(){

        return customerService.getAllCustomers();
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
        try{
            customerService.deleteCustomer(id);
            return new ResponseEntity<>( "Customer with ID" + id + " deleted successfully", HttpStatus.OK);

        }catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getCustomerbyID(@PathVariable Long id){
        Customer customer = customerService.getCustomerByID(id);
        if(customer == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(customer);
    }

    @PatchMapping("/customer/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
        Customer updatedCustomer = customerService.updateCustomer(id,customer);

        if(updatedCustomer == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(updatedCustomer);
    }





}
