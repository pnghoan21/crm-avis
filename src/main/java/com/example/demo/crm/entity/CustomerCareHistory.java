package com.example.demo.crm.entity;

import com.example.demo.crm.enums.CareType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity lưu trữ lịch sử chăm sóc khách hàng.
 * Mỗi bản ghi đại diện cho một lần tương tác/chăm sóc đã được thực hiện với khách hàng,
 * có thể liên kết với một Activity đã hoàn thành hoặc được tạo thủ công.
 */
@Entity
@Table(name = "customer_care_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCareHistory {

    /** ID định danh duy nhất của bản ghi lịch sử chăm sóc, tự động sinh tăng dần. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Khách hàng được chăm sóc trong lần tương tác này.
     * Quan hệ Many-to-One với entity Customer. Không được để trống.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    /**
     * Loại hình chăm sóc đã thực hiện.
     * Ví dụ: CALL (gọi điện), MEETING (gặp mặt), EMAIL (gửi email).
     * Không được để trống.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CareType type;

    /**
     * Nội dung chi tiết của cuộc tương tác/chăm sóc.
     * Ghi lại những gì đã trao đổi, kết quả đạt được hoặc các ghi chú quan trọng.
     * Kiểu TEXT cho phép lưu nội dung dài.
     */
    @Column(columnDefinition = "TEXT")
    private String content;

    /**
     * Nhân viên thực hiện việc chăm sóc và ghi nhận bản ghi này.
     * Quan hệ Many-to-One với entity User.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_id")
    private User createdBy;

    /**
     * Thời điểm bản ghi lịch sử được tạo.
     * Tự động được điền khi tạo mới (xem @PrePersist).
     */
    private LocalDateTime createdAt;

    /**
     * Activity liên quan đã tạo ra bản ghi lịch sử này.
     * Có thể null nếu bản ghi được tạo thủ công không từ Activity.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "related_activity_id")
    private Activity relatedActivity;

    /** Tự động gán thời gian hiện tại cho trường createdAt khi tạo mới bản ghi. */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
