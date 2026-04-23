package com.example.demo.crm.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerDetailResponse extends CustomerResponse {
    private String address;
    private String taxCode;
    private String source;
    private List<CareHistoryResponse> careHistory;
    private List<ComplaintResponse> complaints;
}
