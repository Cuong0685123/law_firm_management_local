package com.lawfirm.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffDto {
    private Long id;
    private String username;
    private String fullName;
    private String role;
    private String position;
    private LocalDate joinDate;
    private BigDecimal monthlyFee;

 
    public StaffDto(Long id, String username, String fullName, String role) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.role = role;
    }
}
