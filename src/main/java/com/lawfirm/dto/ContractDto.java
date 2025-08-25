package com.lawfirm.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractDto {
    private Long id;
    private String code;
    private String content;
    private LocalDate signDate;
    private BigDecimal fee;
    private Long caseId; // lưu id của case liên kết
}
