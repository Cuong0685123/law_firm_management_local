package com.lawfirm.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CaseDto {
    private Long id;
    private String code;
    private String category;
    private String requestContent;
    private String legalRelation;
    private String objective;
    private String applicableLaw;
    private String resolvingAgency;
    private String product;
    private String result;
    private BigDecimal fee;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long clientId;  // chỉ map ID, tránh vòng lặp
}
