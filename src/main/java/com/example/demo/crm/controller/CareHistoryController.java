package com.example.demo.crm.controller;

import com.example.demo.crm.dto.CareHistoryRequest;
import com.example.demo.crm.dto.CareHistoryResponse;
import com.example.demo.crm.entity.User;
import com.example.demo.crm.service.CareHistoryService;
import com.example.demo.crm.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Tag(name = "Care History - Lịch sử chăm sóc", description = "Tra cứu và ghi nhận thủ công lịch sử tương tác/chăm sóc khách hàng. Lịch sử cũng được tự động sinh ra khi nhân viên hoàn thành một lịch nhắc việc (Activity).")
@RestController
@RequestMapping("/api/v1/customers/{customerId}/care")
@RequiredArgsConstructor
public class CareHistoryController {
    private final CareHistoryService careHistoryService;
    private final UserService userService;

    @Operation(
        summary = "Ghi nhận tương tác chăm sóc khách hàng thủ công",
        description = "Tạo một bản ghi lịch sử chăm sóc khách hàng một cách thủ công (không cần qua lịch nhắc việc). " +
                      "Dùng khi nhân viên chăm sóc ngoài kế hoạch, ví dụ: khách hàng tự liên hệ, gặp mặt đột xuất. " +
                      "Người thực hiện sẽ được tự động lấy từ thông tin đăng nhập hiện tại."
    )
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SALES')")
    public CareHistoryResponse addCareHistory(
            @Parameter(description = "ID của khách hàng được chăm sóc") @PathVariable Long customerId,
            @Valid @RequestBody CareHistoryRequest request,
            Authentication authentication) {
        User currentUser = userService.findByUsername(authentication.getName());
        return careHistoryService.addCareHistory(customerId, request, currentUser);
    }

    @Operation(
        summary = "Xem lịch sử chăm sóc của khách hàng",
        description = "Trả về toàn bộ danh sách các lần tương tác/chăm sóc khách hàng theo ID, bao gồm cả các lần tự động sinh từ Activity và các lần ghi thủ công. Sắp xếp từ gần nhất đến xa nhất."
    )
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SALES')")
    public List<CareHistoryResponse> getCareHistory(
            @Parameter(description = "ID của khách hàng cần xem lịch sử") @PathVariable Long customerId) {
        return careHistoryService.getCareHistory(customerId);
    }
}
