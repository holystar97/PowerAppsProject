package com.net.main.dao;

import org.springframework.stereotype.Service;

import com.net.main.dto.ImageDto;
import com.net.main.dto.ResultVO;

@Service
public class ImageRecognitionDao {

	public ResultVO doRecognition(ImageDto imageDTO) {
		System.out.println("이미지 인식 dao 호출");
		
		ResultVO resultVo = null;
		if(true) {
			resultVo = new ResultVO();
			resultVo.setBizno("111-45-84729");
			resultVo.setMerchant("옥돌상회");
			resultVo.setType("유통업");
			System.out.println("## 이미지 인식 성공 ##");
		}
		
		return resultVo;
	}
}
