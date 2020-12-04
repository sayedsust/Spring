package com.kas.pub.shop;

import com.kas.entity.Book;
import com.kas.model.Registration;
import com.kas.service.BookstoreService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class GreetingController {

    private BookstoreService bookstoreService;

    public GreetingController(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    @GetMapping("thyme")
    public String greeting (Map<String, Object> model) {
        model.put("message", "Hello world");
        return "thyme";
    }

}