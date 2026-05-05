package com.example.demo.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO trả về báo cáo hiệu suất làm việc của các nhân viên kinh doanh.
 * Thống kê số lần tương tác với khách hàng trong một khoảng thời gian nhất định.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceReportDTO {

    /**
     * Thời điểm bắt đầu của kỳ báo cáo (từ ngày).
     * Định dạng: yyyy-MM-ddTHH:mm:ss.
     */
    private LocalDateTime fromDate;

    /**
     * Thời điểm kết thúc của kỳ báo cáo (đến ngày).
     * Định dạng: yyyy-MM-ddTHH:mm:ss.
     */
    private LocalDateTime toDate;

    /**
     * Danh sách thống kê hiệu suất của từng nhân viên trong kỳ báo cáo.
     * Mỗi phần tử chứa tên nhân viên và số lần tương tác với khách hàng.
     */
    private List<PerformanceStat> performanceStats;
}
