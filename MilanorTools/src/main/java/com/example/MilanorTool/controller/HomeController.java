package com.example.MilanorTool.controller;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.MilanorTool.model.Manhwa;
import com.example.MilanorTool.repository.ManhwaRepository;
import com.example.MilanorTool.utils.Zip_tools;

@Controller
public class HomeController {
	
	@Autowired
	private ManhwaRepository boardRepository;
	
	@GetMapping("/")
	public String index(Model model, @PageableDefault(size = 12) Pageable pageable) throws IOException {
		Page<Manhwa> manhwa = boardRepository.findAll(pageable);
		int imageNum = 0;
		
		//메인 이미지 호출
		for (Manhwa list: manhwa) {
			File f = new File(list.getTitle_image());
			//System.out.println("저장소 연결여부 체크 = " + f.exists());
			if(f.exists()) {
				String titleImagePath = list.getTitle_image();
				InputStream inputStream = new ByteArrayInputStream(Zip_tools.Zip_tools_byte(titleImagePath));
				byte[] byteArray = IOUtils.toByteArray(inputStream);
				model.addAttribute("image"+imageNum, Base64.getEncoder().encodeToString(byteArray));
			}
			imageNum++;
		}
		int startPage = Math.max(1, manhwa.getPageable().getPageNumber()-1);
		int endPage = Math.min(manhwa.getTotalPages(), manhwa.getPageable().getPageNumber()+3);
		
		//페이지 갯수 조정 로직
		if(manhwa.getPageable().getPageNumber() == 0 || manhwa.getPageable().getPageNumber() == 1) {
			endPage = 5;
			if(manhwa.getTotalPages() < 5) {
				endPage = Math.min(manhwa.getTotalPages(), manhwa.getPageable().getPageNumber()+3);
			}
		}
		
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("manhwa", manhwa);
		
		return "index";
	}
}
