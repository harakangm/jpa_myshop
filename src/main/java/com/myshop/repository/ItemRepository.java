package com.myshop.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
	
	//jpql @Query 어노테이션 : 복잡한 쿼리문을 작성가능 엔티티객체에 직접적으로 감
	//쿼리메소드랑 다르게 이름에 규칙이 정해지진 않음
	// 쿼리문이랑 비슷하고 from 뒤에 엔티티클래스 이름을 정해줌
	// i = 선택변수? 그리고 테이블명은 클래스 필드명을 가져온다
	// @Param("매개변수 변수명") %:매개변수변수 명% 
	
//	@Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
//	List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);
	
	//2번째 방법 
	// %?숫자% 사용
	@Query("select i from Item i where i.itemDetail like %?1% order by i.price desc")
	List<Item> findByItemDetail(String itemDetail);
	
	//navtive쿼리 사용하는 DB랑 같은 쿼리를 사용
	//테이블에 저장된 컬럼명을 사용
	@Query(value = "select * from item i where i.item_detail like %:itemDetail% order by i.price desc", nativeQuery = true)
	List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);
	
	//4 Qureydsl : 작성시 오류를 쉽게 잡아낼수 있음 pom.xml에 의존성 추가해야지 사용가능

	
	 static void  quiz() {	 
//			//퀴즈1
//			List<Item> findByItemNmAndItemSellStatus(String itemNm, ItemSellStatus ItemSellStatus);
//			//퀴즈2
//			List<Item> findByPriceBetween(Integer price1, Integer price2);
//			//퀴즈3
//			List<Item> findByRegTimeAfter(LocalDateTime regTime);
//			//퀴즈4 find()에서 null판단은 매개변수가 없다
//			List<Item> findByItemSellStatusIsNotNull();
//			//퀴즈5
//			List<Item> findByItemDetailEndingWith(String ItemDetail);
//			//List<Item> findbyItemDetailLike(String ItemDetail);
		 }	

	 static void quiz2() {
//			//퀴즈1
//			@Query("select i from Item i where i.price >= :price")
//			List<Item> findByPriceGreaterThanEqual(@Param("price") Integer price);
//			
//			//퀴즈2
//			//매개변수로 받아올것 (필드값) 현재 에넘 itemSellStatus을 사용하니까
//			@Query("select i from Item i where i.itemNm = :itemNm and i.itemSellStatus = :sell")
//			List<Item> findByItemNmAndItemSellSatus(@Param("itemNm") String itemNm,@Param("sell") ItemSellStatus itemSellStatus);
	 }

}
