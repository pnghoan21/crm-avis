package com.example.demo.crm.entity;

import com.example.demo.crm.enums.ComplaintStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity đại diện cho một khiếu nại/phản ánh của khách hàng.
 * Theo dõi và quản lý toàn bộ vòng đời xử lý các phản hồi tiêu cực từ khách hàng.
 */
@Entity
@Table(name = "customer_complaints")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerComplaint {

    /** ID định danh duy nhất của khiếu nại, tự động sinh tăng dần. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Khách hàng gửi khiếu nại này.
     * Quan hệ Many-to-One với entity Customer. Không được để trống.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    /**
     * Tiêu đề ngắn gọn mô tả nội dung khiếu nại.
     * Dùng để hiển thị trong danh sách và dễ dàng nhận diện. Không được để trống.
     */
    @Column(nullable = false)
    private String title;

    /**
     * Mô tả chi tiết về nội dung khiếu nại của khách hàng.
     * Bao gồm vấn đề gặp phải, thời gian xảy ra, và yêu cầu của khách hàng.
     * Kiểu TEXT cho phép lưu nội dung dài.
     */
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * Trạng thái xử lý của khiếu nại.
     * Ví dụ: PENDING (chờ xử lý), IN_PROGRESS (đang xử lý), RESOLVED (đã giải quyết), CLOSED (đã đóng).
     * Không được để trống.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ComplaintStatus status;

    /**
     * Thời điểm khiếu nại được tạo trong hệ thống.
     * Tự động được điền khi tạo mới (xem @PrePersist).
     */
    private LocalDateTime createdAt;

    /** Tự động gán thời gian hiện tại cho trường createdAt khi tạo mới bản ghi. */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
