package com.relatiq.crm_backend.controller;

import com.relatiq.crm_backend.dto.AuthRequest;
import com.relatiq.crm_backend.dto.AuthResponse;
import com.relatiq.crm_backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }
}
