package com.LibraryManagement.service;

import com.LibraryManagement.dto.LoginReqDto;
import com.LibraryManagement.dto.LoginResponseDto;
import com.LibraryManagement.dto.RegisterUserDto;
import com.LibraryManagement.entity.Users;
import com.LibraryManagement.jwt.JwtService;
import com.LibraryManagement.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticateService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public Users registerNormalUser(RegisterUserDto registerUserDto) {
        if(userRepo.findByUserName(registerUserDto.getUserName()).isPresent()) throw new RuntimeException("Username already exists");
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        Users users = new Users();
        users.setEmail(registerUserDto.getEmail());
        users.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        users.setUserName(registerUserDto.getUserName());
        users.setRoles(roles);
        userRepo.save(users);
        return users;
    }

    public Users registerAdmin(RegisterUserDto registerUserDto) {
        if(userRepo.findByUserName(registerUserDto.getUserName()).isPresent()) throw new RuntimeException("Username already exists");
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");
        Users users = new Users();
        users.setEmail(registerUserDto.getEmail());
        users.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        users.setUserName(registerUserDto.getUserName());
        users.setRoles(roles);
        userRepo.save(users);
        return users;
    }

    public LoginResponseDto login(LoginReqDto loginReqDto) {
         Authentication authentication = authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(loginReqDto.getUserName(), loginReqDto.getPassword())
         );
         Users user = userRepo.findByUserName(loginReqDto.getUserName()).orElseThrow(() -> new RuntimeException("Username not found"));
         String token = jwtService.generateToken(user);
         return LoginResponseDto.builder()
                 .token(token)
                 .userName(user.getUsername())
                 .roles(user.getRoles())
                 .build();
    }
}
