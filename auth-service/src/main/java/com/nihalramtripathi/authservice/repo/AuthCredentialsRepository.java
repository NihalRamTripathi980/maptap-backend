package com.nihalramtripathi.authservice.repo;

import com.nihalramtripathi.authservice.entity.AuthCredentialsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthCredentialsRepository extends JpaRepository<AuthCredentialsEntity,Long> {


    Optional<AuthCredentialsEntity> findByUserId(Long user);

}
