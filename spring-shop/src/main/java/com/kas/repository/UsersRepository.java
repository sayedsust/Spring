package com.kas.repository;

import com.kas.entity.Users;
import com.kas.entity.VerificationToken;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Users u SET u.password = :password WHERE u.username = :username")
    int updateUserPassword(String password, String username);
}
