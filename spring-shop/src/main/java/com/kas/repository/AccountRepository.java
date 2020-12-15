package com.kas.repository;

import com.kas.entity.Account;
import com.kas.entity.ConferenceUserDetails;
import com.kas.entity.VerificationToken;

import javax.validation.Valid;

public interface AccountRepository {
    public Account create (@Valid Account account);

    void saveToken(VerificationToken verificationToken);

    VerificationToken findByToken(String token);

    Account findByUsername(String username);

    void createUserDetails(ConferenceUserDetails userDetails);

    void createAuthorities(ConferenceUserDetails userDetails);

    void delete(Account account);

    void deleteToken(String token);
}
