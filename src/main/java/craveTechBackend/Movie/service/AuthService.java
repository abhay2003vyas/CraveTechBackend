package craveTechBackend.Movie.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import craveTechBackend.Movie.models.User;
import craveTechBackend.Movie.repository.UserRepository;
import craveTechBackend.Movie.security.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;


    public String register(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {

            return "User already exists";
        }

        user.setPassword(
                encoder.encode(user.getPassword())
        );

        userRepository.save(user);

        return "User registered successfully";
    }


    public Map<String, String> login(User user) {

        User existingUser =
                userRepository.findByEmail(user.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        if (!encoder.matches(
                user.getPassword(),
                existingUser.getPassword()
        )) {

            throw new RuntimeException("Invalid password");
        }

        String token =
                jwtUtil.generateToken(existingUser.getEmail());

        Map<String, String> response =
                new HashMap<>();

        response.put("token", token);

        return response;
    }
}
