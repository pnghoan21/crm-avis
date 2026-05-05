package com.example.demo.crm.dto;

import com.example.demo.crm.enums.DealStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * DTO nhận dữ liệu đầu vào khi tạo mới hoặc cập nhật một Deal (cơ hội kinh doanh).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DealRequestDTO {

    /**
     * ID của khách hàng liên kết với Deal này.
     * Bắt buộc phải truyền vào để xác định Deal thuộc về khách hàng nào.
     */
    @NotNull(message = "Khách hàng không được để trống")
    private Long customerId;

    /**
     * Nhu cầu cụ thể của khách hàng được ghi nhận trong Deal.
     * Ví dụ: "Triển khai phần mềm quản lý cho 50 nhân viên".
     */
    private String customerNeed;

    /**
     * Giá trị dự kiến của hợp đồng (đơn vị: VNĐ).
     * Dùng để dự báo doanh thu khi Deal được chốt thành công.
     */
    private Double contractValue;

    /**
     * Danh mục dịch vụ hoặc nhóm sản phẩm mà Deal đang hướng đến.
     * Ví dụ: "Phần mềm CRM", "Dịch vụ Cloud", "Bảo trì hệ thống".
     */
    private String serviceCategory;

    /**
     * Hạn chót dự kiến để triển khai hoặc hoàn thành giao dịch.
     * Định dạng: yyyy-MM-dd (chỉ ngày, không bao gồm giờ).
     */
    private LocalDate implementationDeadline;

    /**
     * Trạng thái hiện tại của Deal trong quy trình bán hàng.
     * Ví dụ: NEW, IN_PROGRESS, WON, LOST.
     * Bắt buộc phải truyền vào.
     */
    @NotNull(message = "Trạng thái Deal không được để trống")
    private DealStatus status;

    /**
     * ID của lý do thất bại khi Deal chuyển sang trạng thái LOST.
     * Chỉ bắt buộc khi status = LOST, bỏ trống ở các trạng thái khác.
     */
    private Long lostReasonId;

    /**
     * ID của nhân viên kinh doanh được phân công phụ trách Deal này.
     * Nếu null, Deal chưa được giao cho ai cụ thể.
     */
    private Long assignedEmployeeId;
}
