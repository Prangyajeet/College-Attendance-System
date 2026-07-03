package com.college.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.attendance.dto.LoginRequestDTO;
import com.college.attendance.dto.LoginResponseDTO;
import com.college.attendance.model.Admin;
import com.college.attendance.model.User;
import com.college.attendance.repository.AdminRepository;
import com.college.attendance.repository.UserRepository;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    public LoginResponseDTO login(LoginRequestDTO request) {

        // ✅ 0️⃣ DEAN LOGIN (ADDED)
        if (request.getEmail().equals("dean@gmail.com") &&
            request.getPassword().equals("dean")) {

            return new LoginResponseDTO(
                    "DEAN01",
                    "Dean",
                    "DEAN",
                    "ALL",
                    "Dean Login Successful"
            );
        }

        // 1️⃣ Check Admin Login
        Admin admin = adminRepository.findByEmail(request.getEmail()).orElse(null);

        if (admin != null) {

            if (!admin.getPassword().equals(request.getPassword())) {
                throw new RuntimeException("Invalid password");
            }

            return new LoginResponseDTO(
                    admin.getAdminId(),
                    admin.getName(),
                    "ADMIN",
                    "ADMIN",
                    "Admin Login Successful"
            );
        }

        // 2️⃣ Check Faculty Login
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return new LoginResponseDTO(
                user.getFacultyId(),
                user.getName(),
                user.getRole(),
                user.getDepartment().getDepartmentName(),
                "Login Successful"
        );
    }
}