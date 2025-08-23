package com.lawfirm.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "report")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // daily, semi_annual, annual
    private LocalDate generatedDate = LocalDate.now();

    @Lob
    private String content;

    // Quan hệ N-N với Case
    @ManyToMany
    @JoinTable(
            name = "report_case",
            joinColumns = @JoinColumn(name = "report_id"),
            inverseJoinColumns = @JoinColumn(name = "case_id")
    )
    private Set<CasesEntity> cases;
}
