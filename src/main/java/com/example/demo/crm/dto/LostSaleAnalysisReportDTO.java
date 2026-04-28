package com.example.demo.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LostSaleAnalysisReportDTO {
    private long totalLostDeals;
    private List<LostDealStat> lostByEmployee;
    private List<LostDealStat> lostByCustomerNeed;
    private List<LostDealStat> lostByReason;
}
