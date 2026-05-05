package com.example.demo.crm.dto;

import com.example.demo.crm.enums.ComplaintStatus;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO nhận dữ liệu đầu vào khi tạo mới hoặc cập nhật một khiếu nại của khách hàng.
 * ID khách hàng được lấy từ path variable trên URL, không cần truyền trong body.
 */
@Data
public class ComplaintRequest {

    /**
     * Tiêu đề ngắn gọn mô tả nội dung khiếu nại.
     * Dùng để hiển thị trong danh sách và dễ dàng nhận diện.
     * Bắt buộc phải truyền vào và không được là chuỗi rỗng.
     */
    @NotBlank(message = "Title is required")
    private String title;

    /**
     * Mô tả chi tiết về vấn đề khách hàng gặp phải.
     * Bao gồm thời gian xảy ra, ảnh hưởng và yêu cầu giải quyết.
     * Không bắt buộc nhưng nên cung cấp để xử lý hiệu quả.
     */
    private String description;

    /**
     * Trạng thái xử lý của khiếu nại (dùng khi cập nhật).
     * Ví dụ: PENDING (chờ xử lý), IN_PROGRESS (đang xử lý), RESOLVED (đã giải quyết), CLOSED (đã đóng).
     * Khi tạo mới, trạng thái mặc định thường là PENDING.
     */
    private ComplaintStatus status;
}
