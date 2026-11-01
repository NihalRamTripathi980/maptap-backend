package com.nihalramtripathi.authservice.service.impl;

import com.nihalramtripathi.authservice.client.UserClient;
import com.nihalramtripathi.authservice.dto.request.LoginRequestDTO;
import com.nihalramtripathi.authservice.dto.request.RegisterRequestDTO;
import com.nihalramtripathi.authservice.dto.request.SetPasscodeRequestDTO;
import com.nihalramtripathi.authservice.dto.request.VerifyOtpRequestDTO;
import com.nihalramtripathi.authservice.dto.response.AuthResponseDTO;
import com.nihalramtripathi.authservice.dto.response.MobileCheckResponseDTO;
import com.nihalramtripathi.authservice.dto.response.VerifyOtpResponseDTO;
import com.nihalramtripathi.authservice.entity.AuthCredentialsEntity;
import com.nihalramtripathi.authservice.entity.OtpMasterEntity;
import com.nihalramtripathi.authservice.repo.AuthCredentialsRepository;
import com.nihalramtripathi.authservice.repo.OtpMasterRepository;
import com.nihalramtripathi.authservice.service.AuthService;
import com.nihalramtripathi.authservice.service.OtpService;
import com.nihalramtripathi.commoncore.dto.request.CommonRequestDTO;
import com.nihalramtripathi.commoncore.dto.response.UserDetailsResponseDTO;
import com.nihalramtripathi.commoncore.dto.response.UserLookupResponseDTO;
import com.nihalramtripathi.commonsecurity.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final UserClient userClient;

    private final OtpMasterRepository otpMasterRepository;

    private final AuthCredentialsRepository authCredentialsRepository;

    private final PasswordEncoder passwordEncoder;
    private final OtpService otpService;
    private final JwtService jwtService;


    @Override
    public MobileCheckResponseDTO checkUserLogin(CommonRequestDTO request) {
        UserLookupResponseDTO lookupResponseDTO = userClient.checkUserLogin(request);
        if (lookupResponseDTO.isExists()) {

            return MobileCheckResponseDTO.builder()
                    .userExists(true)
                    .nextStep("LOGIN")
                    .message("User already registered")
                    .userDetails(UserDetailsResponseDTO
                            .builder()
                            .userId(lookupResponseDTO.getUserId())
                            .userName(lookupResponseDTO.getUserName())
                            .build())
                    .build();
        } else {
            otpService.generateAndSendOtp(request.getMobileNumber());
            return MobileCheckResponseDTO.builder()
                    .userExists(false)
                    .mobileNumber(request.getMobileNumber())
                    .nextStep("VERIFY_OTP")
                    .message("OTP sent successfully")
                    .build();
        }

    }

    @Override
    public VerifyOtpResponseDTO verifyOtp(VerifyOtpRequestDTO request) {

        Optional<OtpMasterEntity> otpMasterOptional =
                otpMasterRepository.findLastestOtp(request.getMobileNumber());

        if (otpMasterOptional.isEmpty()) {
            return VerifyOtpResponseDTO.builder()
                    .status(false)
                    .message("Invalid OTP")
                    .build();
        }

        OtpMasterEntity otpMaster = otpMasterOptional.get();

        if (Boolean.TRUE.equals(otpMaster.getVerified())) {
            return VerifyOtpResponseDTO.builder()
                    .status(false)
                    .message("OTP already verified")
                    .build();
        }

        if (otpMaster.getExpiresAt().isBefore(LocalDateTime.now())) {
            return VerifyOtpResponseDTO.builder()
                    .status(false)
                    .message("OTP expired")
                    .build();
        }

//        if (!passwordEncoder.matches(request.getOtp(), otpMaster.getOtpHash())) {
//            return VerifyOtpResponseDTO.builder()
//                    .status(false)
//                    .message("Invalid OTP")
//                    .build();
//        }
        if(!Objects.equals(otpMaster.getOtpHash(), request.getOtp())){
            return VerifyOtpResponseDTO.builder()
                  .status(false)
                   .message("Invalid OTP")
                    .build();
        }

        otpMaster.setVerified(true);
        otpMasterRepository.save(otpMaster);
        CommonRequestDTO commonRequestDTO = CommonRequestDTO
                .builder()
                .mobileNumber(request.getMobileNumber())
                .build();
        log.info("OTP verified successfully: otpId={}, mobileNumber={}",
                request.getOtp(),
                request.getMobileNumber());

        // Create user
        log.info("Calling User Service to create user: mobileNumber={}",
                request.getMobileNumber());

        UserDetailsResponseDTO userDetailsResponseDTO =  userClient.createNewUser(commonRequestDTO);

        // Generate JWT
        String accessToken = jwtService.generateToken(
                userDetailsResponseDTO.getUserId(),
                userDetailsResponseDTO.getMobileNumber()
        );

        AuthResponseDTO authResponseDTO = AuthResponseDTO.builder()
                .userId(userDetailsResponseDTO.getUserId())
                .mobileNumber(userDetailsResponseDTO.getMobileNumber())
                .accessToken(accessToken)
                .tokenType("Bearer")
                .expiresIn(3600L)
                .build();

        return VerifyOtpResponseDTO.builder()
                .status(true)
                .authResponse(
                        authResponseDTO)
                .message("OTP verified successfully")
                .build();
    }


    @Override
    public UserDetailsResponseDTO register(RegisterRequestDTO request) {
        return userClient.registerUser(request);
    }

    @Override
    public void setPasscode(SetPasscodeRequestDTO request) {
        String hashedPasscode = passwordEncoder.encode(request.getPasscode());
        AuthCredentialsEntity authCredentialsEntity = AuthCredentialsEntity.builder()
                .userId(request.getUserId())
                .passCodeHash(hashedPasscode)
                .build();

        authCredentialsRepository.save(authCredentialsEntity);
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO request) {
        AuthCredentialsEntity authCredentials = authCredentialsRepository.findByUserId(request.getUserId()).orElseThrow(() -> new BadCredentialsException("Invalid credentials"));

        if (!passwordEncoder.matches(
                request.getPasscode(),
                authCredentials.getPassCodeHash())) {

            throw new BadCredentialsException("Invalid credentials");
        }

        // Generate JWT
        String accessToken = jwtService.generateToken(
                request.getUserId(),
                request.getMobileNumber()
        );


        return AuthResponseDTO.builder()
                .userId(request.getUserId())
                .mobileNumber(request.getMobileNumber())
                .accessToken(accessToken)
                .tokenType("Bearer")
                .expiresIn(3600L)
                .build();

    }
}
