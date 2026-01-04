package com.example.security.service;

import com.example.security.dto.UserDto;
import com.example.security.dto.UserResponse;
import com.example.security.entity.User;
import com.example.security.enums.Role;
import com.example.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;


    public UserResponse save(UserDto request) {
        User user = User.builder()
                .userName(request.getUserName())
                .password(request.getPassword())
                .nameSurname(request.getNameSurname())
                .role(Role.USER).build();

        userRepository.save(user);
        var token = jwtService.generateToken(user);
        return UserResponse.builder().token(token).build();
    }


    public UserResponse auth(UserDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(),request.getPassword()));
        User user=userRepository.findByUserName(request.getUserName()).orElseThrow();
        String token=jwtService.generateToken(user);
        return UserResponse.builder().token(token).build();
    }
}
