package com.example.demo.crm.service;

import com.example.demo.crm.dto.CareHistoryRequest;
import com.example.demo.crm.dto.CareHistoryResponse;
import com.example.demo.crm.entity.Customer;
import com.example.demo.crm.entity.CustomerCareHistory;
import com.example.demo.crm.entity.User;
import com.example.demo.crm.mapper.CareHistoryMapper;
import com.example.demo.crm.repository.CustomerCareHistoryRepository;
import com.example.demo.crm.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CareHistoryService {
    private final CustomerCareHistoryRepository careHistoryRepository;
    private final CustomerRepository customerRepository;
    private final CareHistoryMapper careHistoryMapper;

    @Transactional
    public CareHistoryResponse addCareHistory(Long customerId, CareHistoryRequest request, User currentUser) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        
        CustomerCareHistory history = careHistoryMapper.toEntity(request);
        history.setCustomer(customer);
        history.setCreatedBy(currentUser);
        
        return careHistoryMapper.toResponse(careHistoryRepository.save(history));
    }

    public List<CareHistoryResponse> getCareHistory(Long customerId) {
        return careHistoryRepository.findByCustomerIdOrderByCreatedAtDesc(customerId)
                .stream().map(careHistoryMapper::toResponse).collect(Collectors.toList());
    }
}
