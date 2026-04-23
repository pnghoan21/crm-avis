package com.example.demo.crm.dto;

import com.example.demo.crm.enums.CareType;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CareHistoryRequest {
    @NotNull(message = "Type is required")
    private CareType type;
    private String content;
}
