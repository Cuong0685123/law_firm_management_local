package com.lawfirm.controller;

import com.lawfirm.model.Staff;
import com.lawfirm.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class StaffController {

    @Autowired
    private StaffService staffService;

    // =====================
    // 🔐 Đăng nhập / Đăng xuất
    // =====================
    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody Staff loginRequest) {
    try {
        Staff staff = staffService.login(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(staff);
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}


    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        staffService.logout();
        return ResponseEntity.ok("Đăng xuất thành công");
    }

    // =====================
    // 👥 CRUD Staff
    // =====================

    @GetMapping("/staff")
    public ResponseEntity<?> getAllStaff() {
        try {
            List<Staff> list = staffService.getAll();
            return ResponseEntity.ok(list);
        } catch (SecurityException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/staff")
    public ResponseEntity<?> createStaff(@RequestBody Staff staff) {
        try {
            Staff saved = staffService.create(staff);
            return ResponseEntity.ok(saved);
        } catch (SecurityException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/staff/{id}")
    public ResponseEntity<?> updateStaff(@PathVariable Long id, @RequestBody Staff staff) {
        try {
            Staff updated = staffService.update(id, staff);
            return ResponseEntity.ok(updated);
        } catch (SecurityException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/staff/{id}")
    public ResponseEntity<?> deleteStaff(@PathVariable Long id) {
        try {
            staffService.delete(id);
            return ResponseEntity.ok("Xóa nhân viên thành công");
        } catch (SecurityException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
