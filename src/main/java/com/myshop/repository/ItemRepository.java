package com.myshop.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myshop.constant.ItemSellStatus;
import com.myshop.entity.Item;

//JpaRepository : 기본적인 CRUD 및 페이징 처리를 위한 메소드가 정의가 되어있다
//JpaRepository<사용할 엔티티 클래스, 엔티티클래스의 기본키 타입>
public interface ItemRepository extends JpaRepository<Item, Long>{
	//파인드메소드 정의 기본적으로 where이 붙음
	
	//select해왔을때 데이터를 담을 객체
	List<Item> findByItemNm(String itemNm);
	//select * from item where item_nm = ?
	
	//find or
	List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);
	//select * from item where tiem_nm =? or item_detail=?
	
	//select * from item where price < ?
	List<Item> findByPriceLessThan(Integer price);
	
	//select * from item where price <? order by price desc
	List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);
	
	//퀴즈1
	List<Item> findByItemNmAndItemSellStatus(String itemNm, ItemSellStatus ItemSellStatus);
	//퀴즈2
	List<Item> findByPriceBetween(Integer price1, Integer price2);
	//퀴즈3
	List<Item> findByRegTimeAfter(LocalDateTime regTime);
	//퀴즈4
	List<Item> findByItemSellStatusIsNotNull();
	//퀴즈5
	List<Item> findByItemDetailEndingWith(String ItemDetail);

}
