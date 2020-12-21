package com.kas.service;

import com.kas.entity.Password;
import com.kas.entity.VerificationToken;
import com.kas.model.ResetToken;
import com.kas.repository.UsersRepository;
import com.kas.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordServiceImpl implements PasswordService {

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void createResetToken(Password password, String token) {
        VerificationToken resetToken = new VerificationToken();
        resetToken.setToken(token);
        resetToken.setEmail(password.getEmail());
        resetToken.setUsername(password.getUsername());

        resetToken.setExpiryDate(resetToken.calculateExpiryDate(resetToken.EXPIRATION));

        verificationTokenRepository.save(resetToken);
    }

    @Override
    public boolean confirmResetToken(ResetToken token) {
        return false;
    }

    @Override
    public int update(VerificationToken resetToken, String password, String username) {
        verificationTokenRepository.delete(resetToken);
        return usersRepository.updateUserPassword(password, username);
    }
}
