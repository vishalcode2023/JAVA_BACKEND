package com.Incture.JavaFinalProject.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.Incture.JavaFinalProject.Dto.AuthResponse;
import com.Incture.JavaFinalProject.Dto.LoginResponse;
import com.Incture.JavaFinalProject.Dto.RegisterRequest;
import com.Incture.JavaFinalProject.Services.AuthServices;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthController {

    private final AuthServices authServices;

    // User Routers

    @PostMapping("/user/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody RegisterRequest registerRequest) {
        AuthResponse response = authServices.registerUser(registerRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/user/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginResponse loginResponse) {
        AuthResponse response = authServices.loginUser(loginResponse);
        return ResponseEntity.ok(response);
    }

    // Admin Routers

    @PostMapping("/admin/register")
    public ResponseEntity<AuthResponse> Adminregister(@RequestBody RegisterRequest registerRequest) {
        AuthResponse response = authServices.Adminregister(registerRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/admin/login")
    public ResponseEntity<AuthResponse> Adminlogin(@RequestBody LoginResponse loginResponse) {
        AuthResponse response = authServices.Adminlogin(loginResponse);
        return ResponseEntity.ok(response);
    }

}
