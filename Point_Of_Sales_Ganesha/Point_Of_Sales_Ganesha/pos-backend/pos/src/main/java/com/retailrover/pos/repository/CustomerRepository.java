package com.retailrover.pos.repository;

import com.retailrover.pos.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
