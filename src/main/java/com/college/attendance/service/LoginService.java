package com.college.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.attendance.dto.LoginRequestDTO;
import com.college.attendance.dto.LoginResponseDTO;
import com.college.attendance.model.User;
import com.college.attendance.repository.UserRepository;
import com.college.attendance.security.UserSession;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public LoginResponseDTO login(LoginRequestDTO request) {

        User user = userRepository
                .findByEmailAndPassword(
                        request.getEmail(),
                        request.getPassword()
                )
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        // 🔥 Store logged-in user ID in session
        UserSession.setLoggedInUserId(user.getId());

        return new LoginResponseDTO(
                user.getId(),
                user.getName(),
                user.getRole(),
                "Login Successful"
        );
    }
}
