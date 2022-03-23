package com.example.MilanorTool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.MilanorTool.model.Manhwa;
import com.example.MilanorTool.repository.ManhwaRepository;

@Controller
public class HomeController {
	
	@Autowired
	private ManhwaRepository boardRepository;
	
	@GetMapping("/")
	public String index(Model model) {
		List<Manhwa> manhwa = boardRepository.findAll();
		model.addAttribute("manhwa", manhwa);
		return "index";
	}
}
