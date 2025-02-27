package com.retailrover.pos.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import com.retailrover.pos.entity.Customer;
import com.retailrover.pos.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer post(Customer customer){

        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();

    }

    public void deleteCustomer(Long id){
        if(!customerRepository.existsById(id)){
            throw new EntityNotFoundException("Customer with ID " + id + " not found");
        }

        customerRepository.deleteById(id);
    }

    public Customer getCustomerByID(Long id){
        return customerRepository.findById(id).orElse(null);
    }

    public Customer updateCustomer(Long id,Customer customer){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isPresent()){
            Customer existingCustomer = optionalCustomer.get();

            existingCustomer.setFirstName(customer.getFirstName());
            existingCustomer.setLastName(customer.getLastName());
            existingCustomer.setEmail(customer.getEmail());

            return customerRepository.save(existingCustomer);

        }
        return null;
    }

}
