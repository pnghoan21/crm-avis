package com.example.demo.crm.entity;

import com.example.demo.crm.enums.CustomerStatus;
import com.example.demo.crm.enums.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

/**
 * Entity đại diện cho khách hàng trong hệ thống CRM.
 * Lưu trữ toàn bộ thông tin liên hệ, phân loại và trạng thái chăm sóc của khách hàng.
 */
@Entity
@Table(name = "customers", indexes = {
    @Index(name = "idx_customer_phone", columnList = "phone"),
    @Index(name = "idx_customer_sales_id", columnList = "assigned_sales_id")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    /** ID định danh duy nhất của khách hàng, tự động sinh tăng dần. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Tên đầy đủ của khách hàng hoặc tên doanh nghiệp. Không được để trống. */
    @Column(nullable = false)
    private String name;

    /**
     * Phân loại khách hàng theo nhóm đối tượng.
     * Ví dụ: INDIVIDUAL (cá nhân), BUSINESS (doanh nghiệp).
     * Không được để trống.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CustomerType type;

    /** Số điện thoại liên hệ của khách hàng. Đã được đánh index để tìm kiếm nhanh. Không được để trống. */
    @Column(nullable = false)
    private String phone;

    /** Địa chỉ email của khách hàng. Dùng để gửi thông báo và liên lạc. */
    private String email;

    /** Địa chỉ trụ sở hoặc nơi ở của khách hàng. */
    private String address;

    /** Mã số thuế của khách hàng (dành cho khách hàng doanh nghiệp). */
    private String taxCode;

    /**
     * Nhân viên kinh doanh (Sales) được phân công chăm sóc khách hàng này.
     * Quan hệ Many-to-One với entity User.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_sales_id")
    private User assignedSales;

    /**
     * Nguồn khách hàng - nơi khách hàng biết đến và tiếp cận dịch vụ.
     * Ví dụ: Referral (giới thiệu), Facebook, Website, Cold Call.
     */
    private String source;

    /**
     * Trạng thái hiện tại của khách hàng trong vòng đời chăm sóc.
     * Ví dụ: LEAD (tiềm năng), ACTIVE (đang hoạt động), INACTIVE (không hoạt động).
     */
    @Enumerated(EnumType.STRING)
    private CustomerStatus status;

    /**
     * Mức độ ưu tiên chăm sóc khách hàng.
     * Ví dụ: HIGH (ưu tiên cao), MEDIUM (trung bình), LOW (thấp).
     */
    @Enumerated(EnumType.STRING)
    private com.example.demo.crm.enums.CustomerPriority priority;
}
