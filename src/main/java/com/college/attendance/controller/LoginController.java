package com.college.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.college.attendance.dto.LoginRequestDTO;
import com.college.attendance.dto.LoginResponseDTO;
import com.college.attendance.service.LoginService;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {
        return loginService.login(request);
    }
}
