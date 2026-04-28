package com.example.demo.crm.entity;

import com.example.demo.crm.enums.DealStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "deals")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private String customerNeed;
    
    private Double contractValue;
    
    private String serviceCategory;
    
    private LocalDate implementationDeadline;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DealStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lost_reason_id")
    private LostReason lostReason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_employee_id")
    private User assignedEmployee;
}

