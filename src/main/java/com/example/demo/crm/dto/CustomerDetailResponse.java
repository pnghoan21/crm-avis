package com.example.demo.crm.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * DTO trả về thông tin đầy đủ và chi tiết của một khách hàng cho client.
 * Kế thừa tất cả các trường cơ bản từ {@link CustomerResponse} và bổ sung thêm
 * địa chỉ, mã số thuế, nguồn tiếp cận, lịch sử chăm sóc và danh sách khiếu nại.
 * Được sử dụng cho API xem chi tiết khách hàng (GET /customers/{id}).
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerDetailResponse extends CustomerResponse {

    /** Địa chỉ trụ sở hoặc nơi ở của khách hàng. */
    private String address;

    /** Mã số thuế của khách hàng (dành cho khách hàng doanh nghiệp). */
    private String taxCode;

    /**
     * Nguồn tiếp cận - nơi khách hàng biết đến dịch vụ.
     * Ví dụ: Referral, Facebook, Website, Cold Call.
     */
    private String source;

    /**
     * Danh sách toàn bộ lịch sử các lần chăm sóc khách hàng.
     * Sắp xếp theo thời gian giảm dần (mới nhất trước).
     */
    private List<CareHistoryResponse> careHistory;

    /**
     * Danh sách toàn bộ khiếu nại của khách hàng.
     * Bao gồm cả các khiếu nại đã xử lý và chưa xử lý.
     */
    private List<ComplaintResponse> complaints;
}
