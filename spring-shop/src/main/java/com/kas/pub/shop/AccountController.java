package com.kas.pub.shop;

import com.kas.entity.Account;
import com.kas.service.AccountService;
import com.kas.util.OnCreateAccountEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @GetMapping("account")
    public String getRegistration(@ModelAttribute("account") Account account) {
        return "account";
    }

    @PostMapping("account")
    public String addRegistration(@Valid @ModelAttribute ("account")
                                          Account account,
                                  BindingResult result) {

        //check for errors
        //should verify that the account and the user don't already exist
        //should verify valid email address

        //encrypt password
        System.out.println("password entered "+account.getPassword());
        String tmp = encoder.encode(account.getPassword());
        System.out.println("password encoded" + tmp);
        account.setPassword(tmp);

        //create the account
        account = accountService.create(account);

        //fire off an event on creation
        eventPublisher.publishEvent(new OnCreateAccountEvent(account,"shop"));
        return "redirect:account";
    }

    @GetMapping("accountConfirm")
    public String confirmAccount(@RequestParam("token") String token) {
        accountService.confirmAccount(token);

        return "accountConfirmed";
    }

}
