package com.javatechie.spring.security.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/userpath")
public class NoAuthController {

	@GetMapping("/getMsg")
	public String sayHi() {
		return "hi";
	}

}
