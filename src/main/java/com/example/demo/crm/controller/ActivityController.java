package com.example.demo.crm.controller;

import com.example.demo.crm.dto.ActivityRequestDTO;
import com.example.demo.crm.dto.ActivityResponseDTO;
import com.example.demo.crm.dto.PerformanceReportDTO;
import com.example.demo.crm.service.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Activities - Nhắc việc chăm sóc", description = "Quản lý lịch nhắc việc chăm sóc khách hàng. Bao gồm: tạo lịch thủ công, hoàn thành lịch (tự động sinh lịch mới dựa trên phân loại HOT/WARM/COLD), và xem báo cáo hiệu suất nhân viên.")
@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @Operation(
        summary = "Lấy danh sách nhắc việc của nhân viên",
        description = "Trả về danh sách tất cả các lịch nhắc việc được giao cho một nhân viên cụ thể, sắp xếp theo ngày đến hạn tăng dần. Kết quả bao gồm cả các nhắc việc PENDING, COMPLETED và OVERDUE."
    )
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<ActivityResponseDTO>> getMyActivities(
            @Parameter(description = "ID của nhân viên cần xem lịch nhắc việc") @PathVariable Long employeeId) {
        return ResponseEntity.ok(activityService.getMyActivities(employeeId));
    }

    @Operation(
        summary = "Tạo mới lịch nhắc việc",
        description = "Tạo thủ công một lịch nhắc việc mới cho một khách hàng cụ thể. Trạng thái ban đầu luôn là PENDING. " +
                      "Hệ thống cũng tự động tạo lịch sau khi nhân viên hoàn thành một lịch cũ (xem API complete)."
    )
    @PostMapping
    public ResponseEntity<ActivityResponseDTO> create(@Valid @RequestBody ActivityRequestDTO dto) {
        return ResponseEntity.ok(activityService.create(dto));
    }

    @Operation(
        summary = "Đánh dấu hoàn thành nhắc việc",
        description = "Hoàn thành một lịch nhắc việc và kích hoạt quy trình tự động: " +
                      "(1) Tạo bản ghi CustomerCareHistory để lưu vết tương tác. " +
                      "(2) Tự động sinh lịch nhắc việc tiếp theo dựa trên phân loại khách hàng: HOT = 3 ngày, WARM = 7 ngày, COLD = 30 ngày."
    )
    @PutMapping("/{id}/complete")
    public ResponseEntity<ActivityResponseDTO> completeActivity(
            @Parameter(description = "ID của nhắc việc cần hoàn thành") @PathVariable Long id,
            @Parameter(description = "Ghi chú kết quả cuộc gọi/gặp mặt") @RequestParam(required = false) String resultNote) {
        return ResponseEntity.ok(activityService.completeActivity(id, resultNote));
    }

    @Operation(
        summary = "Báo cáo hiệu suất hoạt động nhân viên",
        description = "Thống kê số lượng tương tác chăm sóc khách hàng của từng nhân viên trong khoảng thời gian chỉ định. " +
                      "Dùng để đánh giá nỗ lực và hiệu quả bán hàng định kỳ (tuần/tháng/quý). " +
                      "Định dạng tham số: ISO 8601, ví dụ: 2024-01-01T00:00:00"
    )
    @GetMapping("/reports/performance")
    public ResponseEntity<PerformanceReportDTO> getPerformanceReport(
            @Parameter(description = "Thời gian bắt đầu thống kê (ISO 8601)") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @Parameter(description = "Thời gian kết thúc thống kê (ISO 8601)") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return ResponseEntity.ok(activityService.getPerformanceReport(startDate, endDate));
    }
}
