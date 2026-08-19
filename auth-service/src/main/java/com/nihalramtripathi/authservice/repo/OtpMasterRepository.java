package com.nihalramtripathi.authservice.repo;

import com.nihalramtripathi.authservice.entity.OtpMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OtpMasterRepository extends JpaRepository<OtpMasterEntity,Long> {

    //@Query(value = "SELECT * from otp_master where mobile_number = :mobileNumber and otp = :otp order by created_at DESC Limit 1",nativeQuery = true)
    @Query(
            value = "SELECT * FROM find_latest_otp(:mobileNumber)",
            nativeQuery = true
    )
   Optional<OtpMasterEntity> findLastestOtp(@Param("mobileNumber") String mobileNumber);

}
