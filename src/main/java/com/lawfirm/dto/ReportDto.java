package com.lawfirm.dto;

import lombok.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportDto {
    private Long id;
    private String type; 
    private LocalDate generatedDate;
    private String content;
    private Set<Long> caseIds; // chỉ lưu ID các Case
}
