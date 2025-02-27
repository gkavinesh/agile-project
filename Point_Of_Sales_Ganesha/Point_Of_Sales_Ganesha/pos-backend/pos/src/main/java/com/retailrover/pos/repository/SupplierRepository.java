package com.retailrover.pos.repository;

import com.retailrover.pos.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SupplierRepository extends JpaRepository<Supplier,Long> {
}
