package com.example.demo.crm.controller;

import com.example.demo.crm.dto.ActivityRequestDTO;
import com.example.demo.crm.dto.ActivityResponseDTO;
import com.example.demo.crm.dto.PerformanceReportDTO;
import com.example.demo.crm.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<ActivityResponseDTO>> getMyActivities(@PathVariable Long employeeId) {
        return ResponseEntity.ok(activityService.getMyActivities(employeeId));
    }

    @PostMapping
    public ResponseEntity<ActivityResponseDTO> create(@Valid @RequestBody ActivityRequestDTO dto) {
        return ResponseEntity.ok(activityService.create(dto));
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<ActivityResponseDTO> completeActivity(
            @PathVariable Long id,
            @RequestParam(required = false) String resultNote) {
        return ResponseEntity.ok(activityService.completeActivity(id, resultNote));
    }

    @GetMapping("/reports/performance")
    public ResponseEntity<PerformanceReportDTO> getPerformanceReport(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return ResponseEntity.ok(activityService.getPerformanceReport(startDate, endDate));
    }
}

