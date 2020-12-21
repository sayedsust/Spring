package com.kas.service;

import com.kas.entity.Password;
import com.kas.entity.VerificationToken;
import com.kas.model.ResetToken;

public interface PasswordService {

    void createResetToken(Password password, String token);

    boolean confirmResetToken(ResetToken token);

    int update(VerificationToken resetToken, String password, String username);

}
