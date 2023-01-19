package com.myshop.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cart_item")
@Getter
@Setter
@ToString
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="cart_item_id")
	private Long id;
	
	//다대일 관계 자식엔티티기준으로 어노테이션을 작성
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cart_id")
	private Cart cart;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;
	
	
	private int count;
	
}
