package com.example.security.controller;

import com.example.security.dto.UserDto;
import com.example.security.dto.UserResponse;
import com.example.security.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/save")
    public ResponseEntity<UserResponse> save(@Valid @RequestBody UserDto request) {
        return ResponseEntity.ok(authenticationService.save(request));
    }

    @PostMapping("/auth")
    public ResponseEntity<UserResponse> auth(@RequestBody UserDto request) {
        return ResponseEntity.ok(authenticationService.auth(request));
    }
}
