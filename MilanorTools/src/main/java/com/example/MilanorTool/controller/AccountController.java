package com.example.MilanorTool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.MilanorTool.model.Users;
import com.example.MilanorTool.service.UserService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String login() {
		return "account/login";
	}
	
	@GetMapping("/register")
	public String register() {
		return "account/register";
	}
	
	@PostMapping("/doLogin")
	public String doLogin(Users users) {
		return "redirect:/";
	}
	
	@PostMapping("/doRegist")
	public String doRegist(Users users) {
		userService.save(users);
		return "redirect:/login";
	}
}
