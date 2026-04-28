package com.example.demo.crm.controller;

import com.example.demo.crm.dto.ComplaintRequest;
import com.example.demo.crm.dto.ComplaintResponse;
import com.example.demo.crm.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ComplaintController {
    private final ComplaintService complaintService;

    @PostMapping("/customers/{customerId}/complaints")
    @PreAuthorize("hasAnyRole('ADMIN', 'SALES')")
    public ComplaintResponse addComplaint(
            @PathVariable Long customerId,
            @Valid @RequestBody ComplaintRequest request) {
        return complaintService.addComplaint(customerId, request);
    }

    @PutMapping("/complaints/{complaintId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SALES')")
    public ComplaintResponse updateComplaint(
            @PathVariable Long complaintId,
            @Valid @RequestBody ComplaintRequest request) {
        return complaintService.updateComplaint(complaintId, request);
    }

    @GetMapping("/customers/{customerId}/complaints")
    @PreAuthorize("hasAnyRole('ADMIN', 'SALES')")
    public List<ComplaintResponse> getComplaints(@PathVariable Long customerId) {
        return complaintService.getComplaints(customerId);
    }
}

