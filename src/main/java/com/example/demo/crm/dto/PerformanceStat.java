package com.example.demo.crm.dto;

/**
 * Interface projection dùng để nhận kết quả thống kê hiệu suất nhân viên từ JPA Query.
 * Mỗi phần tử chứa tên nhân viên và số lần tương tác với khách hàng trong kỳ báo cáo.
 */
public interface PerformanceStat {

    /**
     * Trả về tên đầy đủ hoặc tên đăng nhập của nhân viên kinh doanh.
     */
    String getEmployeeName();

    /**
     * Trả về tổng số lần tương tác với khách hàng (số Activity đã hoàn thành)
     * trong kỳ báo cáo.
     */
    Long getInteractionCount();
}
