package com.nihalramtripathi.authservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity()
@Table(name = "auth_credentials")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthCredentialsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_id")
    private Long authId;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "passcode_hash", nullable = false)
    private String passCodeHash;

    @Builder.Default
    private boolean active=true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "passcode_expire_at", nullable = false)
    private LocalDateTime passcodeExpireAt;

    @PrePersist
    private void prePersist(){
        LocalDateTime now = LocalDateTime.now();
        createdAt=now;
        updatedAt=now;
        passcodeExpireAt=now.plusMonths(6);
    }

    @PreUpdate
    private void postPersist(){
        updatedAt = LocalDateTime.now();
    }
}
