package com.example.demo.crm.dto;

import com.example.demo.crm.enums.ActivityStatus;
import com.example.demo.crm.enums.CareType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityResponseDTO {
    private Long id;
    private Long customerId;
    private String customerName;
    private Long assignedEmployeeId;
    private String assignedEmployeeName;
    private LocalDateTime dueDate;
    private CareType type;
    private ActivityStatus status;
    private String description;
    private LocalDateTime completedAt;
}
