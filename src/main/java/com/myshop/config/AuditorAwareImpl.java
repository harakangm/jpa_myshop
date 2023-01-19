package com.myshop.config;

import java.util.Optional;


import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

//comfig패키지에는 주로 설정과 관련된 클래스가 생성된다
//AuditorAware 인터페이스 게시글이 수정될때 작성자 정보 등록일등을 자동으로 데이터 베이스에 수정 등록해준다
//AuditorAware 설정 클래스

//로그인한 사용자의 정보를 등록자와 수정자로 지정
public class AuditorAwareImpl implements AuditorAware<String>{

	
	@Override
	public Optional<String> getCurrentAuditor() {
		//현재 로그인한 사용자의 정보를 가지고옴
		Authentication authenticateAction = SecurityContextHolder.getContext().getAuthentication();
		
		String userId = "";
		
		//만약 사용자의 정보가 null이 아니라면
		if (authenticateAction != null) {
			userId = authenticateAction.getName(); //사용자의 이름을 가져온다.
		}
		return Optional.of(userId); //사용자의 이름을 등록자와 수정자로 지정
	}
	
	
}
