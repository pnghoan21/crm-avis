package com.example.demo.crm.service;

import com.example.demo.crm.dto.LostReasonDTO;
import com.example.demo.crm.entity.LostReason;
import com.example.demo.crm.mapper.LostReasonMapper;
import com.example.demo.crm.repository.LostReasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LostReasonService {

    private final LostReasonRepository repository;
    private final LostReasonMapper mapper;

    public List<LostReasonDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public LostReasonDTO create(LostReasonDTO dto) {
        LostReason entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    public LostReasonDTO update(Long id, LostReasonDTO dto) {
        LostReason entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lý do thất bại với ID: " + id));
        entity.setReasonName(dto.getReasonName());
        entity.setDescription(dto.getDescription());
        return mapper.toDto(repository.save(entity));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
