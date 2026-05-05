package com.example.demo.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO dùng cho cả request (tạo/cập nhật) và response (trả về) của một lý do thất bại (LostReason).
 * Khi dùng làm request: trường {@code id} bỏ qua, {@code reasonName} bắt buộc.
 * Khi dùng làm response: tất cả các trường đều được điền đầy đủ.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LostReasonDTO {

    /**
     * ID định danh duy nhất của lý do thất bại.
     * Chỉ có giá trị khi được trả về từ server (response), bỏ qua khi tạo mới.
     */
    private Long id;

    /**
     * Tên ngắn gọn, duy nhất của lý do thất bại.
     * Ví dụ: "Giá quá cao", "Chọn đối thủ cạnh tranh", "Hết ngân sách".
     * Bắt buộc phải truyền vào và không được là chuỗi rỗng.
     */
    @NotBlank(message = "Tên lý do không được để trống")
    private String reasonName;

    /**
     * Mô tả chi tiết hơn về lý do thất bại.
     * Giúp nhân viên hiểu rõ hơn khi áp dụng lý do này cho một Deal cụ thể.
     * Không bắt buộc.
     */
    private String description;
}
