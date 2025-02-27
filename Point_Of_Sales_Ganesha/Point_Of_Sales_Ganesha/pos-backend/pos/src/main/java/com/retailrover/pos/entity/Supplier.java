package com.retailrover.pos.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Entity
@Data
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String supplier_name;

    private String product_name;

    private String address;

    private String email;

    // Constructors, getters, and setters
}
