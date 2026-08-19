package com.nihalramtripathi.authservice.controller;

import com.nihalramtripathi.authservice.dto.request.LoginRequestDTO;
import com.nihalramtripathi.authservice.dto.request.RegisterRequestDTO;
import com.nihalramtripathi.authservice.dto.request.SetPasscodeRequestDTO;
import com.nihalramtripathi.authservice.dto.request.VerifyOtpRequestDTO;
import com.nihalramtripathi.authservice.dto.response.AuthResponseDTO;
import com.nihalramtripathi.authservice.dto.response.MobileCheckResponseDTO;
import com.nihalramtripathi.authservice.dto.response.VerifyOtpResponseDTO;
import com.nihalramtripathi.authservice.service.AuthService;
import com.nihalramtripathi.commoncore.dto.ApiResponse;
import com.nihalramtripathi.commoncore.dto.request.CommonRequestDTO;
import com.nihalramtripathi.commoncore.dto.response.UserDetailsResponseDTO;
import com.nihalramtripathi.commonsecurity.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/checkUserLogin")
    public ResponseEntity<ApiResponse<MobileCheckResponseDTO>> checkUserLogin(@RequestBody CommonRequestDTO requestDTO) {

        MobileCheckResponseDTO responseDTO = authService.checkUserLogin(requestDTO);

        ApiResponse<MobileCheckResponseDTO> apiResponse = ApiResponse.success("Success", responseDTO);
        return ResponseEntity.ok(apiResponse);
    }


    @PostMapping("/verify-otp")
    public ResponseEntity<ApiResponse<VerifyOtpResponseDTO>> verifyOTP(@RequestBody VerifyOtpRequestDTO requestDTO) {

        VerifyOtpResponseDTO isVerified = authService.verifyOtp(requestDTO);

        ApiResponse<VerifyOtpResponseDTO> apiResponse = ApiResponse.success("Success", isVerified);


        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/user/register")
    public ResponseEntity<ApiResponse<UserDetailsResponseDTO>> register(
            @Valid @RequestBody RegisterRequestDTO request) {

        UserDetailsResponseDTO response = authService.register(request);

        ApiResponse<UserDetailsResponseDTO> apiResponse = ApiResponse.success("User Registered Successfully",
                response);

        return ResponseEntity.ok(apiResponse);
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
