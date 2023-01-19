package com.myshop.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.thymeleaf.util.StringUtils;

import com.myshop.constant.ItemSellStatus;
import com.myshop.entity.Item;
import com.myshop.entity.QItem;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

//테스트코드 작성
@SpringBootTest
//property연결 property2 를 이용
@TestPropertySource(locations="classpath:application-test.properties")
class ItemRepositoryTest {

	//의존성주입
	@Autowired
	ItemRepository itemRepository;
	
	
	//Qureydsl을 사용하기위해서는 영속성컨택스트를 따로 불러와야함
	@PersistenceContext //영속성컨택스트를 사용하기 위해 선언
	EntityManager em; //엔티티 매니저를 가져옴
	
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
//			item.setUpdateTime(LocalDateTime.now());
			
			Item savedItem = itemRepository.save(item);
		}		
	}
	
	public void createItemTest2() {
		for( int i = 1; i <= 5; i++) {
			Item item = new Item();
			item.setItemNm("테스트 상품" + i);
			item.setPrice(10000 + i);
			item.setItemDetail("테스트 상품 상세 설명" + i);
			item.setItemSellStatus(ItemSellStatus.SELL);
			item.setStockNumber(100);
			item.setRegTime(LocalDateTime.now());
//			item.setUpdateTime(LocalDateTime.now());
			
			Item savedItem = itemRepository.save(item);
		}		
		for( int i = 6; i <= 10; i++) {
			Item item = new Item();
			item.setItemNm("테스트 상품" + i);
			item.setPrice(10000 + i);
			item.setItemDetail("테스트 상품 상세 설명" + i);
			item.setItemSellStatus(ItemSellStatus.SOLD_OUT);
			item.setStockNumber(0);
			item.setRegTime(LocalDateTime.now());
//			item.setUpdateTime(LocalDateTime.now());
			
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
	
// jpql @Query
	
	@Test
	@DisplayName("@Query를 이용한 상품 조회 테스트")
	public void findByItemDetailTest() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByItemDetail("테스트 상품 상세 설명");
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Test
	@DisplayName("@native Query를 이용한 상품 조회 테스트")
	public void findByItemDetailByNativetest() {
		this.createItemTest();                                        
		List<Item> itemList = itemRepository.findByItemDetailByNative("테스트 상품 상세 설명");
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}

	//4 Qureydsl : 작성시 오류를 쉽게 잡아낼수 있음 pom.xml에 의존성 추가해야지 사용가능	
	@Test
	@DisplayName("querydsl 조회 테스트")
	public void queryDslTest() {
		this.createItemTest();
		//JPAQueryFactory : 쿼리를 동적으로 생성하기 위한 객체
		//JPAQueryFactory(엔티티매니저 객체)
		JPAQueryFactory qf = new JPAQueryFactory(em);
		QItem qItem = QItem.item;		
		//쿼리문을 실행시킬 객체
		//select * from item where itemSellStatus = 'SELL'and itemDetail Like '%테스트 상품 상세 설명%'
		JPAQuery<Item> query = qf.selectFrom(qItem)
				.where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
				.where(qItem.itemDetail.like("%테스트 상품 상세 설명%"))
				.orderBy(qItem.price.desc());
		
		List<Item> itemList = query.fetch();
		
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}

	// Qureydsl 페이징 기능사용
	// 검색기능
	@Test
	@DisplayName("querydsl 조회 테스트2")
	public void queryDslTest2() {
		this.createItemTest2();
		
		JPAQueryFactory qf = new JPAQueryFactory(em);
		QItem qItem = QItem.item;	
		
		Pageable page = PageRequest.of(0, 2); // of (조회할 페이지의 번호, 한페이지당 조회 할 데이터의 갯수)
		
	
		JPAQuery<Item> query = qf.selectFrom(qItem)
				.where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
				.where(qItem.itemDetail.like("%테스트 상품 상세 설명%"))
				.where(qItem.price.gt(10003))
				.offset(page.getOffset())
				.limit(page.getPageSize());
		
		List<Item> itemList = query.fetch();
		
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
		
	}
	
	
	static void  quiz() {
		//=================================================
//		@Test
//		@DisplayName("and테스트")
//		public void findByItemNmAndItemSellStatustest() {
//			this.createItemTest();
//			List<Item> itemList = itemRepository.findByItemNmAndItemSellStatus("테스트 상품1", ItemSellStatus.SELL);
//			for(Item item : itemList) {
//				System.out.println(item.toString());
//			}
//		}
	//	
//		@Test
//		@DisplayName("between")
//		public void findByPriceBtweentest() {
//			this.createItemTest();
//			List<Item> itemList = itemRepository.findByPriceBetween(10004,10008);
//			for(Item item : itemList) {
//				System.out.println(item.toString());
//			}
//		}
	//	
//		@Test
//		@DisplayName("datetime after")
//		public void findByRegTimeLessThantest() {
//			this.createItemTest();
//			
//			List<Item> itemList = itemRepository.findByRegTimeAfter(LocalDateTime.of(2023,1,1,12,12,44));
//			for(Item item : itemList) {
//				System.out.println(item.toString());
//			}
//		}
	//	
//		@Test
//		@DisplayName("Not null")
//		public void findItemSellStatusNotNullTest() {
//			this.createItemTest();
//			List<Item> itemList = itemRepository.findByItemSellStatusIsNotNull();
//			for(Item item : itemList) {
//				System.out.println(item.toString());
//			}
//		}
	//	
//		@Test
//		@DisplayName("endingWith")
//		public void findByItemDetailEndingWithtest() {
//			this.createItemTest();
//			List<Item> itemList = itemRepository.findByItemDetailEndingWith("설명1");
//			for(Item item : itemList) {
//				System.out.println(item.toString());
//			}
//		}
	}

	static void quiz2() {
//			@Test
//			@DisplayName("퀴즈1")
//			public void findByPriceGreaterThanEqualtest() {
//				this.createItemTest();
//				List<Item> itemList = itemRepository.findByPriceGreaterThanEqual(10005);
//				for (Item item : itemList) {
//					System.out.println(item.toString());
//				}
//			}
//			
//			@Test
//			@DisplayName("퀴즈2")
//			public void findByItemNmAndItemSellSatustest() {
//				this.createItemTest();
//				List<Item> itemList = itemRepository.findByItemNmAndItemSellSatus("테스트 상품1",ItemSellStatus.SELL);
//				for (Item item : itemList) {
//					System.out.println(item.toString());
//				}
//			}
	 }

	static void quiz3() {
//		@Test
//		@DisplayName("querydsl 퀴즈1")
//		public void test1() {
//			this.createItemTest();
//			//쿼리문을 쓰기위한 객체 생성
//			JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
//			
//			QItem item = QItem.item;
//			
//			JPAQuery<Item> query =  jpaQueryFactory.selectFrom(item).where(item.itemNm.eq("테스트 상품1"))
//					.where(item.itemSellStatus.eq(ItemSellStatus.SELL));
//			
//			List<Item> itemList = query.fetch();
//			
//			for(Item i : itemList) {
//				System.out.print(i.toString());
//			}
//		}
//		
//		@Test
//		@DisplayName("qureydsl 퀴즈2")
//		public void test2() {
//			this.createItemTest();
//			
//			JPAQueryFactory query = new JPAQueryFactory(em);
//			QItem item = QItem.item;
//			
//			JPAQuery<Item> query2 = query.selectFrom(item).where(item.price.between(10004, 10008));
//			
//			List<Item> itemList = query2.fetch();
//			
//			for(Item i : itemList) {
//				System.out.println(i.toString());
//			}
//		}
//
//		@Test
//		@DisplayName("querydsl 퀴즈3")
//		public void test3() {
//			this.createItemTest();
//			JPAQueryFactory query = new JPAQueryFactory(em);
//			QItem item = QItem.item;
//			
//			JPAQuery<Item> query2 = query.selectFrom(item).where(item.regTime.after(LocalDateTime.of(2023,01,01,12,12,44)));
//			
//			List<Item> itemList = query2.fetch();
//			
//			for(Item i : itemList) {
//				System.out.println(i.toString());
//			}
//		}
//		
//		@Test
//		@DisplayName("querydsl 퀴즈4")
//		public void test4() {
//			this.createItemTest();
//			JPAQueryFactory query = new JPAQueryFactory(em);
//			QItem item = QItem.item;
//			
//			JPAQuery<Item> query2 = query.selectFrom(item).where(item.itemSellStatus.isNotNull());
//			
//			List<Item> itemList = query2.fetch();
//			
//			for(Item i : itemList) {
//				System.out.println(i.toString());
//			}
//		}
//		
//		@Test
//		@DisplayName("querydsl 퀴즈5")
//		public void test5() {
//			this.createItemTest();
//			
//			JPAQueryFactory q = new JPAQueryFactory(em);
//			QItem qItem = QItem.item;
//			
//			JPAQuery<Item> qurey = q.selectFrom(qItem).where(qItem.itemDetail.like("%설명1"));
//			List<Item> itemList = qurey.fetch();
//			
//			for(Item i : itemList) {
//				System.out.println(i.toString());
//			}
//		}
	}
}
