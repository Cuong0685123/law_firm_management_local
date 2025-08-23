package com.lawfirm.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contract")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @Lob
    private String content;

    private LocalDate signDate;
    private BigDecimal fee;

    // Liên kết 1-1 với Case
    @OneToOne
    @JoinColumn(name = "case_id", referencedColumnName = "id")
    private CasesEntity caseEntity;
}
