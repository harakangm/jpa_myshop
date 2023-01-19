package com.myshop.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="order_item") //테이블명
@Getter
@Setter
@ToString
public class OrderItem extends BaseEntity{
	
	@Id
	@Column(name ="order_item_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//다대일 관계에서는 자식엔티티 클래스 기준으로 어노테이션을 작성
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;
	
	//지연로딩
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;
	
	
	private int orderPrice; //주문가격
	
	private int count; //주문수량
}
