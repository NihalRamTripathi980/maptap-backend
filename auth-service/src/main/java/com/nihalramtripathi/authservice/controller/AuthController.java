package com.nihalramtripathi.authservice.controller;

import com.nihalramtripathi.authservice.dto.request.LoginRequestDTO;
import com.nihalramtripathi.authservice.dto.request.RegisterRequestDTO;
import com.nihalramtripathi.authservice.dto.request.SetPasscodeRequestDTO;
import com.nihalramtripathi.authservice.dto.response.AuthResponseDTO;
import com.nihalramtripathi.authservice.service.AuthService;
import com.nihalramtripathi.commonsecurity.service.JwtService;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;

    @GetMapping("/token")
    public String generateToken() {

        return jwtService.generateToken(
                1L,
                "9876543210"
        );
    }

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody RegisterRequestDTO request) {

        authService.register(request);

        return ResponseEntity.ok("User Registered Successfully");
    }

    @PostMapping("/set-passcode")
    public ResponseEntity<AuthResponseDTO> setPasscode(
            @Valid @RequestBody SetPasscodeRequestDTO request) {

        return ResponseEntity.ok(authService.setPasscode(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO request) {

        return ResponseEntity.ok(authService.login(request));
    }

}
