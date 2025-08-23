package com.lawfirm.service;

import com.lawfirm.dto.ClientDto;
import com.lawfirm.model.Client;
import com.lawfirm.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    // Convert Entity -> DTO
    private ClientDto toDto(Client client) {
        return ClientDto.builder()
                .id(client.getId())
                .fullName(client.getFullName())
                .birthDate(client.getBirthDate())
                .idNumber(client.getIdNumber())
                .address(client.getAddress())
                .phone(client.getPhone())
                .email(client.getEmail())
                .build();
    }

    // Convert DTO -> Entity
    private Client toEntity(ClientDto dto) {
        return Client.builder()
                .id(dto.getId())
                .fullName(dto.getFullName())
                .birthDate(dto.getBirthDate())
                .idNumber(dto.getIdNumber())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .build();
    }

    // CREATE
    public ClientDto create(ClientDto dto) {
        Client client = toEntity(dto);
        return toDto(clientRepository.save(client));
    }

    // READ all
    public List<ClientDto> getAll() {
        return clientRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // READ by id
    public ClientDto getById(Long id) {
        return clientRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
    }

    // UPDATE
    public ClientDto update(Long id, ClientDto dto) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));

        client.setFullName(dto.getFullName());
        client.setBirthDate(dto.getBirthDate());
        client.setIdNumber(dto.getIdNumber());
        client.setAddress(dto.getAddress());
        client.setPhone(dto.getPhone());
        client.setEmail(dto.getEmail());

        return toDto(clientRepository.save(client));
    }

    // DELETE
    public void delete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Client not found with id: " + id);
        }
        clientRepository.deleteById(id);
    }
}
