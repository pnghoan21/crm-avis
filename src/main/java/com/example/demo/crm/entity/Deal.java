package com.example.demo.crm.entity;

import com.example.demo.crm.enums.DealStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entity đại diện cho một cơ hội kinh doanh (Deal/Opportunity) trong hệ thống CRM.
 * Theo dõi toàn bộ vòng đời của một giao dịch từ tiềm năng đến chốt hợp đồng hoặc thất bại.
 */
@Entity
@Table(name = "deals")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Deal {

    /** ID định danh duy nhất của Deal, tự động sinh tăng dần. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Khách hàng gắn liền với Deal này.
     * Quan hệ Many-to-One với entity Customer. Không được để trống.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    /**
     * Nhu cầu cụ thể của khách hàng được ghi nhận trong Deal này.
     * Ví dụ: "Cần triển khai hệ thống CRM cho 50 nhân viên".
     */
    private String customerNeed;

    /**
     * Giá trị dự kiến của hợp đồng tính bằng VNĐ.
     * Dùng để dự báo doanh thu nếu Deal được chốt thành công.
     */
    private Double contractValue;

    /**
     * Danh mục dịch vụ hoặc sản phẩm mà Deal hướng đến.
     * Ví dụ: "Phần mềm CRM", "Dịch vụ Cloud", "Bảo trì hệ thống".
     */
    private String serviceCategory;

    /**
     * Hạn chót dự kiến để triển khai hoặc hoàn thành giao dịch.
     * Sử dụng định dạng ngày (không bao gồm giờ).
     */
    private LocalDate implementationDeadline;

    /**
     * Trạng thái hiện tại của Deal trong quy trình bán hàng.
     * Ví dụ: NEW (mới), IN_PROGRESS (đang xử lý), WON (thắng), LOST (thất bại).
     * Không được để trống.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DealStatus status;

    /**
     * Lý do thất bại khi Deal có trạng thái LOST.
     * Quan hệ Many-to-One với entity LostReason. Chỉ điền khi Deal bị thua.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lost_reason_id")
    private LostReason lostReason;

    /**
     * Nhân viên kinh doanh được phân công phụ trách theo dõi Deal này.
     * Quan hệ Many-to-One với entity User.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_employee_id")
    private User assignedEmployee;
}
