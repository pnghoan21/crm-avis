package com.example.demo.crm.entity;

import com.example.demo.crm.enums.ActivityStatus;
import com.example.demo.crm.enums.CareType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity đại diện cho một hoạt động chăm sóc khách hàng được lên lịch.
 * Mỗi Activity là một nhiệm vụ cụ thể (gọi điện, gặp mặt, email,...) được giao cho nhân viên
 * để thực hiện với một khách hàng nhất định.
 */
@Entity
@Table(name = "activities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity {

    /** ID định danh duy nhất của hoạt động, tự động sinh tăng dần. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Khách hàng là đối tượng của hoạt động này.
     * Quan hệ Many-to-One với entity Customer. Không được để trống.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    /**
     * Nhân viên được phân công thực hiện hoạt động này.
     * Quan hệ Many-to-One với entity User. Không được để trống.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_employee_id", nullable = false)
    private User assignedEmployee;

    /**
     * Thời hạn cần hoàn thành hoạt động (deadline).
     * Bao gồm cả ngày và giờ cụ thể. Không được để trống.
     */
    @Column(nullable = false)
    private LocalDateTime dueDate;

    /**
     * Loại hình hoạt động chăm sóc khách hàng.
     * Ví dụ: CALL (gọi điện), MEETING (gặp mặt), EMAIL (gửi email), VISIT (thăm tại chỗ).
     * Không được để trống.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CareType type;

    /**
     * Trạng thái thực hiện của hoạt động.
     * Ví dụ: PENDING (chờ thực hiện), IN_PROGRESS (đang thực hiện), DONE (đã hoàn thành), CANCELLED (đã hủy).
     * Không được để trống.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActivityStatus status;

    /**
     * Mô tả chi tiết về nội dung, mục đích hoặc ghi chú của hoạt động.
     * Kiểu TEXT cho phép lưu nội dung dài.
     */
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * Thời điểm hoạt động được đánh dấu hoàn thành.
     * Null nếu hoạt động chưa được hoàn thành.
     */
    private LocalDateTime completedAt;
}
