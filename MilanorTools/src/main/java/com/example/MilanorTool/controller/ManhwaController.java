package com.example.MilanorTool.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.MilanorTool.model.Image;
import com.example.MilanorTool.model.Manhwa;
import com.example.MilanorTool.repository.ManhwaRepository;
import com.example.MilanorTool.utils.Zip_tools;

@Controller
@RequestMapping("/manhwa")
public class ManhwaController {
	
	@Autowired
	private ManhwaRepository manhwaRepository;
	
	@GetMapping("/list")
	public String list(Model model) {
		List<Manhwa> manhwa = manhwaRepository.findAll();
		model.addAttribute("manhwa", manhwa);
		return "manhwa/list";
	}
	
	@GetMapping("/form")
	public String form(Model model) {
		return "manhwa/form";
	}
	
	@SuppressWarnings("null")
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") String id, Model model) throws IOException {
		List<Manhwa> manhwa = manhwaRepository.findById(id);
		List<Image> contentImage = new ArrayList<Image>();
		Image data = new Image();
		String full_path = null;
		String text = "";
		File f = null;
		
		//메인 컨텐츠 호출
		for(Manhwa list : manhwa) {
			full_path = "C:/Users/pluie/Desktop/로컬 작업 자료/00.zip";
			f = new File(full_path);
			if(f.exists()) {
				InputStream inputStream = new ByteArrayInputStream(Zip_tools.Zip_tools_byte(full_path));
				byte[] byteArray = IOUtils.toByteArray(inputStream);

				data.setImage(Base64.getEncoder().encodeToString(byteArray));
				text = Base64.getEncoder().encodeToString(byteArray);
				System.out.println("byteArray = " + byteArray);
				System.out.println("text = " + text );
				System.out.println("data.getImage = " + data.getImage());
//				contentImage.add(data);
//				System.out.println("contentImage = " + contentImage.get(0).getImage());
//				
				model.addAttribute("content", Base64.getEncoder().encodeToString(byteArray));
			}
		}
		
		
		model.addAttribute("manhwa", manhwa);
		
		return "manhwa/detail";
	}
}
