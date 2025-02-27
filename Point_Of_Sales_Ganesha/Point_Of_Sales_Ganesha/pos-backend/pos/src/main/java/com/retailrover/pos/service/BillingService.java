package com.retailrover.pos.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import com.retailrover.pos.entity.Billing;
import com.retailrover.pos.repository.BillingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BillingService {

    private final BillingRepository billingRepository;

    public Billing post(Billing billing){

        return billingRepository.save(billing);
    }

    public List<Billing> getAllBillings(){
        return billingRepository.findAll();

    }

    public void deleteBilling(Long id){
        if(!billingRepository.existsById(id)){
            throw new EntityNotFoundException("User with ID " + id + " not found");
        }

        billingRepository.deleteById(id);
    }


    public Billing getBillingByID(Long id){
        return billingRepository.findById(id).orElse(null);
    }

}
