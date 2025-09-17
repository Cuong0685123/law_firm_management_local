package com.lawfirm.service;

import com.lawfirm.dto.LoginRequest;
import com.lawfirm.model.Staff;
import com.lawfirm.repository.StaffRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StaffService {

    public static boolean validate(LoginRequest loginRequest) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private final StaffRepository staffRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    // Đăng ký user mới
    public Staff register(String username, String password, String fullName, String role) {
        if (staffRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Tên đăng nhập đã tồn tại!");
        }

        Staff staff = Staff.builder()
                .username(username)
                .password(encoder.encode(password))
                .fullName(fullName)
                .role(role)
                .enabled(true)
                .accountNonLocked(true)
                .build();

        return staffRepository.save(staff);
    }

    // Đăng nhập
    public boolean login(String username, String password) {
        return staffRepository.findByUsername(username)
                .filter(staff -> staff.isEnabled() && staff.isAccountNonLocked())
                .map(staff -> encoder.matches(password, staff.getPassword()))
                .orElse(false);
    }
}
