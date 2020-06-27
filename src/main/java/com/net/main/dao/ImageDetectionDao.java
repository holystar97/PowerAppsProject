package com.net.main.dao;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.net.main.dto.ImageDto;

@Service
public class ImageDetectionDao {
	public ImageDto doDection(String img) {
		System.out.println("이미지 감지 DAO 호출");
		
		//이미지의 x,y,z 넓이 높이 지정
		//현재는 임의의 데이터로 set함
		ImageDto imageDto = new ImageDto();
		imageDto.setX(1.0);
		imageDto.setY(2.0);
		imageDto.setZ(3.0);
		imageDto.setHeight(10.0);
		imageDto.setWidth(20.0);
		
		return imageDto;
	}
}
