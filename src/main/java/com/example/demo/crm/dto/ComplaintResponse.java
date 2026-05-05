package com.example.demo.crm.dto;

import com.example.demo.crm.enums.ComplaintStatus;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * DTO trả về thông tin của một khiếu nại khách hàng cho client.
 */
@Data
public class ComplaintResponse {

    /** ID định danh duy nhất của khiếu nại. */
    private Long id;

    /**
     * Tiêu đề ngắn gọn mô tả nội dung khiếu nại.
     * Dùng để hiển thị trong danh sách và nhận diện nhanh.
     */
    private String title;

    /**
     * Mô tả chi tiết về vấn đề khách hàng gặp phải.
     * Có thể là null nếu không được cung cấp khi tạo.
     */
    private String description;

    /**
     * Trạng thái xử lý hiện tại của khiếu nại.
     * Ví dụ: PENDING, IN_PROGRESS, RESOLVED, CLOSED.
     */
    private ComplaintStatus status;

    /**
     * Thời điểm khiếu nại được tạo trong hệ thống.
     * Định dạng: yyyy-MM-ddTHH:mm:ss.
     */
    private LocalDateTime createdAt;
}
