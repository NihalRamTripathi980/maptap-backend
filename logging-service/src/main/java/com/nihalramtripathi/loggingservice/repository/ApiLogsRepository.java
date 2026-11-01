package com.nihalramtripathi.loggingservice.repository;

import com.nihalramtripathi.loggingservice.entity.ApiLogsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
 public interface ApiLogsRepository extends JpaRepository<ApiLogsEntity,Long> {
}
