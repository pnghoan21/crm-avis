package com.example.demo.crm.dto;

import com.example.demo.crm.enums.CareType;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * DTO trả về thông tin của một bản ghi lịch sử chăm sóc khách hàng cho client.
 */
@Data
public class CareHistoryResponse {

    /** ID định danh duy nhất của bản ghi lịch sử chăm sóc. */
    private Long id;

    /**
     * Loại hình chăm sóc đã thực hiện.
     * Ví dụ: CALL, MEETING, EMAIL, VISIT.
     */
    private CareType type;

    /**
     * Nội dung chi tiết của cuộc tương tác.
     * Ghi lại những gì đã trao đổi và kết quả đạt được.
     */
    private String content;

    /** Tên đăng nhập của nhân viên đã tạo bản ghi chăm sóc này. */
    private String createdByUsername;

    /**
     * Thời điểm bản ghi lịch sử được tạo trong hệ thống.
     * Định dạng: yyyy-MM-ddTHH:mm:ss.
     */
    private LocalDateTime createdAt;
}
