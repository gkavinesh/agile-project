package com.retailrover.pos.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import com.retailrover.pos.entity.Supplier;
import com.retailrover.pos.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public Supplier post(Supplier supplier){

        return supplierRepository.save(supplier);
    }

    public List<Supplier> getAllSuppliers(){
        return supplierRepository.findAll();

    }

    public void deleteSuppliers(Long id){
        if(!supplierRepository.existsById(id)){
            throw new EntityNotFoundException("Supplier with ID " + id + " not found");
        }

        supplierRepository.deleteById(id);
    }

    public Supplier getSupplierByID(Long id){
        return supplierRepository.findById(id).orElse(null);
    }

    public Supplier updateSupplier(Long id,Supplier supplier){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if(optionalSupplier.isPresent()){
            Supplier existingSupplier = optionalSupplier.get();

            existingSupplier.setSupplier_name(supplier.getSupplier_name());
            existingSupplier.setProduct_name(supplier.getProduct_name());
            existingSupplier.setAddress(supplier.getAddress());
            existingSupplier.setEmail(supplier.getEmail());

            return supplierRepository.save(existingSupplier);

        }
        return null;
    }

}