package com.example.demo.crm.dto;

import com.example.demo.crm.enums.DealStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO trả về thông tin chi tiết của một Deal (cơ hội kinh doanh) cho client.
 * Bao gồm các thông tin mở rộng như tên khách hàng và tên nhân viên phụ trách.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DealResponseDTO {

    /** ID định danh duy nhất của Deal. */
    private Long id;

    /** ID của khách hàng liên kết với Deal này. */
    private Long customerId;

    /** Tên đầy đủ của khách hàng liên kết với Deal, trả về để hiển thị trực tiếp. */
    private String customerName;

    /**
     * Nhu cầu cụ thể của khách hàng được ghi nhận trong Deal.
     * Ví dụ: "Triển khai phần mềm quản lý cho 50 nhân viên".
     */
    private String customerNeed;

    /**
     * Giá trị dự kiến của hợp đồng (đơn vị: VNĐ).
     * Dùng để dự báo và thống kê doanh thu.
     */
    private Double contractValue;

    /**
     * Danh mục dịch vụ hoặc nhóm sản phẩm mà Deal hướng đến.
     * Ví dụ: "Phần mềm CRM", "Dịch vụ Cloud".
     */
    private String serviceCategory;

    /**
     * Hạn chót dự kiến để triển khai hoặc hoàn thành giao dịch.
     * Định dạng: yyyy-MM-dd.
     */
    private LocalDate implementationDeadline;

    /**
     * Trạng thái hiện tại của Deal trong quy trình bán hàng.
     * Ví dụ: NEW, IN_PROGRESS, WON, LOST.
     */
    private DealStatus status;

    /**
     * Thông tin lý do thất bại (nếu Deal có trạng thái LOST).
     * Null nếu Deal chưa thất bại hoặc chưa được gán lý do.
     */
    private LostReasonDTO lostReason;

    /** ID của nhân viên kinh doanh đang phụ trách Deal này. */
    private Long assignedEmployeeId;

    /** Tên của nhân viên kinh doanh đang phụ trách Deal, trả về để hiển thị trực tiếp. */
    private String assignedEmployeeName;
}
