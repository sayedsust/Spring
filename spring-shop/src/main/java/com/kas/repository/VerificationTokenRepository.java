package com.kas.repository;

import com.kas.entity.VerificationToken;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT v FROM VerificationToken v WHERE v.token = :token")
    VerificationToken findByToken(String token);

}