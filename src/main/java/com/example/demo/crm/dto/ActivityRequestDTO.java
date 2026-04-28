package com.example.demo.crm.dto;

import com.example.demo.crm.enums.CareType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRequestDTO {

    @NotNull(message = "KhÃ¡ch hÃ ng khÃ´ng Ä‘Æ°á»£c Ä‘á»ƒ trá»‘ng")
    private Long customerId;

    @NotNull(message = "NhÃ¢n viÃªn phá»¥ trÃ¡ch khÃ´ng Ä‘Æ°á»£c Ä‘á»ƒ trá»‘ng")
    private Long assignedEmployeeId;

    @NotNull(message = "Thá»i háº¡n khÃ´ng Ä‘Æ°á»£c Ä‘á»ƒ trá»‘ng")
    private LocalDateTime dueDate;

    @NotNull(message = "Loáº¡i hoáº¡t Ä‘á»™ng khÃ´ng Ä‘Æ°á»£c Ä‘á»ƒ trá»‘ng")
    private CareType type;

    private String description;
}

