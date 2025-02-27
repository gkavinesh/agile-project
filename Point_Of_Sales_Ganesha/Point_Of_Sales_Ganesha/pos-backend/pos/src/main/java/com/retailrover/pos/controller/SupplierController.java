package com.retailrover.pos.controller;

import com.retailrover.pos.entity.Supplier;
import com.retailrover.pos.service.SupplierService;
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
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping("/supplier")
    public Supplier postsupplier(@RequestBody Supplier supplier) {
        return supplierService.post(supplier);
    }

    @GetMapping("/supplier")
    public List<Supplier> getAllSuppliers() {

        return supplierService.getAllSuppliers();
    }

    @DeleteMapping("/supplier/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable Long id) {
        try {
            supplierService.deleteSuppliers(id);
            return new ResponseEntity<>("Supplier with ID" + id + " deleted successfully", HttpStatus.OK);

        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/supplier/{id}")
    public ResponseEntity<?> getSupplierbyID(@PathVariable Long id) {
        Supplier supplier = supplierService.getSupplierByID(id);
        if (supplier == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(supplier);
    }

    @PatchMapping("/supplier/{id}")
    public ResponseEntity<?> updateSupplier(@PathVariable Long id, @RequestBody Supplier supplier) {
        Supplier updatedSupplier = supplierService.updateSupplier(id, supplier);

        if (updatedSupplier == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(updatedSupplier);
    }
}
