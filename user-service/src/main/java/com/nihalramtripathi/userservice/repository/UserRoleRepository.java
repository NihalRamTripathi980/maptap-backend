package com.nihalramtripathi.userservice.repository;

import com.nihalramtripathi.userservice.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity,Long> {

    Optional<UserRoleEntity> findByRoleName(String roleName);
}
