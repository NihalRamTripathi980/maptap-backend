package com.nihalramtripathi.authservice.service.impl;

import com.nihalramtripathi.authservice.entity.OtpMasterEntity;
import com.nihalramtripathi.authservice.repo.OtpMasterRepository;
import com.nihalramtripathi.authservice.service.OtpService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final PasswordEncoder passwordEncoder;
    private final OtpMasterRepository otpMasterRepository;

    private final SecureRandom secureRandom = new SecureRandom();

    @Override
    public String generateAndSendOtp(String mobileNumber) {

        // 1. Generate 6-digit OTP
        String otp = String.valueOf(
                100000 + secureRandom.nextInt(900000)
        );

        // 2. Hash OTP before storing
      //  String otpHash = passwordEncoder.encode(otp);

        // 3. Create entity
        OtpMasterEntity otpMaster = OtpMasterEntity.builder()
                .mobileNumber(mobileNumber)
              //  .otpHash(otpHash)
                .otpHash(otp)
                .expiresAt(LocalDateTime.now().plusMinutes(5))
                .verified(false)
                .attemptCount(0)
                .build();

        // 4. Save to database
        otpMasterRepository.save(otpMaster);

        // 5. Return plaintext OTP
        // In production, send this through SMS instead.
        return otp;
    }
}
