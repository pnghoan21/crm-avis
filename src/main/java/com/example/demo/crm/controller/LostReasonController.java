package com.example.demo.crm.controller;

import com.example.demo.crm.dto.LostReasonDTO;
import com.example.demo.crm.service.LostReasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/lost-reasons")
@RequiredArgsConstructor
public class LostReasonController {

    private final LostReasonService service;

    @GetMapping
    public ResponseEntity<List<LostReasonDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<LostReasonDTO> create(@Valid @RequestBody LostReasonDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LostReasonDTO> update(@PathVariable Long id, @Valid @RequestBody LostReasonDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

