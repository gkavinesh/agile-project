package com.retailrover.pos.controller;

import com.retailrover.pos.entity.Billing;
import com.retailrover.pos.service.BillingService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class BillingController {

    private final BillingService billingService;

    public BillingController(BillingService billingService) {
        this.billingService = billingService; // Initialization via constructor
    }

    @PostMapping("/billing")
    public Billing postBilling(@RequestBody Billing billing) {
        return billingService.post(billing);
    }

    @GetMapping("/billing")
    public List<Billing> getAllBilling() {
        return billingService.getAllBillings();
    }

    @DeleteMapping("/billing/{id}")
    public ResponseEntity<String> deleteBilling(@PathVariable Long id) {
        try {
            billingService.deleteBilling(id);
            return new ResponseEntity<>("User with ID " + id + " deleted successfully", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/billing/{id}")
    public ResponseEntity<Billing> getBillingByID(@PathVariable Long id) {
        Billing billing = billingService.getBillingByID(id);
        if (billing == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(billing);
    }
}

