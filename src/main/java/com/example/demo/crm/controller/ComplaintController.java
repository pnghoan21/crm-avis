package com.example.demo.crm.controller;

import com.example.demo.crm.dto.ComplaintRequest;
import com.example.demo.crm.dto.ComplaintResponse;
import com.example.demo.crm.service.ComplaintService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Tag(name = "Complaints - Khiếu nại", description = "Quản lý khiếu nại của khách hàng. Cho phép ghi nhận, cập nhật trạng thái xử lý và tra cứu lịch sử toàn bộ khiếu nại theo từng khách hàng.")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ComplaintController {
    private final ComplaintService complaintService;

    @Operation(
        summary = "Ghi nhận khiếu nại mới từ khách hàng",
        description = "Tạo một bản ghi khiếu nại mới cho khách hàng theo ID. Ghi nhận nội dung vấn đề và trạng thái xử lý ban đầu (thường là PENDING/OPEN)."
    )
    @PostMapping("/customers/{customerId}/complaints")
    @PreAuthorize("hasAnyRole('ADMIN', 'SALES')")
    public ComplaintResponse addComplaint(
            @Parameter(description = "ID của khách hàng có khiếu nại") @PathVariable Long customerId,
            @Valid @RequestBody ComplaintRequest request) {
        return complaintService.addComplaint(customerId, request);
    }

    @Operation(
        summary = "Cập nhật thông tin khiếu nại",
        description = "Chỉnh sửa nội dung hoặc cập nhật trạng thái xử lý của một khiếu nại (ví dụ: chuyển từ OPEN sang RESOLVED sau khi đã giải quyết xong)."
    )
    @PutMapping("/complaints/{complaintId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SALES')")
    public ComplaintResponse updateComplaint(
            @Parameter(description = "ID của khiếu nại cần cập nhật") @PathVariable Long complaintId,
            @Valid @RequestBody ComplaintRequest request) {
        return complaintService.updateComplaint(complaintId, request);
    }

    @Operation(
        summary = "Xem danh sách khiếu nại của khách hàng",
        description = "Lấy toàn bộ lịch sử khiếu nại của một khách hàng theo ID, sắp xếp từ mới nhất đến cũ nhất."
    )
    @GetMapping("/customers/{customerId}/complaints")
    @PreAuthorize("hasAnyRole('ADMIN', 'SALES')")
    public List<ComplaintResponse> getComplaints(
            @Parameter(description = "ID của khách hàng cần tra cứu khiếu nại") @PathVariable Long customerId) {
        return complaintService.getComplaints(customerId);
    }
}
