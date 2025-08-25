package com.lawfirm.controller;

import com.lawfirm.dto.ReportDto;
import com.lawfirm.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping
    public ResponseEntity<ReportDto> create(@RequestBody ReportDto dto) {
        return ResponseEntity.ok(reportService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<ReportDto>> getAll() {
        return ResponseEntity.ok(reportService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(reportService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReportDto> update(@PathVariable Long id, @RequestBody ReportDto dto) {
        return ResponseEntity.ok(reportService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        reportService.delete(id);
        return ResponseEntity.ok("Deleted report with id " + id);
    }
}
