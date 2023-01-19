package com.myshop.entity;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.myshop.dto.MemberFormDto;
import com.myshop.repository.CartRepository;
import com.myshop.repository.MemberRepository;
import com.myshop.service.MemberService;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class CartTest {

	
	@Autowired	
	CartRepository cartRepository;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@PersistenceContext //영속성 컨텍스트를 사용하기 위해 선언
	EntityManager em;
	
	public Member createMember() {
		MemberFormDto member = new MemberFormDto();
		member.setName("홍길동");
		member.setEmail("test@email.com");
		member.setAddress("서울시 마포구 합정동");
		member.setPassword("1324");
		
			
		return Member.createMember(member, passwordEncoder);
	}
	
	@Test
	@DisplayName("장바구니 회원 엔티티 매핑 조회 테스트")
	public void findCartAndMemberTest() {
		Member member = createMember();
		memberRepository.save(member); //save() update도 되고 save도됨
		
		Cart cart = new Cart();
		cart.setMember(member);
		
		cartRepository.save(cart); //1.아직까진 sql쓰기저장소에 저장되어있다가
		
		em.flush(); //2.트랜잭션이 끝날떄 데이터베이스에 반영
		em.clear(); //3.영속성 컨텍스트를 비워준다 -> 실제 데이터베이스에서 장바구니 엔티티를 가지고 올때 회원 엔티티도 같이 가지고오는지 보기 위해
		
		Cart saverdCart = cartRepository.findById(cart.getId()) //옵셔널(Optional) 자바8부터 등장 에러처리를 if를 사용하지 않고 처리 가능
				.orElseThrow(EntityNotFoundException::new);
		
		assertEquals(saverdCart.getMember().getId(), member.getId());
	}
}
