package com.LibraryManagement.controller;

import com.LibraryManagement.dto.LoginReqDto;
import com.LibraryManagement.dto.RegisterUserDto;
import com.LibraryManagement.entity.Users;
import com.LibraryManagement.service.AuthenticateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {
    private final AuthenticateService authenticateService;

    @PostMapping("/registeradmin")
    public ResponseEntity<Users> registerAdminUser(@RequestBody RegisterUserDto  registerUserDto) {
        return ResponseEntity.ok(authenticateService.registerAdmin(registerUserDto));
    }
}
