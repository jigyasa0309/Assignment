package com.demo.controller;


import com.demo.dto.AuthResponse;
import com.demo.dto.UserLoginRequest;
import com.demo.dto.UserRegisterRequest;
import com.demo.entity.User;
import com.demo.repository.UserRepository;
import com.demo.service.AuthService;
import com.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;

    private final AuthService authService;


    @PostMapping("/register")
    public User register(@Valid @RequestBody UserRegisterRequest request) {
          if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return userService.register(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody UserLoginRequest request) {
        return authService.login(request);
    }
}
