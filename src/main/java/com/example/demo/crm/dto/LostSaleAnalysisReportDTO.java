package com.example.demo.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO trả về báo cáo phân tích nguyên nhân thất bại trong bán hàng (Lost Sale Analysis).
 * Tổng hợp thống kê các Deal bị thua theo nhiều chiều: nhân viên, nhu cầu khách hàng và lý do thất bại.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LostSaleAnalysisReportDTO {

    /**
     * Tổng số Deal bị thất bại (trạng thái LOST) trong kỳ phân tích.
     */
    private long totalLostDeals;

    /**
     * Thống kê số Deal bị thua theo từng nhân viên kinh doanh.
     * Mỗi phần tử chứa tên nhân viên (category) và tổng số Deal thua (totalCount).
     */
    private List<LostDealStat> lostByEmployee;

    /**
     * Thống kê số Deal bị thua theo nhu cầu khách hàng (customerNeed).
     * Giúp xác định nhóm nhu cầu nào thường dẫn đến thất bại.
     * Mỗi phần tử chứa tên nhóm nhu cầu (category) và tổng số Deal thua (totalCount).
     */
    private List<LostDealStat> lostByCustomerNeed;

    /**
     * Thống kê số Deal bị thua theo từng lý do thất bại (LostReason).
     * Giúp xác định nguyên nhân chính dẫn đến thua Deal để cải thiện quy trình bán hàng.
     * Mỗi phần tử chứa tên lý do (category) và tổng số Deal thua (totalCount).
     */
    private List<LostDealStat> lostByReason;
}
