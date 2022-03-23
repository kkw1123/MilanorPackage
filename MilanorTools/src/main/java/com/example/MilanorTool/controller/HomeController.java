package com.example.MilanorTool.controller;


import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.MilanorTool.model.Manhwa;
import com.example.MilanorTool.repository.ManhwaRepository;
import com.example.MilanorTool.utils.Zip_tools;

@Controller
public class HomeController {
	
	@Autowired
	private ManhwaRepository boardRepository;
	
	@GetMapping("/")
	public String index(Model model) throws IOException {
		List<Manhwa> manhwa = boardRepository.findAll();
		InputStream inputStream = new ByteArrayInputStream(Zip_tools.Zip_tools_byte());
		byte[] byteArray = IOUtils.toByteArray(inputStream);
		model.addAttribute("manhwa", manhwa);
		model.addAttribute("image", Base64.getEncoder().encodeToString(byteArray));
		return "index";
	}
}
