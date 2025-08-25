package com.lawfirm.controller;

import com.lawfirm.dto.ContractDto;
import com.lawfirm.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @PostMapping
    public ResponseEntity<ContractDto> create(@RequestBody ContractDto dto) {
        return ResponseEntity.ok(contractService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<ContractDto>> getAll() {
        return ResponseEntity.ok(contractService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(contractService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContractDto> update(@PathVariable Long id, @RequestBody ContractDto dto) {
        return ResponseEntity.ok(contractService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        contractService.delete(id);
        return ResponseEntity.ok("Deleted contract with id " + id);
    }
}
