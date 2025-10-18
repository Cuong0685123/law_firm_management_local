package com.lawfirm.service;

import com.lawfirm.model.Staff;
import com.lawfirm.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private Staff currentUser; // Lưu tạm thông tin người đang đăng nhập

    // ==========================
    // 🔐 ĐĂNG NHẬP
    // ==========================
    public Staff login(String username, String password) {
        Staff staff = staffRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Tài khoản không tồn tại"));

        System.out.println("🔑 Đăng nhập: " + username + " (DB role=" + staff.getRole() + ")");

        if (!passwordEncoder.matches(password, staff.getPassword())) {
            throw new RuntimeException("Sai mật khẩu");
        }

        if (!staff.isEnabled()) {
            throw new RuntimeException("Tài khoản đã bị vô hiệu hóa");
        }

        this.currentUser = staff;
        return staff;
    }

    public void logout() {
        this.currentUser = null;
    }

    public Staff getCurrentUser() {
        return currentUser;
    }

    public boolean isAdmin() {
        return currentUser != null && "ADMIN".equalsIgnoreCase(currentUser.getRole());
    }

    // ==========================
    // 👥 CRUD NHÂN VIÊN
    // ==========================
    public List<Staff> getAll() {
        if (!isAdmin()) {
            throw new SecurityException("Bạn không có quyền ADMIN để xem danh sách nhân viên.");
        }
        return staffRepository.findAll();
    }

    public Staff create(Staff staff) {
        if (!isAdmin()) {
            throw new SecurityException("Bạn không có quyền ADMIN để thêm nhân viên mới.");
        }

        if (staffRepository.existsByUsername(staff.getUsername())) {
            throw new RuntimeException("Tên đăng nhập đã tồn tại");
        }

        staff.setPassword(passwordEncoder.encode(staff.getPassword()));
        staff.setEnabled(true);
        return staffRepository.save(staff);
    }

    public Staff update(Long id, Staff updated) {
        if (!isAdmin()) {
            throw new SecurityException("Bạn không có quyền ADMIN để sửa thông tin nhân viên.");
        }

        Staff existing = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên."));

        existing.setFullName(updated.getFullName());
        existing.setPosition(updated.getPosition());
        existing.setRole(updated.getRole());
        existing.setMonthlyFee(updated.getMonthlyFee());
        existing.setEnabled(updated.isEnabled());

        return staffRepository.save(existing);
    }

    public void delete(Long id) {
        if (!isAdmin()) {
            throw new SecurityException("Bạn không có quyền ADMIN để xóa nhân viên.");
        }

        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên."));
        staffRepository.delete(staff);
    }
}
