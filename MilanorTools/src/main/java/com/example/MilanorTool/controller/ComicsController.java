package com.example.MilanorTool.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.MilanorTool.model.Comics;
import com.example.MilanorTool.model.Contents;
import com.example.MilanorTool.model.Items;
import com.example.MilanorTool.repository.ComicsRepository;
import com.example.MilanorTool.utils.ZipTools;

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
	public String comicsDetail(@PathVariable("id") String id, Model model, int itemId) throws IOException {
		List<Comics> comics = ComicsRepository.findById(id);
		String fullPath = comics.get(0).getContent_path();
		List<Items> items = new ArrayList<Items>();
		int creItemId = 1;
		String volumes = "";
		
		List<Contents> contentItemLst = new ArrayList<Contents>();
		
		//전체 항목 조회 및 출력
		File dir = new File(fullPath);
		File[] files = dir.listFiles();
		Arrays.sort(files); // 파일이름 순 정렬
		
		for(File lst : files) {
			Items itemList = new Items();
			itemList.setId(creItemId);
			itemList.setFullName(lst.getName());
			itemList.setName(lst.getName().substring(0, lst.getName().length()-4));
			
			if(itemId == creItemId) {
				fullPath = fullPath + "/" + lst.getName();
				volumes = itemList.getName();
			}
			items.add(itemList);
			
			creItemId++;
		}
		
		//메인 컨텐츠 이미지 호출
		File f = new File(fullPath);
		if(f.exists()) {
			contentItemLst = ZipTools.Zip_tools_bytes(fullPath);
			model.addAttribute("contents", contentItemLst);
		} else {
			System.out.println("파일이 없습니다.");
		}
		
		model.addAttribute("itemId", itemId);
		model.addAttribute("items", items);
		model.addAttribute("comics", comics);
		model.addAttribute("volumes", volumes);
		
		return "comics/detail";
	}
}
