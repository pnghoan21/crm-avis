package com.example.demo.crm.dto;

import com.example.demo.crm.enums.CustomerPriority;
import com.example.demo.crm.enums.CustomerStatus;
import com.example.demo.crm.enums.CustomerType;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class CustomerRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Type is required")
    private CustomerType type;

    @NotBlank(message = "Phone is required")
    private String phone;

    private String email;
    private String address;
    private String taxCode;
    private String source;
    private CustomerStatus status;
    private CustomerPriority priority;
}

