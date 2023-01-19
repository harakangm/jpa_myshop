package com.myshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//Auditor기능을 사용하기 위한 클래스
@Configuration
@EnableJpaAuditing// jpa의 auditing기능을 활성화 
public class AuditorConfig {
	
	@Bean // 스프링컨테이너에서 관리하고 의존성주입이 가능함
	public AuditorAware<String> AuditorProvider() {
		return new AuditorAwareImpl();
	}
}
