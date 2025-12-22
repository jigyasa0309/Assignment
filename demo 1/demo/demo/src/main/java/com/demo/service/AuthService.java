package com.demo.service;


import com.demo.config.JwtUtil;
import com.demo.dto.AuthResponse;
import com.demo.dto.UserLoginRequest;
import com.demo.entity.User;
import com.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class  AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(UserLoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        String token = JwtUtil.generateToken(user.getUsername());
        return new AuthResponse(token,user.getUsername());
    }
}
