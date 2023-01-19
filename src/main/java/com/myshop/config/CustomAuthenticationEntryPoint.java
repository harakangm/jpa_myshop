package com.myshop.config;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;


//AuthenticationEntryPoint : 인증이 안됬을때 리소스를 요청할 경우 예외를 처리 하는 클래스
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		//에러를 보내주는 메서드 (int(에러코드),"보낼 스트링타입의 에러 메세지")
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}
	
}
