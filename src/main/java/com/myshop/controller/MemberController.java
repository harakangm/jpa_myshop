package com.myshop.controller;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myshop.dto.MemberFormDto;
import com.myshop.entity.Member;
import com.myshop.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor // 파이널키워드가 있는 객체 의존성주입가능
public class MemberController {
	private final MemberService memberservice;
	private final PasswordEncoder passwordEncoder;
	
	//회원가입 화면
	@GetMapping(value ="/new")
	public String memberForm(Model model) {
		model.addAttribute("memberFormDto", new MemberFormDto());
		return "member/memberForm";
	}
	
	//회원가입 버튼을 눌렀을때 실행되는 메소드
	//@Valid: 유효성을 검증하려는 객체 앞에 붙은다.
	//BindingResult: 유효성 검증후에 결과는 BindingResult에 넣어준다.
	@PostMapping(value="/new")
	public String memberForm(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {
		//에러가 있다면 회원가입 페이지로 이동
		if(bindingResult.hasErrors()) {
			return "member/memberForm";
		}
		
		try {
			Member member = Member.createMember(memberFormDto, passwordEncoder);
			memberservice.saveMember(member);			
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "member/memberForm";
		}
		
		//포스트 방식	
		
		return "redirect:/";
	}
}
