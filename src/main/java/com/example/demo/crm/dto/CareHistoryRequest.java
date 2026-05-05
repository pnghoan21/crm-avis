package com.example.demo.crm.dto;

import com.example.demo.crm.enums.CareType;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * DTO nhận dữ liệu đầu vào khi tạo mới một bản ghi lịch sử chăm sóc khách hàng.
 * ID khách hàng được lấy từ path variable trên URL, không cần truyền trong body.
 */
@Data
public class CareHistoryRequest {

    /**
     * Loại hình chăm sóc đã thực hiện.
     * Ví dụ: CALL (gọi điện), MEETING (gặp mặt), EMAIL (gửi email), VISIT (thăm tại chỗ).
     * Bắt buộc phải truyền vào.
     */
    @NotNull(message = "Type is required")
    private CareType type;

    /**
     * Nội dung chi tiết của cuộc tương tác/chăm sóc.
     * Ghi lại những gì đã trao đổi, kết quả đạt được hoặc các ghi chú quan trọng.
     * Không bắt buộc.
     */
    private String content;
}

