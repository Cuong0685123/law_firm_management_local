package com.lawfirm.service;

import com.lawfirm.dto.ContractDto;
import com.lawfirm.model.CasesEntity;
import com.lawfirm.model.Contract;
import com.lawfirm.repository.CaseRepository;
import com.lawfirm.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;
    private final CaseRepository caseRepository;

    // Convert Entity -> DTO
    private ContractDto toDto(Contract contract) {
        return ContractDto.builder()
                .id(contract.getId())
                .code(contract.getCode())
                .content(contract.getContent())
                .signDate(contract.getSignDate())
                .fee(contract.getFee())
                .caseId(contract.getCaseEntity() != null ? contract.getCaseEntity().getId() : null)
                .build();
    }

    // Convert DTO -> Entity
    private Contract toEntity(ContractDto dto) {
        CasesEntity caseEntity = null;
        if (dto.getCaseId() != null) {
            caseEntity = caseRepository.findById(dto.getCaseId())
                    .orElseThrow(() -> new RuntimeException("Case not found with id: " + dto.getCaseId()));
        }

        return Contract.builder()
                .id(dto.getId())
                .code(dto.getCode())
                .content(dto.getContent())
                .signDate(dto.getSignDate())
                .fee(dto.getFee())
                .caseEntity(caseEntity)
                .build();
    }

    // CREATE
    public ContractDto create(ContractDto dto) {
        Contract contract = toEntity(dto);
        return toDto(contractRepository.save(contract));
    }

    // READ all
    public List<ContractDto> getAll() {
        return contractRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // READ by id
    public ContractDto getById(Long id) {
        return contractRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Contract not found with id: " + id));
    }

    // UPDATE
    public ContractDto update(Long id, ContractDto dto) {
        Contract existing = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found with id: " + id));

        existing.setCode(dto.getCode());
        existing.setContent(dto.getContent());
        existing.setSignDate(dto.getSignDate());
        existing.setFee(dto.getFee());

        if (dto.getCaseId() != null) {
            CasesEntity caseEntity = caseRepository.findById(dto.getCaseId())
                    .orElseThrow(() -> new RuntimeException("Case not found with id: " + dto.getCaseId()));
            existing.setCaseEntity(caseEntity);
        }

        return toDto(contractRepository.save(existing));
    }

    // DELETE
    public void delete(Long id) {
        if (!contractRepository.existsById(id)) {
            throw new RuntimeException("Contract not found with id: " + id);
        }
        contractRepository.deleteById(id);
    }
}
