package com.example.demo.crm.dto;

import com.example.demo.crm.enums.CareType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CareHistoryResponse {
    private Long id;
    private CareType type;
    private String content;
    private String createdByUsername;
    private LocalDateTime createdAt;
}
