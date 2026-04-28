package com.example.demo.crm.mapper;

import com.example.demo.crm.dto.LostReasonDTO;
import com.example.demo.crm.entity.LostReason;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LostReasonMapper {
    LostReasonDTO toDto(LostReason entity);
    LostReason toEntity(LostReasonDTO dto);
}
