package com.kas.repository;

import com.kas.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT a FROM Account a WHERE a.username = :name")
    Account findByUsername(String name);
}
