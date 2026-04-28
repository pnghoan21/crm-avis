package com.example.demo.crm.dto;

import com.example.demo.crm.enums.DealStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DealResponseDTO {
    private Long id;
    private Long customerId;
    private String customerName;
    private String customerNeed;
    private Double contractValue;
    private String serviceCategory;
    private LocalDate implementationDeadline;
    private DealStatus status;
    private LostReasonDTO lostReason;
    private Long assignedEmployeeId;
    private String assignedEmployeeName;
}
