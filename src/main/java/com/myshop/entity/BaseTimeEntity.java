package com.myshop.entity;


import java.time.LocalDateTime;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.*;


//등록과 수정 시간을 상속할 엔티티클래스

@EntityListeners(value = {AuditingEntityListener.class}) // auditing을 적용하기 위해쓰는 어노테이션
@MappedSuperclass //부모 클래스를 상속받는 자식 클래스한테 매핑정보만 제공
@Getter
@Setter
public class BaseTimeEntity {
	
	@CreatedDate //엔티티가 생성되서 저장될때 시간을 자동으로 저장.
	@Column(updatable =false) // 수정불가 컬럼
	private LocalDateTime regTime; //등록날짜
	
	
	@LastModifiedDate
	private LocalDateTime upDateTime; //수정날짜
}
