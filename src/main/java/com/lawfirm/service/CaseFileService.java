package com.lawfirm.service;

import com.lawfirm.model.CaseFile;
import com.lawfirm.model.CasesEntity;
import com.lawfirm.repository.CaseFileRepository;
import com.lawfirm.repository.CaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CaseFileService {

    private final CaseFileRepository fileRepository;
    private final CaseRepository caseRepository;

    @Value("${file.upload-dir:uploads}")
    private String uploadDir;

    public CaseFile uploadFile(Long caseId, MultipartFile file) throws IOException {
        CasesEntity caseEntity = caseRepository.findById(caseId)
                .orElseThrow(() -> new RuntimeException("Case not found with ID: " + caseId));

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        CaseFile caseFile = CaseFile.builder()
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .filePath(filePath.toString())
                .caseEntity(caseEntity)
                .build();

        return fileRepository.save(caseFile);
    }

    public List<CaseFile> getFilesByCase(Long caseId) {
        return fileRepository.findByCaseEntityId(caseId);
    }

    public byte[] downloadFile(Long fileId) throws IOException {
        CaseFile file = fileRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found"));

        return Files.readAllBytes(Path.of(file.getFilePath()));
    }

    public void deleteFile(Long fileId) throws IOException {
        CaseFile file = fileRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found"));
        Files.deleteIfExists(Path.of(file.getFilePath()));
        fileRepository.deleteById(fileId);
    }
}
