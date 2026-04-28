package com.example.demo.crm.dto;

import com.example.demo.crm.enums.DealStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DealRequestDTO {

    @NotNull(message = "KhÃ¡ch hÃ ng khÃ´ng Ä‘Æ°á»£c Ä‘á»ƒ trá»‘ng")
    private Long customerId;

    private String customerNeed;
    
    private Double contractValue;
    
    private String serviceCategory;
    
    private LocalDate implementationDeadline;

    @NotNull(message = "Tráº¡ng thÃ¡i Deal khÃ´ng Ä‘Æ°á»£c Ä‘á»ƒ trá»‘ng")
    private DealStatus status;

    private Long lostReasonId;

    private Long assignedEmployeeId;
}

