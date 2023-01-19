package com.myshop.service;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.myshop.entity.ItemImg;
import com.myshop.repository.ItemImgRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {
	
	@Value("${itemImgLocation}")
	private String itemImgLocation; 
	
	private final ItemImgRepository itemImgRepository;
	
	private final FileService fileService;
	
	
	//MultipartFile 스프링에서 업로드하는 파일을 관리를 쉽게 해주는 클래스
	public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws Exception{
		String oriImgName = itemImgFile.getOriginalFilename();
		String imgName = "";
		String imgUrl = "";
		
		//파일 업로드
		if(!StringUtils.isEmpty(oriImgName)) {
			imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
			imgUrl = "/images/item/" + imgName;
			
		}
		//상품 이미지 정보 저장
		itemImg.updateItemImg(oriImgName, imgName, imgUrl);
		itemImgRepository.save(itemImg);
	}
	
	
}
