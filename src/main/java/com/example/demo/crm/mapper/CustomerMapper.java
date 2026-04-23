package com.example.demo.crm.mapper;

import com.example.demo.crm.dto.CustomerDetailResponse;
import com.example.demo.crm.dto.CustomerRequest;
import com.example.demo.crm.dto.CustomerResponse;
import com.example.demo.crm.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "assignedSales", ignore = true)
    Customer toEntity(CustomerRequest request);

    @Mapping(target = "assignedSalesId", source = "assignedSales.id")
    @Mapping(target = "assignedSalesName", source = "assignedSales.username")
    CustomerResponse toResponse(Customer entity);

    @Mapping(target = "assignedSalesId", source = "assignedSales.id")
    @Mapping(target = "assignedSalesName", source = "assignedSales.username")
    @Mapping(target = "careHistory", ignore = true)
    @Mapping(target = "complaints", ignore = true)
    CustomerDetailResponse toDetailResponse(Customer entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "assignedSales", ignore = true)
    void updateEntity(CustomerRequest request, @MappingTarget Customer entity);
}
