package com.lawfirm.controller;

import com.lawfirm.model.CaseFile;
import com.lawfirm.service.CaseFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/cases/{caseId}/files")
@RequiredArgsConstructor
public class CaseFileController {

    private final CaseFileService caseFileService;

    @PostMapping("/upload")
    public ResponseEntity<CaseFile> uploadFile(@PathVariable Long caseId, @RequestParam("file") MultipartFile file)
            throws IOException {
        return ResponseEntity.ok(caseFileService.uploadFile(caseId, file));
    }

    @GetMapping
    public ResponseEntity<List<CaseFile>> getFiles(@PathVariable Long caseId) {
        return ResponseEntity.ok(caseFileService.getFilesByCase(caseId));
    }

    @GetMapping("/{fileId}/download")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long fileId) throws IOException {
        byte[] data = caseFileService.downloadFile(fileId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"file.pdf\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(data);
    }

    @DeleteMapping("/{fileId}")
    public ResponseEntity<Void> deleteFile(@PathVariable Long fileId) throws IOException {
        caseFileService.deleteFile(fileId);
        return ResponseEntity.noContent().build();
    }
}
