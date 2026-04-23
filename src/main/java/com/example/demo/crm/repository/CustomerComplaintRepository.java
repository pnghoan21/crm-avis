package com.example.demo.crm.repository;

import com.example.demo.crm.entity.CustomerComplaint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerComplaintRepository extends JpaRepository<CustomerComplaint, Long> {
    List<CustomerComplaint> findByCustomerIdOrderByCreatedAtDesc(Long customerId);
    Page<CustomerComplaint> findByCustomerId(Long customerId, Pageable pageable);
}
