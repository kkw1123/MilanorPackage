package com.example.MilanorTool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.MilanorTool.model.ManhwaBoard;
import com.example.MilanorTool.repository.ManhwaBoardRepository;

@Controller
@RequestMapping("/manhwa")
public class ManhwaBoardController {
	
	@Autowired
	private ManhwaBoardRepository boardRepository;
	
	@GetMapping("/list")
	public String list(Model model) {
		List<ManhwaBoard> boards = boardRepository.findAll();
		model.addAttribute("boards", boards);
		return "manhwa/list";
	}
	
	@GetMapping("/form")
	public String form(Model model) {
		return "manhwa/form";
	}
}
