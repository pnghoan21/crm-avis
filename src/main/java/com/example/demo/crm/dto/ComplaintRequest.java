package com.example.demo.crm.dto;

import com.example.demo.crm.enums.ComplaintStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ComplaintRequest {
    @NotBlank(message = "Title is required")
    private String title;
    private String description;
    private ComplaintStatus status;
}
