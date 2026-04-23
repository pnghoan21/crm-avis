package com.example.demo.crm.dto;

import com.example.demo.crm.enums.CustomerStatus;
import com.example.demo.crm.enums.CustomerType;
import lombok.Data;

@Data
public class CustomerResponse {
    private Long id;
    private String name;
    private CustomerType type;
    private String phone;
    private String email;
    private CustomerStatus status;
    private Long assignedSalesId;
    private String assignedSalesName;
}
