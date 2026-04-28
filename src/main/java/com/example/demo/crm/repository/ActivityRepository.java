package com.example.demo.crm.repository;

import com.example.demo.crm.entity.Activity;
import com.example.demo.crm.enums.ActivityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    
    @Query("SELECT a FROM Activity a WHERE a.status = :status AND a.dueDate < :now")
    List<Activity> findActivitiesByStatusAndDueDateBefore(@Param("status") ActivityStatus status, @Param("now") LocalDateTime now);
    
    List<Activity> findByAssignedEmployeeIdOrderByDueDateAsc(Long employeeId);
}
