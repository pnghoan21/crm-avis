package com.example.demo.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceReportDTO {
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private List<PerformanceStat> performanceStats;
}
