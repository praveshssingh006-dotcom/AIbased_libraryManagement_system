package com.LibraryManagement.controller;

import com.LibraryManagement.dto.LoginReqDto;
import com.LibraryManagement.dto.LoginResponseDto;
import com.LibraryManagement.dto.RegisterUserDto;
import com.LibraryManagement.entity.Users;
import com.LibraryManagement.service.AuthenticateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthenticateService authenticateService;

    @PostMapping("/registernormaluser")
    public ResponseEntity<Users> registerNormalUser(@RequestBody RegisterUserDto registerUserDto) {
        log.info("registerNormalUser");
        return ResponseEntity.ok(authenticateService.registerNormalUser(registerUserDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginReqDto loginReqDto) {
        return ResponseEntity.ok(authenticateService.login(loginReqDto));
    }
}
