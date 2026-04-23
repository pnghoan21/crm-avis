package com.example.demo.crm.dto;

import com.example.demo.crm.enums.ComplaintStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ComplaintResponse {
    private Long id;
    private String title;
    private String description;
    private ComplaintStatus status;
    private LocalDateTime createdAt;
}
