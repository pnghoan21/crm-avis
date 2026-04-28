package com.example.demo.crm.controller;

import com.example.demo.crm.dto.CustomerDetailResponse;
import com.example.demo.crm.dto.CustomerRequest;
import com.example.demo.crm.dto.CustomerResponse;
import com.example.demo.crm.entity.User;
import com.example.demo.crm.enums.CustomerType;
import com.example.demo.crm.service.CustomerService;
import com.example.demo.crm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SALES')")
    public Page<CustomerResponse> getCustomers(
            @RequestParam(required = false) CustomerType type,
            @RequestParam(required = false) Long salesId,
            @RequestParam(required = false) String keyword,
            Pageable pageable,
            Authentication authentication) {
        
        User currentUser = userService.findByUsername(authentication.getName());
        
        // Sales can only see their own customers unless filtered explicitly by Admin
        if (currentUser.getRole().name().equals("SALES")) {
            return customerService.searchCustomers(type, currentUser.getId(), keyword, pageable);
        }
        
        return customerService.searchCustomers(type, salesId, keyword, pageable);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SALES')")
    public CustomerResponse createCustomer(@Valid @RequestBody CustomerRequest request, Authentication authentication) {
        User currentUser = userService.findByUsername(authentication.getName());
        return customerService.createCustomer(request, currentUser);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SALES')")
    public CustomerDetailResponse getCustomerDetail(@PathVariable Long id) {
        return customerService.getCustomerDetail(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SALES')")
    public CustomerResponse updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerRequest request) {
        return customerService.updateCustomer(id, request);
    }

    @PutMapping("/{id}/assign")
    @PreAuthorize("hasRole('ADMIN')")
    public void assignSales(@PathVariable Long id, @RequestParam Long salesId) {
        User sales = new User();
        sales.setId(salesId);
        customerService.assignSales(id, sales);
    }
}

