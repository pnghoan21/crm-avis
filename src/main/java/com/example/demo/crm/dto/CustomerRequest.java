package com.example.demo.crm.dto;

import com.example.demo.crm.enums.CustomerPriority;
import com.example.demo.crm.enums.CustomerStatus;
import com.example.demo.crm.enums.CustomerType;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO nhận dữ liệu đầu vào khi tạo mới hoặc cập nhật thông tin khách hàng.
 */
@Data
public class CustomerRequest {

    /**
     * Tên đầy đủ của khách hàng hoặc tên doanh nghiệp.
     * Bắt buộc phải truyền vào và không được là chuỗi rỗng.
     */
    @NotBlank(message = "Name is required")
    private String name;

    /**
     * Phân loại khách hàng theo nhóm đối tượng.
     * Ví dụ: INDIVIDUAL (cá nhân), BUSINESS (doanh nghiệp).
     * Bắt buộc phải truyền vào.
     */
    @NotNull(message = "Type is required")
    private CustomerType type;

    /**
     * Số điện thoại liên hệ chính của khách hàng.
     * Bắt buộc phải truyền vào và không được là chuỗi rỗng.
     */
    @NotBlank(message = "Phone is required")
    private String phone;

    /**
     * Địa chỉ email của khách hàng. Dùng để gửi thông báo và liên lạc.
     * Không bắt buộc.
     */
    private String email;

    /**
     * Địa chỉ trụ sở hoặc nơi ở của khách hàng.
     * Không bắt buộc.
     */
    private String address;

    /**
     * Mã số thuế của khách hàng (dành cho khách hàng doanh nghiệp).
     * Không bắt buộc.
     */
    private String taxCode;

    /**
     * Nguồn tiếp cận - nơi khách hàng biết đến dịch vụ.
     * Ví dụ: Referral (giới thiệu), Facebook, Website, Cold Call.
     * Không bắt buộc.
     */
    private String source;

    /**
     * Trạng thái hiện tại của khách hàng.
     * Ví dụ: LEAD (tiềm năng), ACTIVE (đang hoạt động), INACTIVE (không hoạt động).
     * Không bắt buộc, mặc định có thể là LEAD khi tạo mới.
     */
    private CustomerStatus status;

    /**
     * Mức độ ưu tiên chăm sóc khách hàng.
     * Ví dụ: HIGH (ưu tiên cao), MEDIUM (trung bình), LOW (thấp).
     * Không bắt buộc.
     */
    private CustomerPriority priority;
}
