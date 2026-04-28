package com.example.demo.crm.mapper;

import com.example.demo.crm.dto.CareHistoryRequest;
import com.example.demo.crm.dto.CareHistoryResponse;
import com.example.demo.crm.entity.CustomerCareHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CareHistoryMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "relatedActivity", ignore = true)
    CustomerCareHistory toEntity(CareHistoryRequest request);

    @Mapping(target = "createdByUsername", source = "createdBy.username")
    CareHistoryResponse toResponse(CustomerCareHistory entity);
}
