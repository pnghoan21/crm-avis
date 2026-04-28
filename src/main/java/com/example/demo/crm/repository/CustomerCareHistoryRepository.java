package com.example.demo.crm.repository;

import com.example.demo.crm.dto.PerformanceStat;
import com.example.demo.crm.entity.CustomerCareHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerCareHistoryRepository extends JpaRepository<CustomerCareHistory, Long> {
       List<CustomerCareHistory> findByCustomerIdOrderByCreatedAtDesc(Long customerId);

       Page<CustomerCareHistory> findByCustomerId(Long customerId, Pageable pageable);

       @Query("SELECT c.createdBy.username AS employeeName, COUNT(c) AS interactionCount " +
                     "FROM CustomerCareHistory c " +
                     "WHERE c.createdAt >= :startDate AND c.createdAt <= :endDate AND c.createdBy IS NOT NULL " +
                     "GROUP BY c.createdBy.username")
       List<PerformanceStat> countInteractionsByEmployee(
                     @org.springframework.data.repository.query.Param("startDate") java.time.LocalDateTime startDate,
                     @org.springframework.data.repository.query.Param("endDate") java.time.LocalDateTime endDate);
}
