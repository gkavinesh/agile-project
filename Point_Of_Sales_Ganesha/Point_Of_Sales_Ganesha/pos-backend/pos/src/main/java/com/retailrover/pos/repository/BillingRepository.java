package com.retailrover.pos.repository;

import com.retailrover.pos.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BillingRepository extends JpaRepository<Billing,Long> {
}
