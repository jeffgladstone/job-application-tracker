package com.example.tracker.controller;

import com.example.tracker.dto.SignUpRequestDTO;
import com.example.tracker.model.User;
import com.example.tracker.security.JwtUtil;
import com.example.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000") // allow React frontend to call
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }

        // Generate JWT
        String token = jwtUtil.generateToken(user.getEmail());
        return token;
    }

    @PostMapping("/signup")
    public Map<String, String> signup(@RequestBody SignUpRequestDTO request) {
        User user = userService.signup(request);

        String token = jwtUtil.generateToken(user.getEmail());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }

}
