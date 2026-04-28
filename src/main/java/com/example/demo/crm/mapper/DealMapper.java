package com.example.demo.crm.mapper;

import com.example.demo.crm.dto.DealRequestDTO;
import com.example.demo.crm.dto.DealResponseDTO;
import com.example.demo.crm.entity.Deal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {LostReasonMapper.class})
public interface DealMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "lostReason", ignore = true)
    @Mapping(target = "assignedEmployee", ignore = true)
    Deal toEntity(DealRequestDTO dto);

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "customerName", source = "customer.name")
    @Mapping(target = "assignedEmployeeId", source = "assignedEmployee.id")
    @Mapping(target = "assignedEmployeeName", source = "assignedEmployee.username")
    DealResponseDTO toDto(Deal entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "lostReason", ignore = true)
    @Mapping(target = "assignedEmployee", ignore = true)
    void updateEntityFromDto(DealRequestDTO dto, @MappingTarget Deal entity);
}
