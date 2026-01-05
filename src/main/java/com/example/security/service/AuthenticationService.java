package com.example.security.service;

import com.example.security.dto.UserDto;
import com.example.security.dto.UserResponse;
import com.example.security.entity.User;
import com.example.security.enums.Role;
import com.example.security.exception.UserNotFound;
import com.example.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public UserResponse save(UserDto request) {
        if (userRepository.findByUserName(request.getUserName()).isPresent()) {
            throw new IllegalArgumentException("Username already exists!");
        }

        User user = User.builder()
                .userName(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return UserResponse.builder().token(token).build();
    }



    public UserResponse auth(UserDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(),request.getPassword()));
        User user=userRepository.findByUserName(request.getUserName()).orElseThrow(()->new UserNotFound("User Not Found"));
        String token=jwtService.generateToken(user);
        return UserResponse.builder().token(token).build();
    }
}
