package com.example.demo.crm.dto;

import com.example.demo.crm.enums.CareType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * DTO nhận dữ liệu đầu vào khi tạo mới một Activity (hoạt động chăm sóc khách hàng).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRequestDTO {

    /**
     * ID của khách hàng là đối tượng của hoạt động này.
     * Bắt buộc phải truyền vào.
     */
    @NotNull(message = "Khách hàng không được để trống")
    private Long customerId;

    /**
     * ID của nhân viên được phân công thực hiện hoạt động này.
     * Bắt buộc phải truyền vào.
     */
    @NotNull(message = "Nhân viên phụ trách không được để trống")
    private Long assignedEmployeeId;

    /**
     * Thời hạn cần hoàn thành hoạt động (deadline).
     * Định dạng: yyyy-MM-ddTHH:mm:ss (bao gồm cả ngày và giờ cụ thể).
     * Bắt buộc phải truyền vào.
     */
    @NotNull(message = "Thời hạn không được để trống")
    private LocalDateTime dueDate;

    /**
     * Loại hình hoạt động chăm sóc khách hàng.
     * Ví dụ: CALL (gọi điện), MEETING (gặp mặt), EMAIL (gửi email), VISIT (thăm tại chỗ).
     * Bắt buộc phải truyền vào.
     */
    @NotNull(message = "Loại hoạt động không được để trống")
    private CareType type;

    /**
     * Mô tả chi tiết về mục đích, nội dung hoặc ghi chú của hoạt động.
     * Không bắt buộc.
     */
    private String description;
}
