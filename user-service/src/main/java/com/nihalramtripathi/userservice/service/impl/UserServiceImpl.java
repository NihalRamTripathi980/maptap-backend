package com.nihalramtripathi.userservice.service.impl;

import com.nihalramtripathi.userservice.dto.UserRegistrationDTO;
import com.nihalramtripathi.userservice.entity.UserEntity;
import com.nihalramtripathi.userservice.entity.UserRoleEntity;
import com.nihalramtripathi.userservice.repository.UserRepository;
import com.nihalramtripathi.userservice.repository.UserRoleRepository;
import com.nihalramtripathi.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository roleRepository;

    @Override
    public void register(UserRegistrationDTO request) {
        if (userRepository.existsByMobileNumber(request.getMobileNumber())) {
            throw new RuntimeException("Mobile number already registered.");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered.");
        }

        UserRoleEntity role = roleRepository.findByRoleName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));

        Set<UserRoleEntity> roles = new HashSet<>();
        roles.add(role);

        UserEntity user = UserEntity.builder()
                .mobileNumber(request.getMobileNumber())
                .email(request.getEmail())
                .fullName(request.getFullName())
                .fatherName(request.getFatherName())
                .salaried(request.getSalaried())
                .roles(roles)
                .enabled(true)
                .mobileVerified(false)
                .emailVerified(false)
                .build();

        userRepository.save(user);
    }
}
