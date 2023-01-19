package com.myshop.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.Getter;
import lombok.Setter;

//등록자와 수정자를 상속할 엔티티클래스

@EntityListeners(value = {AuditingEntityListener.class}) // auditing을 적용하기 위해쓰는 어노테이션
@MappedSuperclass //부모 클래스를 상속받는 자식 클래스한테 매핑정보만 제공
@Getter
public class BaseEntity extends BaseTimeEntity{
	
	@CreatedBy //등록자를 자동으로 저장
	@Column(updatable = false)
	private String creatBy; //등록자
	
	
	@LastModifiedBy // 수정자를 자동으로 저장
	private String modifiedBy; //수정자
	
}
