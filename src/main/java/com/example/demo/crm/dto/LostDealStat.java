package com.example.demo.crm.dto;

/**
 * Interface projection dùng để nhận kết quả thống kê Deal thất bại từ JPA Query.
 * Mỗi phần tử chứa một danh mục thống kê (tên nhân viên, tên lý do, v.v.)
 * và tổng số Deal bị thua tương ứng.
 */
public interface LostDealStat {

    /**
     * Trả về tên danh mục thống kê.
     * Tùy ngữ cảnh, có thể là: tên nhân viên, tên nhóm nhu cầu hoặc tên lý do thất bại.
     */
    String getCategory();

    /**
     * Trả về tổng số Deal bị thua thuộc danh mục này.
     */
    Long getTotalCount();
}
