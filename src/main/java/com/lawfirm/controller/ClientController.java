package com.lawfirm.controller;

import com.lawfirm.dto.ClientDto;
import com.lawfirm.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientDto> create(@RequestBody ClientDto dto) {
        return ResponseEntity.ok(clientService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> getAll() {
        return ResponseEntity.ok(clientService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> update(@PathVariable Long id, @RequestBody ClientDto dto) {
        return ResponseEntity.ok(clientService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.ok("Deleted client with id " + id);
    }
}
