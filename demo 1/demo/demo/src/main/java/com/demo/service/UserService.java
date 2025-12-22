package com.demo.service;


import com.demo.entity.User;
import com.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;

    public User register(User user) {
        return userRepository.save(user);
    }
}
