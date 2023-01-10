package com.myshop.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.myshop.constant.ItemSellStatus;
import com.myshop.entity.Item;

//테스트코드 작성
@SpringBootTest
//property연결 property2 를 이용
@TestPropertySource(locations="classpath:application-test.properties")
class ItemRepositoryTest {

	//의존성주입
	@Autowired
	ItemRepository itemRepository;
	
	//테스트 코드를 만들때 @Test 어노테이션을 붙여주면됨
	//@DisplayName 테스트 코드의 메서드 이름을 정해줌
//	@Test
//	@DisplayName("상품 저장 테스트")
//	public void createItemtest() {
//		Item item = new Item();
//		item.setItemNm("테스트 상품");
//		item.setPrice(10000);
//		item.setItemDetail("테스트 상품 상세 설명");
//		item.setItemSellStatus(ItemSellStatus.SELL);
//		item.setStockNumber(100);
//		item.setRegTime(LocalDateTime.now());
//		item.setUpdateTime(LocalDateTime.now());
//		
//		//save()메서드 JpaRepositor에서 상속받아온 메서드
//		//데이터 insert
//		Item savedItem = itemRepository.save(item);
//		
//		System.out.println(savedItem.toString());
//		
//		//run as = junit test 테스트코드 실행
//		//의존성 꼭봐라 씹련아
//	}
	
	public void createItemTest() {
		for( int i = 1; i <= 10; i++) {
			Item item = new Item();
			item.setItemNm("테스트 상품" + i);
			item.setPrice(10000 + i);
			item.setItemDetail("테스트 상품 상세 설명" + i);
			item.setItemSellStatus(ItemSellStatus.SELL);
			item.setStockNumber(100);
			item.setRegTime(LocalDateTime.now());
			item.setUpdateTime(LocalDateTime.now());
			
			Item savedItem = itemRepository.save(item);
		}		
	}

	//쿼리 메소드
	@Test
	@DisplayName("상품명 조회 테스트")
	public void findByItemNmTest() {
		this.createItemTest(); //item테이블에 insert
		List<Item> itemList = itemRepository.findByItemNm("테스트 상품2");
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Test
	@DisplayName("상품명 , 상품상세설명 or 테스트")
	public void findByItemNmOrItemDetailTest() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품1","테스트 상품 상세 설명5");
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Test
	@DisplayName("가격 LessThan 테스트")
	public void findByPriceLessThanTest() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByPriceLessThan(10005);
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Test
	@DisplayName("가격 내림차순 조회 테스트")
	public void findByPriceLessThanOrderByPriceDescTest() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}
//=================================================
	@Test
	@DisplayName("and테스트")
	public void findByItemNmAndItemSellStatustest() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByItemNmAndItemSellStatus("테스트 상품1", ItemSellStatus.SELL);
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Test
	@DisplayName("between")
	public void findByPriceBtweentest() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByPriceBetween(10004,10008);
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Test
	@DisplayName("datetime after")
	public void findByRegTimeLessThantest() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByRegTimeAfter(LocalDateTime.of(2023,1,1,12,12,44));
		for(Item item : itemList) {
			System.out.println(item.toString());
			System.out.println("확인");
		}
	}
	
	@Test
	@DisplayName("Not null")
	public void findItemSellStatusNotNullTest() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByItemSellStatusIsNotNull();
		for(Item item : itemList) {
			System.out.println(item.toString());
			System.out.println("확인");
		}
	}
	
	@Test
	@DisplayName("endingWith")
	public void findByItemDetailEndingWithtest() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByItemDetailEndingWith("설명1");
		for(Item item : itemList) {
			System.out.println(item.toString());
			System.out.println("확인");
		}
	}
}
