package com.example.demo.crm.repository;

import com.example.demo.crm.entity.CustomerCareHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerCareHistoryRepository extends JpaRepository<CustomerCareHistory, Long> {
    List<CustomerCareHistory> findByCustomerIdOrderByCreatedAtDesc(Long customerId);
    Page<CustomerCareHistory> findByCustomerId(Long customerId, Pageable pageable);
}
