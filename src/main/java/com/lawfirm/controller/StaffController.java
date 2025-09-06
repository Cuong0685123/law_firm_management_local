package com.lawfirm.controller;

import com.lawfirm.dto.LoginRequest;
import com.lawfirm.dto.RegisterRequest;
import com.lawfirm.model.Staff;
import com.lawfirm.service.StaffService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    // ====== Đăng ký ======
   @PostMapping("/register")
public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
    Staff staff = staffService.register(
            request.getUsername(),
            request.getPassword(),
            request.getFullName(),
            request.getRole()
    );
    staff.setPassword(null);
    return ResponseEntity.ok(staff);
}

@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    boolean success = staffService.login(request.getUsername(), request.getPassword());
    if (success) {
        return ResponseEntity.ok(
            Map.of(
                "status", "success",
                "username", request.getUsername(),
                "message", "Đăng nhập thành công!"
            )
        );
    }
    return ResponseEntity.status(401).body(
        Map.of(
            "status", "fail",
            "message", "Sai tên đăng nhập hoặc mật khẩu!"
        )
    );
}

}
