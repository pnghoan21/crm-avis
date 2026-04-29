package com.example.demo.crm.controller;

import com.example.demo.crm.dto.CustomerDetailResponse;
import com.example.demo.crm.dto.CustomerRequest;
import com.example.demo.crm.dto.CustomerResponse;
import com.example.demo.crm.entity.User;
import com.example.demo.crm.enums.CustomerType;
import com.example.demo.crm.service.CustomerService;
import com.example.demo.crm.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Tag(name = "Customers - Khách hàng", description = "Quản lý hồ sơ khách hàng. Admin có thể xem toàn bộ danh sách, nhân viên Sales chỉ xem được khách hàng được giao cho mình.")
@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final UserService userService;

    @Operation(
        summary = "Tìm kiếm và phân trang danh sách khách hàng",
        description = "Lấy danh sách khách hàng với hỗ trợ lọc và phân trang. " +
                      "Admin có thể xem toàn bộ và lọc theo salesId. " +
                      "Nhân viên Sales chỉ thấy danh sách khách hàng được phân công cho mình."
    )
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SALES')")
    public Page<CustomerResponse> getCustomers(
            @Parameter(description = "Lọc theo loại khách hàng: INDIVIDUAL | COMPANY") @RequestParam(required = false) CustomerType type,
            @Parameter(description = "Lọc theo ID nhân viên phụ trách (chỉ Admin)") @RequestParam(required = false) Long salesId,
            @Parameter(description = "Tìm kiếm theo tên, số điện thoại hoặc email") @RequestParam(required = false) String keyword,
            Pageable pageable,
            Authentication authentication) {
        User currentUser = userService.findByUsername(authentication.getName());
        if (currentUser.getRole().name().equals("SALES")) {
            return customerService.searchCustomers(type, currentUser.getId(), keyword, pageable);
        }
        return customerService.searchCustomers(type, salesId, keyword, pageable);
    }

    @Operation(
        summary = "Tạo mới hồ sơ khách hàng",
        description = "Tạo một hồ sơ khách hàng mới trong hệ thống. Khách hàng mới sẽ tự động được gán cho nhân viên đang thực hiện thao tác. " +
                      "Có thể khai báo phân loại ưu tiên (priority): HOT, WARM hoặc COLD để hệ thống tự động thiết lập lịch chăm sóc phù hợp."
    )
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SALES')")
    public CustomerResponse createCustomer(@Valid @RequestBody CustomerRequest request, Authentication authentication) {
        User currentUser = userService.findByUsername(authentication.getName());
        return customerService.createCustomer(request, currentUser);
    }

    @Operation(
        summary = "Xem chi tiết hồ sơ khách hàng",
        description = "Trả về toàn bộ thông tin chi tiết của một khách hàng bao gồm: thông tin cơ bản, lịch sử chăm sóc (careHistory) và danh sách khiếu nại (complaints)."
    )
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SALES')")
    public CustomerDetailResponse getCustomerDetail(
            @Parameter(description = "ID của khách hàng") @PathVariable Long id) {
        return customerService.getCustomerDetail(id);
    }

    @Operation(
        summary = "Cập nhật thông tin khách hàng",
        description = "Chỉnh sửa thông tin hồ sơ của một khách hàng hiện có. Có thể dùng để cập nhật mức độ ưu tiên (priority) giúp hệ thống điều chỉnh chu kỳ nhắc việc tự động."
    )
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SALES')")
    public CustomerResponse updateCustomer(
            @Parameter(description = "ID của khách hàng cần cập nhật") @PathVariable Long id,
            @Valid @RequestBody CustomerRequest request) {
        return customerService.updateCustomer(id, request);
    }

    @Operation(
        summary = "Giao khách hàng cho nhân viên Sales (Admin only)",
        description = "Chỉ Admin mới có quyền phân công (hoặc chuyển giao) một khách hàng sang nhân viên Sales khác."
    )
    @PutMapping("/{id}/assign")
    @PreAuthorize("hasRole('ADMIN')")
    public void assignSales(
            @Parameter(description = "ID của khách hàng") @PathVariable Long id,
            @Parameter(description = "ID nhân viên Sales mới được giao") @RequestParam Long salesId) {
        User sales = new User();
        sales.setId(salesId);
        customerService.assignSales(id, sales);
    }
}
