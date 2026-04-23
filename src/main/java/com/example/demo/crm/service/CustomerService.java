package com.example.demo.crm.service;

import com.example.demo.crm.dto.CustomerDetailResponse;
import com.example.demo.crm.dto.CustomerRequest;
import com.example.demo.crm.dto.CustomerResponse;
import com.example.demo.crm.entity.Customer;
import com.example.demo.crm.entity.User;
import com.example.demo.crm.enums.CustomerStatus;
import com.example.demo.crm.enums.CustomerType;
import com.example.demo.crm.mapper.CareHistoryMapper;
import com.example.demo.crm.mapper.ComplaintMapper;
import com.example.demo.crm.mapper.CustomerMapper;
import com.example.demo.crm.repository.CustomerCareHistoryRepository;
import com.example.demo.crm.repository.CustomerComplaintRepository;
import com.example.demo.crm.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerCareHistoryRepository careHistoryRepository;
    private final CustomerComplaintRepository complaintRepository;
    private final CustomerMapper customerMapper;
    private final CareHistoryMapper careHistoryMapper;
    private final ComplaintMapper complaintMapper;

    public Page<CustomerResponse> searchCustomers(CustomerType type, Long salesId, String keyword, Pageable pageable) {
        return customerRepository.searchCustomers(type, salesId, keyword, pageable)
                .map(customerMapper::toResponse);
    }

    @Transactional
    public CustomerResponse createCustomer(CustomerRequest request, User currentUser) {
        if (customerRepository.findByPhone(request.getPhone()).isPresent()) {
            throw new RuntimeException("Phone number already exists");
        }
        Customer customer = customerMapper.toEntity(request);
        customer.setAssignedSales(currentUser);
        customer.setStatus(CustomerStatus.ACTIVE);
        return customerMapper.toResponse(customerRepository.save(customer));
    }

    @Transactional
    public CustomerResponse updateCustomer(Long id, CustomerRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customerMapper.updateEntity(request, customer);
        return customerMapper.toResponse(customerRepository.save(customer));
    }

    public CustomerDetailResponse getCustomerDetail(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        
        CustomerDetailResponse response = customerMapper.toDetailResponse(customer);
        
        response.setCareHistory(careHistoryRepository.findByCustomerIdOrderByCreatedAtDesc(id)
                .stream().map(careHistoryMapper::toResponse).collect(Collectors.toList()));
        
        response.setComplaints(complaintRepository.findByCustomerIdOrderByCreatedAtDesc(id)
                .stream().map(complaintMapper::toResponse).collect(Collectors.toList()));
        
        return response;
    }

    @Transactional
    public void assignSales(Long customerId, User sales) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setAssignedSales(sales);
        customerRepository.save(customer);
    }

    @Transactional
    public void autoCreateFromDeal(String name, String phone, String email, User sales) {
        if (customerRepository.findByPhone(phone).isPresent()) {
            return; // Already exists
        }
        Customer customer = new Customer();
        customer.setName(name);
        customer.setPhone(phone);
        customer.setEmail(email);
        customer.setType(CustomerType.INDIVIDUAL);
        customer.setAssignedSales(sales);
        customer.setStatus(CustomerStatus.ACTIVE);
        customer.setSource("DEAL");
        customerRepository.save(customer);
    }
}
