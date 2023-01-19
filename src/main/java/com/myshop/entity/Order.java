package com.myshop.entity;

import java.time.LocalDateTime;
import java.util.*;

import javax.persistence.*;

import com.myshop.constant.OrderStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Orders") // 테이블명 설정, 안하면 클래스명으로 자동 설정됨.
@Getter
@Setter
@ToString
public class Order {

	@Id
	@Column(name = "order_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JoinColumn(name = "member_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;
	
	private LocalDateTime orderDate; //주문일
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus; //주문상태
	
	//양방향관계
	//주인이 아님(fk를 관리 하는데 주인은 외래키가 있는곳 자식 테이블)
	//mappedby로 주인표시
	//값을 수정 불가 참조만 가능
	//cascade = 영속성 전이 : 엔티티의 상태를 변경할 때 해당 엔티티와 연관된 엔티티의 상태 변화를 전파시키는 옵션
	//부모쪽에 적어주면 된다
	//orphanRemoval: 고아객체 삭제 부모엔티티가 삭제되면 연관된 자식엔티티도 삭제된다
	@OneToMany(mappedBy = "order" , cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)//OrderItem에 있는 order에 의해 관리 된다(필드값).
	private List<OrderItem> orderItems = new ArrayList<>();
	
}
