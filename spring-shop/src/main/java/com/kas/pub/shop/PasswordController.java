package com.kas.pub.shop;

import com.kas.entity.Password;
import com.kas.entity.VerificationToken;
import com.kas.repository.PasswordRepository;
import com.kas.repository.VerificationTokenRepository;
import com.kas.service.PasswordService;
import com.kas.util.OnPasswordResetEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class PasswordController {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("password")
    public String getPasswordReset(@ModelAttribute("password") Password password) {
        return "password";
    }

    @PostMapping("password")
    public String sendEmailToReset(@Valid @ModelAttribute("password") Password password, BindingResult result) {
        //check for errors
        //should verify valid email address
        //verify email from database
        //fire off an event to reset email
        eventPublisher.publishEvent(new OnPasswordResetEvent(password, "shop"));
        return "redirect:password?sent=true";
    }

    @GetMapping("passwordReset")
    public ModelAndView getNewPassword(@RequestParam("token") String token) {
        //verify token
        Password password = new Password();
        password.setToken(token);

        return new ModelAndView("resetPassword", "password", password);
    }

    @PostMapping("passwordReset")
    public String saveNewPassword(@ModelAttribute("password") Password password) {
        //should match the password

        //verify token
        VerificationToken resetToken = verificationTokenRepository.findByToken(password.getToken());

        if (resetToken.getExpiryDate().after(new Date())) {
            password.setPasswordValue(encoder.encode(password.getPasswordValue()));
            int nChangedRow = passwordService.update(resetToken, password.getPasswordValue(), resetToken.getUsername());
            if(nChangedRow == 1)
                return "redirect:login?reset=true";
            else
                return "serverError";
        } else {
            return "tokenExpired";
        }
    }
}
