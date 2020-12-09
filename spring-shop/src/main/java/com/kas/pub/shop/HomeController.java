package com.kas.pub.shop;

import com.kas.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    @ModelAttribute("module")
    String module() {
        return "home";
    }
    @GetMapping("/")
    public String showSignUpForm(User user) {
        return "homeNotSignedIn";
    }
}
