package com.example.demo.crm.controller;

import com.example.demo.crm.dto.DealRequestDTO;
import com.example.demo.crm.dto.DealResponseDTO;
import com.example.demo.crm.dto.LostSaleAnalysisReportDTO;
import com.example.demo.crm.enums.DealStatus;
import com.example.demo.crm.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/deals")
@RequiredArgsConstructor
public class DealController {

    private final DealService dealService;

    @GetMapping
    public ResponseEntity<List<DealResponseDTO>> getAll() {
        return ResponseEntity.ok(dealService.getAll());
    }

    @PostMapping
    public ResponseEntity<DealResponseDTO> create(@Valid @RequestBody DealRequestDTO dto) {
        return ResponseEntity.ok(dealService.create(dto));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<DealResponseDTO> updateStatus(
            @PathVariable Long id,
            @RequestParam DealStatus status,
            @RequestParam(required = false) Long lostReasonId) {
        return ResponseEntity.ok(dealService.updateStatus(id, status, lostReasonId));
    }

    @GetMapping("/reports/lost-sales")
    public ResponseEntity<LostSaleAnalysisReportDTO> getLostSaleReport() {
        return ResponseEntity.ok(dealService.getLostSaleAnalysisReport());
    }
}

