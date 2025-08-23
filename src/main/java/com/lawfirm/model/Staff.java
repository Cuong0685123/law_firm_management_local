package com.lawfirm.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "staff")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ====== Thông tin đăng nhập ======
    @Column(unique = true, nullable = false)
    private String username;   // hoặc email

    @Column(nullable = false)
    private String password;

    private boolean enabled = true;
    private boolean accountNonLocked = true;

    // ====== Thông tin cá nhân ======
    private Integer orderNumber;
    private String fullName;
    private LocalDate birthDate;

    private String idNumber;
    private LocalDate idIssueDate;
    private String idIssuePlace;

    private String socialInsuranceBook;
    private String healthInsurance;
    private String laborBook;

    // ====== Chức vụ & nghề nghiệp ======
    private String role;          // Ví dụ: ADMIN, STAFF, LAWYER
    private String position;      // Ví dụ: Luật sư, Thư ký
    private String otherPositions;

    private LocalDate joinDate;
    private LocalDate apprenticeshipStart;
    private LocalDate apprenticeshipEnd;
    private LocalDate lawyerStartDate;

    // ====== Thu nhập ======
    private BigDecimal monthlyFee;
    private BigDecimal benefitLevel;

    @Lob
    private String incomeTable; // JSON / text

    // ====== Quan hệ với Case ======
    @ManyToMany(mappedBy = "assignedStaff")
    private Set<CasesEntity> cases;
}
