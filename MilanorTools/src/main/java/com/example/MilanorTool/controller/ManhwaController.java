package com.example.MilanorTool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.MilanorTool.model.Manhwa;
import com.example.MilanorTool.repository.ManhwaRepository;

@Controller
@RequestMapping("/manhwa")
public class ManhwaController {
	
	@Autowired
	private ManhwaRepository manhwaRepository;
	
	@GetMapping("")
	public String list(Model model) {
		List<Manhwa> manhwa = manhwaRepository.findAll();
		model.addAttribute("manhwa", manhwa);
		return "manhwa/list";
	}
	
	@GetMapping("/form")
	public String form(Model model) {
		return "manhwa/form";
	}
	
	@GetMapping("/detail")
	public String detail(Model model) {
		return "manhwa/detail";
	}
}
