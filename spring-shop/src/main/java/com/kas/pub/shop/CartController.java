package com.kas.pub.shop;

import com.kas.entity.Book;
import com.kas.entity.Product;
import com.kas.model.ShoppingCart;
import com.kas.repository.BookRepository;
import com.kas.service.BookstoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    ShoppingCart shoppingCart;
    @Autowired
    BookRepository bookRepository;

    @GetMapping("/cart/add/{id}")
    public String addToCart(@PathVariable("id") long id){
        shoppingCart.addProduct(bookRepository.findById(id).get());
        return "redirect:/cart/show";
    }

    @GetMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable("id") long id){
        shoppingCart.removeProduct(bookRepository.findById(id).get());
        return "";
    }

    @GetMapping("/cart/show")
    public String getCart(Model model){

        model.addAttribute("shoppingCart", shoppingCart);
        return "page-order";
    }

    @GetMapping("/cart/show/{user_id}")
    public String getCustomerCart(){

        return "";
    }

    @GetMapping("/order/{user_id}")
    public String getOrder(@PathVariable("id") long id){

        return "";
    }

}
