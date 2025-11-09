package com.Incture.JavaFinalProject.Services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Incture.JavaFinalProject.Dto.AuthResponse;
import com.Incture.JavaFinalProject.Dto.LoginResponse;
import com.Incture.JavaFinalProject.Dto.RegisterRequest;
import com.Incture.JavaFinalProject.Entity.AdminModel;
import com.Incture.JavaFinalProject.Entity.UserModel;
import com.Incture.JavaFinalProject.Exception.CustomException;
import com.Incture.JavaFinalProject.Repository.AdminRepository;
import com.Incture.JavaFinalProject.Repository.UserRepository;
import com.Incture.JavaFinalProject.Utils.JwtCreation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class AuthServices {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final JwtCreation jwtCreation;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse registerUser(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new CustomException("Email is Already Existing");
        }

        UserModel userModel = UserModel.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(registerRequest.getRole())
                .build();

        userRepository.save(userModel);

        String token = jwtCreation.JwtGeneration(registerRequest.getEmail(), registerRequest.getRole());

        return AuthResponse.builder().token(token).message("Registered Done").build();
    }

    public AuthResponse loginUser(LoginResponse loginResponse) {

        UserModel userModel = userRepository.findByEmail(loginResponse.getEmail())
                .orElseThrow(() -> new CustomException("Email or Password is not valid"));

        if (!passwordEncoder.matches(loginResponse.getPassword(), userModel.getPassword())) {
            throw new CustomException("Email or Password is not valid");
        }

        String token = jwtCreation.JwtGeneration(userModel.getEmail(), userModel.getRole());
        return AuthResponse.builder()
                .token(token)
                .message("Login successful")
                .build();
    }

    // Admin Services
    public AuthResponse Adminregister(RegisterRequest registerRequest) {
        if (adminRepository.existsByEmail(registerRequest.getEmail())) {
            throw new CustomException("Email is Already Existing");
        }

        AdminModel adminModel = AdminModel.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();

        adminRepository.save(adminModel);

        String token = jwtCreation.JwtGeneration(registerRequest.getEmail(), registerRequest.getRole());
        return AuthResponse.builder().message("Register Done").token(token).build();
    }

    public AuthResponse Adminlogin(LoginResponse loginResponse) {
        AdminModel adminModel = adminRepository.findByEmail(loginResponse.getEmail())
                .orElseThrow(() -> new CustomException("Email or Password is invalid"));

        if (!passwordEncoder.matches(loginResponse.getPassword(), adminModel.getPassword())) {
            throw new CustomException("Email or Password is not valid");
        }

        String token = jwtCreation.JwtGeneration(adminModel.getEmail(), adminModel.getRole());

        return AuthResponse.builder().message("Login Done").token(token).build();
    }
}
