package com.example.demo.crm.mapper;

import com.example.demo.crm.dto.ActivityRequestDTO;
import com.example.demo.crm.dto.ActivityResponseDTO;
import com.example.demo.crm.entity.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ActivityMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "assignedEmployee", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "completedAt", ignore = true)
    Activity toEntity(ActivityRequestDTO dto);

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "customerName", source = "customer.name")
    @Mapping(target = "assignedEmployeeId", source = "assignedEmployee.id")
    @Mapping(target = "assignedEmployeeName", source = "assignedEmployee.username")
    ActivityResponseDTO toDto(Activity entity);
}
