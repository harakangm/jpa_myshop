package com.myshop.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myshop.dto.ItemFormDto;
import com.myshop.entity.Item;
import com.myshop.entity.ItemImg;
import com.myshop.repository.ItemImgRepository;
import com.myshop.repository.ItemRepository;

import groovyjarjarantlr4.v4.parse.ANTLRParser.exceptionGroup_return;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {
	private final ItemRepository itemRepository;
	private final ItemImgService itemImgService;
	private final ItemImgRepository itemImgRepository;
	
	//상품 등록
	public Long saveItem(ItemFormDto itemFormDto,List<MultipartFile> itemImgFileList) throws Exception {
		
		//상품등록
		Item item =itemFormDto.CreateItem();
		itemRepository.save(item);
		
		//이미지 등록
		for(int i = 0; i < itemImgFileList.size(); i++) {
			ItemImg itemImg = new ItemImg();
			itemImg.setItme(item);
			
			//첫번쨰 이미지 일때 대표 상품 이미지 여부 지정
			if(i == 0) { 
				itemImg.setRepimgYn("Y");
			}else {
				itemImg.setRepimgYn("N");
			}
			
			itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
		}
		return item.getId();
	}
}
