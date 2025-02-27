package com.retailrover.pos.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import com.retailrover.pos.entity.Inventory;
import com.retailrover.pos.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public Inventory post(Inventory inventory){

        return inventoryRepository.save(inventory);
    }

    public List<Inventory> getAllInventoryItems(){
        return inventoryRepository.findAll();

    }

    public void deleteInventoryItem(Long id){
        if(!inventoryRepository.existsById(id)){
            throw new EntityNotFoundException("Inventory Item with ID " + id + " not found");
        }

        inventoryRepository.deleteById(id);
    }

    public Inventory getInventoryItemByID(Long id){
        return inventoryRepository.findById(id).orElse(null);
    }

    public Inventory updateInventory(Long id,Inventory inventory){
        Optional<Inventory> optionalInventory = inventoryRepository.findById(id);
        if(optionalInventory.isPresent()){
            Inventory existingInventory = optionalInventory.get();

            existingInventory.setName(inventory.getName());
            existingInventory.setStockavailable(inventory.getStockavailable());
            existingInventory.setPrice(inventory.getPrice());

            return inventoryRepository.save(existingInventory);

        }
        return null;
    }

}
