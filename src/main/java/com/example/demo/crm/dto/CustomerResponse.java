package com.example.demo.crm.dto;

import com.example.demo.crm.enums.CustomerStatus;
import com.example.demo.crm.enums.CustomerType;
import lombok.Data;

/**
 * DTO trả về thông tin tóm tắt của một khách hàng cho client.
 * Được sử dụng trong các danh sách (list API) để trả về thông tin cần thiết nhất.
 * Thông tin chi tiết hơn xem tại {@link CustomerDetailResponse}.
 */
@Data
public class CustomerResponse {

    /** ID định danh duy nhất của khách hàng. */
    private Long id;

    /** Tên đầy đủ của khách hàng hoặc tên doanh nghiệp. */
    private String name;

    /**
     * Phân loại khách hàng theo nhóm đối tượng.
     * Ví dụ: INDIVIDUAL (cá nhân), BUSINESS (doanh nghiệp).
     */
    private CustomerType type;

    /** Số điện thoại liên hệ chính của khách hàng. */
    private String phone;

    /** Địa chỉ email của khách hàng. */
    private String email;

    /**
     * Trạng thái hiện tại của khách hàng trong vòng đời chăm sóc.
     * Ví dụ: LEAD, ACTIVE, INACTIVE.
     */
    private CustomerStatus status;

    /** ID của nhân viên kinh doanh đang phụ trách khách hàng này. */
    private Long assignedSalesId;

    /** Tên của nhân viên kinh doanh đang phụ trách, trả về để hiển thị trực tiếp. */
    private String assignedSalesName;
}
