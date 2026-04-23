package com.example.demo.crm.controller;

import com.example.demo.crm.dto.CareHistoryRequest;
import com.example.demo.crm.dto.CareHistoryResponse;
import com.example.demo.crm.entity.User;
import com.example.demo.crm.service.CareHistoryService;
import com.example.demo.crm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers/{customerId}/care")
@RequiredArgsConstructor
public class CareHistoryController {
    private final CareHistoryService careHistoryService;
    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SALES')")
    public CareHistoryResponse addCareHistory(
            @PathVariable Long customerId,
            @Valid @RequestBody CareHistoryRequest request,
            Authentication authentication) {
        User currentUser = userService.findByUsername(authentication.getName());
        return careHistoryService.addCareHistory(customerId, request, currentUser);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SALES')")
    public List<CareHistoryResponse> getCareHistory(@PathVariable Long customerId) {
        return careHistoryService.getCareHistory(customerId);
    }
}
