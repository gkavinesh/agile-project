package com.retailrover.pos.controller;

import com.retailrover.pos.entity.Inventory;
import com.retailrover.pos.service.InventoryService;
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
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/inventory")
    public Inventory postinventory(@RequestBody Inventory inventory) {
        return inventoryService.post(inventory);
    }

    @GetMapping("/inventory")
    public List<Inventory> getAllInventoryItems() {

        return inventoryService.getAllInventoryItems();
    }

    @DeleteMapping("/inventory/{id}")
    public ResponseEntity<?> deleteInventory(@PathVariable Long id) {
        try {
            inventoryService.deleteInventoryItem(id);
            return new ResponseEntity<>("Inventory Item with ID" + id + " deleted successfully", HttpStatus.OK);

        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/inventory/{id}")
    public ResponseEntity<?> getInventoryItemByID(@PathVariable Long id) {
        Inventory inventory = inventoryService.getInventoryItemByID(id);
        if (inventory == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(inventory);
    }

    @PatchMapping("/inventory/{id}")
    public ResponseEntity<?> updateInventory(@PathVariable Long id, @RequestBody Inventory inventory) {
        Inventory updatedInventory = inventoryService.updateInventory(id, inventory);

        if (updatedInventory == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(updatedInventory);
    }
}
