package com.kas.service;

import com.kas.entity.Account;

import javax.validation.Valid;

public interface AccountService {

    public Account create (@Valid Account account);

    void createVerificationToken(Account account, String token);

    void confirmAccount(String token);
}
