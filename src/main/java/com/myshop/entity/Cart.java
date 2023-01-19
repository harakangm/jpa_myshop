package com.myshop.entity;


import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cart") // 테이블명 설정, 안하면 클래스명으로 자동 설정됨.
@Getter
@Setter
@ToString
public class Cart {
	
	@Id
	@Column(name = "cart_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//일대일 매핑
	
	//@JoinColumn 객체에있는 필드(참조할 컬럼)을 명시
	@OneToOne(fetch = FetchType.LAZY) //일대일 관계 카트가 멤버를 참조함
	@JoinColumn(name="member_id") //join 관계에 있는 컬럼 지정
	private Member member;
	
}
