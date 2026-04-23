package com.example.demo.crm.service;

import com.example.demo.crm.dto.ComplaintRequest;
import com.example.demo.crm.dto.ComplaintResponse;
import com.example.demo.crm.entity.Customer;
import com.example.demo.crm.entity.CustomerComplaint;
import com.example.demo.crm.mapper.ComplaintMapper;
import com.example.demo.crm.repository.CustomerComplaintRepository;
import com.example.demo.crm.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComplaintService {
    private final CustomerComplaintRepository complaintRepository;
    private final CustomerRepository customerRepository;
    private final ComplaintMapper complaintMapper;

    @Transactional
    public ComplaintResponse addComplaint(Long customerId, ComplaintRequest request) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        
        CustomerComplaint complaint = complaintMapper.toEntity(request);
        complaint.setCustomer(customer);
        
        return complaintMapper.toResponse(complaintRepository.save(complaint));
    }

    @Transactional
    public ComplaintResponse updateComplaint(Long complaintId, ComplaintRequest request) {
        CustomerComplaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
        
        complaintMapper.updateEntity(request, complaint);
        return complaintMapper.toResponse(complaintRepository.save(complaint));
    }

    public List<ComplaintResponse> getComplaints(Long customerId) {
        return complaintRepository.findByCustomerIdOrderByCreatedAtDesc(customerId)
                .stream().map(complaintMapper::toResponse).collect(Collectors.toList());
    }
}
