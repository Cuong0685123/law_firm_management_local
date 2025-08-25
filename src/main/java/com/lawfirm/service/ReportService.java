package com.lawfirm.service;

import com.lawfirm.dto.ReportDto;
import com.lawfirm.model.CasesEntity;
import com.lawfirm.model.Report;
import com.lawfirm.repository.CaseRepository;
import com.lawfirm.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final CaseRepository caseRepository;

    // Convert Entity -> DTO
    private ReportDto toDto(Report report) {
        return ReportDto.builder()
                .id(report.getId())
                .type(report.getType())
                .generatedDate(report.getGeneratedDate())
                .content(report.getContent())
                .caseIds(report.getCases() != null
                        ? report.getCases().stream().map(CasesEntity::getId).collect(Collectors.toSet())
                        : null)
                .build();
    }

    // Convert DTO -> Entity
    private Report toEntity(ReportDto dto) {
        Report report = new Report();
        report.setId(dto.getId());
        report.setType(dto.getType());
        report.setGeneratedDate(dto.getGeneratedDate());
        report.setContent(dto.getContent());

        if (dto.getCaseIds() != null) {
            Set<CasesEntity> cases = dto.getCaseIds().stream()
                    .map(caseId -> caseRepository.findById(caseId)
                            .orElseThrow(() -> new RuntimeException("Case not found with id: " + caseId)))
                    .collect(Collectors.toSet());
            report.setCases(cases);
        }
        return report;
    }

    // CREATE
    public ReportDto create(ReportDto dto) {
        Report report = toEntity(dto);
        return toDto(reportRepository.save(report));
    }

    // READ all
    public List<ReportDto> getAll() {
        return reportRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // READ by id
    public ReportDto getById(Long id) {
        return reportRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Report not found with id: " + id));
    }

    // UPDATE
    public ReportDto update(Long id, ReportDto dto) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found with id: " + id));

        report.setType(dto.getType());
        report.setGeneratedDate(dto.getGeneratedDate());
        report.setContent(dto.getContent());

        if (dto.getCaseIds() != null) {
            Set<CasesEntity> cases = dto.getCaseIds().stream()
                    .map(caseId -> caseRepository.findById(caseId)
                            .orElseThrow(() -> new RuntimeException("Case not found with id: " + caseId)))
                    .collect(Collectors.toSet());
            report.setCases(cases);
        }

        return toDto(reportRepository.save(report));
    }

    // DELETE
    public void delete(Long id) {
        if (!reportRepository.existsById(id)) {
            throw new RuntimeException("Report not found with id: " + id);
        }
        reportRepository.deleteById(id);
    }
}
