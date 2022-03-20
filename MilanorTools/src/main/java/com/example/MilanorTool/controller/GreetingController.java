package com.example.MilanorTool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class GreetingController {

	@GetMapping("/practice")
	public String greeting(@RequestParam(name = "name", defaultValue = "World") String name, Model model) {
		model.addAttribute("name",name);
		return "greeting";
	}
}