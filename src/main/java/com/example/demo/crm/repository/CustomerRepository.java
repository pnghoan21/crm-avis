package com.example.demo.crm.repository;

import com.example.demo.crm.entity.Customer;
import com.example.demo.crm.enums.CustomerType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByPhone(String phone);
    Optional<Customer> findByEmail(String email);

    @Query("SELECT c FROM Customer c WHERE " +
           "(:type IS NULL OR c.type = :type) AND " +
           "(:salesId IS NULL OR c.assignedSales.id = :salesId) AND " +
           "(:keyword IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "OR c.phone LIKE CONCAT('%', :keyword, '%'))")
    Page<Customer> searchCustomers(@Param("type") CustomerType type,
                                 @Param("salesId") Long salesId,
                                 @Param("keyword") String keyword,
                                 Pageable pageable);

    Page<Customer> findByAssignedSalesId(Long salesId, Pageable pageable);
}
