package com.example.demo.crm.mapper;

import com.example.demo.crm.dto.ComplaintRequest;
import com.example.demo.crm.dto.ComplaintResponse;
import com.example.demo.crm.entity.CustomerComplaint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ComplaintMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    CustomerComplaint toEntity(ComplaintRequest request);

    ComplaintResponse toResponse(CustomerComplaint entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntity(ComplaintRequest request, @MappingTarget CustomerComplaint entity);
}
