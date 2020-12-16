package com.kas.service;

import com.kas.entity.Account;
import com.kas.entity.Authorities;
import com.kas.entity.Users;
import com.kas.entity.VerificationToken;
import com.kas.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    AuthoritiesRepository authoritiesRepository;
    @Autowired
    UsersRepository usersRepository;

    @Override
    public Account create(@Valid Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void createVerificationToken(Account account, String token) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUsername(account.getUsername());
        verificationToken.setExpiryDate(verificationToken.calculateExpiryDate(verificationToken.EXPIRATION));

        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public void confirmAccount(String token) {
        //retrieve token
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        //verify date
        if(verificationToken.getExpiryDate().after(new Date())) {
            //move from account table to userdetails table
            Account account = accountRepository.findByUsername(verificationToken.getUsername());
            //create user details
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            Users users = new Users(account.getUsername(), account.getEmail(),account.getPassword(),true);

            usersRepository.save(users);
            authoritiesRepository.save(new Authorities(account.getUsername(),"ROLE_USER"));
            //delete from accounts
            accountRepository.delete(account);
            //delete from tokens
            verificationTokenRepository.delete(verificationToken);
        }
    }
}
