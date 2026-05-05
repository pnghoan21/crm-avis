package com.example.demo.crm.controller;

import com.example.demo.crm.dto.DealRequestDTO;
import com.example.demo.crm.dto.DealResponseDTO;
import com.example.demo.crm.dto.LostSaleAnalysisReportDTO;
import com.example.demo.crm.enums.DealStatus;
import com.example.demo.crm.service.DealService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Tag(name = "Deals - Cơ hội bán hàng", description = "Quản lý toàn bộ vòng đời cơ hội bán hàng: từ khởi tạo, chuyển trạng thái đến phân tích thất bại và chuyển đổi thành công.")
@RestController
@RequestMapping("/api/deals")
@RequiredArgsConstructor
public class DealController {

    private final DealService dealService;

    @Operation(
        summary = "Lấy danh sách tất cả Deals",
        description = "Trả về toàn bộ danh sách các cơ hội bán hàng trong hệ thống, bao gồm các thông tin: khách hàng, nhân viên phụ trách, trạng thái và giá trị hợp đồng."
    )
    @GetMapping
    public ResponseEntity<List<DealResponseDTO>> getAll() {
        return ResponseEntity.ok(dealService.getAll());
    }

    @Operation(
        summary = "Tạo mới một Deal",
        description = "Khởi tạo một cơ hội bán hàng mới cho một khách hàng có sẵn trong hệ thống. Deal mới sẽ được gán cho một nhân viên phụ trách và có trạng thái mặc định là NEW."
    )
    @PostMapping
    public ResponseEntity<DealResponseDTO> create(@Valid @RequestBody DealRequestDTO dto) {
        return ResponseEntity.ok(dealService.create(dto));
    }

    @Operation(
        summary = "Chuyển đổi trạng thái Deal",
        description = "Cập nhật trạng thái của một cơ hội bán hàng. Quy tắc nghiệp vụ: " +
                      "(1) Nếu status=LOST, bắt buộc phải truyền lostReasonId để ghi nhận lý do thất bại. " +
                      "(2) Nếu status=WON, Deal phải đã có giá trị hợp đồng (contractValue) và danh mục dịch vụ (serviceCategory) mới được phép chuyển. " +
                      "Trình tự hợp lệ: NEW → QUALIFIED → PROPOSITION → WON hoặc LOST."
    )
    @PutMapping("/{id}/status")
    public ResponseEntity<DealResponseDTO> updateStatus(
            @Parameter(description = "ID của Deal cần cập nhật") @PathVariable Long id,
            @Parameter(description = "Trạng thái mới: NEW | QUALIFIED | PROPOSITION | WON | LOST") @RequestParam DealStatus status,
            @Parameter(description = "ID lý do thất bại (bắt buộc khi status=LOST)") @RequestParam(required = false) Long lostReasonId) {
        return ResponseEntity.ok(dealService.updateStatus(id, status, lostReasonId));
    }

    @Operation(
        summary = "Báo cáo phân tích Lost Sale",
        description = "Xuất báo cáo tổng hợp tỷ lệ mất khách hàng. Kết quả bao gồm 3 góc phân tích: " +
                      "(1) Thống kê số deal thất bại theo từng nhân viên phụ trách. " +
                      "(2) Thống kê theo nhu cầu của khách hàng (customerNeed). " +
                      "(3) Thống kê chi tiết theo từng lý do thất bại cụ thể."
    )
    @GetMapping("/reports/lost-sales")
    public ResponseEntity<LostSaleAnalysisReportDTO> getLostSaleReport() {
        return ResponseEntity.ok(dealService.getLostSaleAnalysisReport());
    }
}
