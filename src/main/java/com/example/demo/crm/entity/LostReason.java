package com.example.demo.crm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

/**
 * Entity lưu trữ danh mục các lý do thất bại trong bán hàng (Deal LOST).
 * Dùng để phân tích và thống kê nguyên nhân mất cơ hội kinh doanh.
 */
@Entity
@Table(name = "lost_reasons")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LostReason {

    /** ID định danh duy nhất của lý do thất bại, tự động sinh tăng dần. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Tên gọi ngắn gọn của lý do thất bại. Phải là duy nhất trong hệ thống.
     * Ví dụ: "Giá quá cao", "Chọn đối thủ cạnh tranh", "Hết ngân sách".
     * Không được để trống.
     */
    @Column(nullable = false, unique = true)
    private String reasonName;

    /**
     * Mô tả chi tiết hơn về lý do thất bại.
     * Giúp nhân viên hiểu rõ hơn khi áp dụng lý do này cho một Deal cụ thể.
     */
    private String description;
}
