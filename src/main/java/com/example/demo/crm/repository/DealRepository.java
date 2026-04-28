package com.example.demo.crm.repository;

import com.example.demo.crm.dto.LostDealStat;
import com.example.demo.crm.entity.Deal;
import com.example.demo.crm.enums.DealStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {

    @Query("SELECT d.assignedEmployee.username AS category, COUNT(d) AS totalCount " +
           "FROM Deal d WHERE d.status = 'LOST' AND d.assignedEmployee IS NOT NULL GROUP BY d.assignedEmployee.username")
    List<LostDealStat> countLostDealsByEmployee();

    @Query("SELECT d.customerNeed AS category, COUNT(d) AS totalCount " +
           "FROM Deal d WHERE d.status = 'LOST' GROUP BY d.customerNeed")
    List<LostDealStat> countLostDealsByCustomerNeed();

    @Query("SELECT d.lostReason.reasonName AS category, COUNT(d) AS totalCount " +
           "FROM Deal d WHERE d.status = 'LOST' AND d.lostReason IS NOT NULL GROUP BY d.lostReason.reasonName")
    List<LostDealStat> countLostDealsByReason();

    long countByStatus(DealStatus status);
}
