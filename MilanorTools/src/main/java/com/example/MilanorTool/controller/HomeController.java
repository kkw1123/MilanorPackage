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

import com.example.MilanorTool.model.Comics;
import com.example.MilanorTool.repository.ComicsRepository;
import com.example.MilanorTool.utils.ZipTools;

@Controller
public class HomeController {
	
	@Autowired
	private ComicsRepository comicsRepository;
	
	//메인화면
	@GetMapping("/")
	public String index(Model model, @PageableDefault(size = 12) Pageable pageable) throws IOException {
		Page<Comics> comics = comicsRepository.findAll(pageable);
		int imageNum = 0;
		
		//메인 이미지 호출
		for (Comics list: comics) {
			File f = new File(list.getTitle_image());
			//System.out.println("저장소 연결여부 체크 = " + f.exists());
			if(f.exists()) {
				String titleImagePath = list.getTitle_image();
				InputStream inputStream = new ByteArrayInputStream(ZipTools.Zip_tools_byte(titleImagePath));
				byte[] byteArray = IOUtils.toByteArray(inputStream);
				model.addAttribute("image"+imageNum, Base64.getEncoder().encodeToString(byteArray));
			}
			imageNum++;
		}
		int startPage = Math.max(1, comics.getPageable().getPageNumber()-1);
		int endPage = Math.min(comics.getTotalPages(), comics.getPageable().getPageNumber()+3);
		
		//페이지 갯수 조정 로직(무조건 5개씩만 나오게)
		if(comics.getPageable().getPageNumber() == 0 || comics.getPageable().getPageNumber() == 1) {
			endPage = 5;
			if(comics.getTotalPages() < 5) {
				endPage = Math.min(comics.getTotalPages(), comics.getPageable().getPageNumber()+3);
			}
		}
		
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("comics", comics);
		
		return "index";
	}
}
