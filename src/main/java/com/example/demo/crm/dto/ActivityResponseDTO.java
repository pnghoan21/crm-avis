package com.example.demo.crm.dto;

import com.example.demo.crm.enums.ActivityStatus;
import com.example.demo.crm.enums.CareType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO trả về thông tin chi tiết của một Activity (hoạt động chăm sóc khách hàng) cho client.
 * Bao gồm các thông tin mở rộng như tên khách hàng và tên nhân viên phụ trách.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityResponseDTO {

    /** ID định danh duy nhất của hoạt động. */
    private Long id;

    /** ID của khách hàng là đối tượng của hoạt động này. */
    private Long customerId;

    /** Tên đầy đủ của khách hàng, trả về để hiển thị trực tiếp mà không cần gọi thêm API. */
    private String customerName;

    /** ID của nhân viên được phân công thực hiện hoạt động này. */
    private Long assignedEmployeeId;

    /** Tên của nhân viên được phân công, trả về để hiển thị trực tiếp mà không cần gọi thêm API. */
    private String assignedEmployeeName;

    /**
     * Thời hạn cần hoàn thành hoạt động (deadline).
     * Định dạng: yyyy-MM-ddTHH:mm:ss.
     */
    private LocalDateTime dueDate;

    /**
     * Loại hình hoạt động chăm sóc khách hàng.
     * Ví dụ: CALL, MEETING, EMAIL, VISIT.
     */
    private CareType type;

    /**
     * Trạng thái thực hiện hiện tại của hoạt động.
     * Ví dụ: PENDING, IN_PROGRESS, DONE, CANCELLED.
     */
    private ActivityStatus status;

    /**
     * Mô tả chi tiết về nội dung hoặc ghi chú của hoạt động.
     */
    private String description;

    /**
     * Thời điểm hoạt động được đánh dấu hoàn thành.
     * Null nếu hoạt động chưa được hoàn thành.
     */
    private LocalDateTime completedAt;
}
