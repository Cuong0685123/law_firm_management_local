package com.lawfirm.service;

import com.lawfirm.dto.CaseDto;
import com.lawfirm.model.CasesEntity;
import com.lawfirm.model.Client;
import com.lawfirm.repository.CaseRepository;
import com.lawfirm.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CaseService {

    private final CaseRepository caseRepository;
    private final ClientRepository clientRepository;

    private CaseDto mapToDto(CasesEntity entity) {
        return CaseDto.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .category(entity.getCategory())
                .requestContent(entity.getRequestContent())
                .legalRelation(entity.getLegalRelation())
                .objective(entity.getObjective())
                .applicableLaw(entity.getApplicableLaw())
                .resolvingAgency(entity.getResolvingAgency())
                .product(entity.getProduct())
                .result(entity.getResult())
                .fee(entity.getFee())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .clientId(entity.getClient() != null ? entity.getClient().getId() : null)
                .build();
    }

    // Create
    public CaseDto create(CaseDto dto) {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        CasesEntity entity = CasesEntity.builder()
                .code(dto.getCode())
                .category(dto.getCategory())
                .requestContent(dto.getRequestContent())
                .legalRelation(dto.getLegalRelation())
                .objective(dto.getObjective())
                .applicableLaw(dto.getApplicableLaw())
                .resolvingAgency(dto.getResolvingAgency())
                .product(dto.getProduct())
                .result(dto.getResult())
                .fee(dto.getFee())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .client(client)
                .build();

        return mapToDto(caseRepository.save(entity));
    }

    // Read all
    public List<CaseDto> getAll() {
        return caseRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Read by ID
    public CaseDto getById(Long id) {
        return caseRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("Case not found"));
    }

    // Update
    public CaseDto update(Long id, CaseDto dto) {
        CasesEntity entity = caseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Case not found"));

        entity.setCode(dto.getCode());
        entity.setCategory(dto.getCategory());
        entity.setRequestContent(dto.getRequestContent());
        entity.setLegalRelation(dto.getLegalRelation());
        entity.setObjective(dto.getObjective());
        entity.setApplicableLaw(dto.getApplicableLaw());
        entity.setResolvingAgency(dto.getResolvingAgency());
        entity.setProduct(dto.getProduct());
        entity.setResult(dto.getResult());
        entity.setFee(dto.getFee());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());

        if (dto.getClientId() != null) {
            Client client = clientRepository.findById(dto.getClientId())
                    .orElseThrow(() -> new RuntimeException("Client not found"));
            entity.setClient(client);
        }

        return mapToDto(caseRepository.save(entity));
    }

    // Delete
    public void delete(Long id) {
        caseRepository.deleteById(id);
    }
}
