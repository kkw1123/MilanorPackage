package com.example.MilanorTool.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.MilanorTool.model.Comics;
import com.example.MilanorTool.model.Contents;
import com.example.MilanorTool.repository.ComicsRepository;
import com.example.MilanorTool.utils.Zip_tools;

@Controller
@RequestMapping("/comics")
public class ComicsController {
	
	@Autowired
	private ComicsRepository ComicsRepository;
	
	@GetMapping("/list")
	public String comicsList(Model model) {
		List<Comics> comics = ComicsRepository.findAll();
		model.addAttribute("comics", comics);
		return "comics/list";
	}
	
	@GetMapping("/form")
	public String comicsForm(Model model) {
		return "comics/form";
	}
	
	@GetMapping("/detail/{id}")
	public String comicsDetail(@PathVariable("id") String id, Model model) throws IOException {
		List<Comics> comics = ComicsRepository.findById(id);
		List<Contents> contentImage = new ArrayList<Contents>();
		String fullPath = comics.get(0).getContent_path();
		int volumes = comics.get(0).getVolumes();
		
		fullPath = "C:/Users/pluie/Desktop/로컬 작업 자료/00.zip"; //임시
		
		//메인 컨텐츠 호출
		File f = new File(fullPath);
		if(f.exists()) {
			contentImage = Zip_tools.Zip_tools_bytes(fullPath);
			model.addAttribute("contents", contentImage);
		} else {
			System.out.println("파일이 없습니다.");
		}
		
		model.addAttribute("comics", comics);
		
		return "comics/detail";
	}
}
