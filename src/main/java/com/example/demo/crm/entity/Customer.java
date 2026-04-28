package com.example.demo.crm.entity;

import com.example.demo.crm.enums.CustomerStatus;
import com.example.demo.crm.enums.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "customers", indexes = {
    @Index(name = "idx_customer_phone", columnList = "phone"),
    @Index(name = "idx_customer_sales_id", columnList = "assigned_sales_id")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CustomerType type;

    @Column(nullable = false)
    private String phone;

    private String email;
    private String address;
    private String taxCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_sales_id")
    private User assignedSales;

    private String source;

    @Enumerated(EnumType.STRING)
    private CustomerStatus status;

    @Enumerated(EnumType.STRING)
    private com.example.demo.crm.enums.CustomerPriority priority;
}

