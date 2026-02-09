package craveTechBackend.Books.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import craveTechBackend.Books.models.User;
import craveTechBackend.Books.service.AuthService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/register")
    public String register(@RequestBody User user) {

        return authService.register(user);
    }


    @PostMapping("/login")
    public Map<String, String> login(
            @RequestBody User user
    ) {

        return authService.login(user);
    }
}
