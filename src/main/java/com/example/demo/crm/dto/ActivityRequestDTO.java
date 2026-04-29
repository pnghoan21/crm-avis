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

    @NotNull(message = "Khách hàng không được để trống")
    private Long customerId;

    @NotNull(message = "Nhân viên phụ trách không được để trống")
    private Long assignedEmployeeId;

    @NotNull(message = "Thời hạn không được để trống")
    private LocalDateTime dueDate;

    @NotNull(message = "Loại hoạt động không được để trống")
    private CareType type;

    private String description;
}
