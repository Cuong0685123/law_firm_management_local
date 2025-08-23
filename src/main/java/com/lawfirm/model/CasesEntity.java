package com.lawfirm.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "cases")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CasesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    private String category;
    
    @Lob
    private String requestContent;
    @Lob
    private String legalRelation;
    @Lob
    private String objective;
    @Lob
    private String applicableLaw;

    private String resolvingAgency;

    @Lob
    private String product;
    @Lob
    private String result;

    private BigDecimal fee;

    private LocalDate startDate = LocalDate.now();
    private LocalDate endDate;

    // Quan hệ
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToMany
    @JoinTable(
            name = "case_staff",
            joinColumns = @JoinColumn(name = "case_id"),
            inverseJoinColumns = @JoinColumn(name = "staff_id")
    )
    private Set<Staff> assignedStaff;

    @OneToOne(mappedBy = "caseEntity", cascade = CascadeType.ALL)
    private Contract contract;

    @ManyToMany(mappedBy = "cases")
    private Set<Report> reports;
}
