package com.example.artwebsitebe.controller.auth;

import com.example.artwebsitebe.dto.AuthResponseDTO;
import com.example.artwebsitebe.dto.LoginRequestDTO;
import com.example.artwebsitebe.dto.RegisterRequestDTO;
import com.example.artwebsitebe.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(
            value = "/register",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO request) {
        authService.register(request);
        return ResponseEntity.ok("Register successfully");
    }

    @PostMapping(
            value = "/login",
            consumes = "application/json",
            produces = "application/json"
    )
    public AuthResponseDTO login(@RequestBody LoginRequestDTO request) {
        return authService.login(request);
    }

    @GetMapping("/test")
    public String test() {
        return "OK";
    }
}