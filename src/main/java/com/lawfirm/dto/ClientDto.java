package com.lawfirm.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDto {
    private Long id;
    private String fullName;
    private LocalDate birthDate;
    private String idNumber;
    private String address;
    private String phone;
    private String email;
}
