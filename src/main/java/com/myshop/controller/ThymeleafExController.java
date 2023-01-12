package com.myshop.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myshop.dto.ItemDto;

@Controller //컨트롤러의 역활을 하는 클래스를 정의
@RequestMapping(value = "/thymeleaf") //request url의 경로지정
public class ThymeleafExController {
	
	@GetMapping(value = "/ex01")
	public String thymeleafEx01(Model model) {
		//Model 클래스 : jsp의 request랑 비슷한 역활을 해줌
		model.addAttribute("data","타임리프 예제 입니다.");
		//html경로 탬플릿 폴더 밑의 경로를 그대로 적어주면됨
		return "thymeleafEx/thymeleafEx01";
	}
	
	
	@GetMapping(value = "/ex02")
	public String tymeleafEx02(Model model) {
		ItemDto itemDto = new ItemDto();
		
		itemDto.setItemNm("테스트 상품");
		itemDto.setPrice(10000);
		itemDto.setItemDetail("테스트 상품 상세 설명");
		itemDto.setRegTime(LocalDateTime.now());
		
		model.addAttribute("itemDto", itemDto);
		
		return "thymeleafEx/thymeleafEx02";
	}
	
	@GetMapping(value = "/ex03")
	public String tymeleafEx03(Model model) {
		List<ItemDto> itemDtoList = new ArrayList<>();
		
		for(int i = 1; i <=10; i++) {			
			ItemDto itemDto = new ItemDto();
			
			itemDto.setItemNm("테스트 상품" + i);
			itemDto.setPrice(10000 + i);
			itemDto.setItemDetail("테스트 상품 상세 설명" + i);
			itemDto.setRegTime(LocalDateTime.now());
			
			itemDtoList.add(itemDto);
		}
		
		model.addAttribute("itemDtoList", itemDtoList);
		
		return "thymeleafEx/thymeleafEx03";
	}
	
	@GetMapping(value = "/ex04")
	public String tymeleafEx04(Model model) {
		List<ItemDto> itemDtoList = new ArrayList<>();
		
		for(int i = 1; i <=10; i++) {			
			ItemDto itemDto = new ItemDto();
			
			itemDto.setItemNm("테스트 상품" + i);
			itemDto.setPrice(10000 + i);
			itemDto.setItemDetail("테스트 상품 상세 설명" + i);
			itemDto.setRegTime(LocalDateTime.now());
			
			itemDtoList.add(itemDto);
		}
		
		model.addAttribute("itemDtoList", itemDtoList);
		
		return "thymeleafEx/thymeleafEx04";
	}

	@GetMapping(value = "/ex05")
	public String tymeleafEx05(Model model) {		
		return "thymeleafEx/thymeleafEx05";
	}
	
	
	@GetMapping(value = "/ex06")
	//메소드의 매개변수로 집어넣으면 데이터를 받아 올 수 있다
	public String tymeleafEx06(String param1, String param2, Model model) {
		System.out.println(param1 + "," + param2);
		
		model.addAttribute("param1", param1);
		model.addAttribute("param2", param2);
		
		return "thymeleafEx/thymeleafEx06";
	}
	

	@GetMapping(value = "/ex07")
	public String tymeleafEx07(Model model) {
		return "thymeleafEx/thymeleafEx07";
	}
}
