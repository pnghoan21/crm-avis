package com.example.demo.crm.repository;

import com.example.demo.crm.entity.LostReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LostReasonRepository extends JpaRepository<LostReason, Long> {
}
