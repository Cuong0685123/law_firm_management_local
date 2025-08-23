package com.lawfirm.controller;

import com.lawfirm.dto.CaseDto;
import com.lawfirm.service.CaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cases")
@RequiredArgsConstructor
public class CaseController {

    private final CaseService caseService;

    @PostMapping
    public ResponseEntity<CaseDto> create(@RequestBody CaseDto dto) {
        return ResponseEntity.ok(caseService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<CaseDto>> getAll() {
        return ResponseEntity.ok(caseService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(caseService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CaseDto> update(@PathVariable Long id, @RequestBody CaseDto dto) {
        return ResponseEntity.ok(caseService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        caseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
