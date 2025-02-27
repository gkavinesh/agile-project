package com.retailrover.pos.repository;

import com.retailrover.pos.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InventoryRepository extends JpaRepository<Inventory,Long> {
}
