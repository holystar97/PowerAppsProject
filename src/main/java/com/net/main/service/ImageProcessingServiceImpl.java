package com.net.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.net.main.dao.ImageDetectionDao;
import com.net.main.dao.ImageRecognitionDao;
import com.net.main.dao.ImageUploadDao;
import com.net.main.dto.ImageDto;
import com.net.main.dto.ResultVO;

@Service
public class ImageProcessingServiceImpl implements ImageProcessingService {
	@Autowired
	private ImageUploadDao imageUploadDao;
	
	@Autowired
	private ImageRecognitionDao imageRecognitionDao;
	
	@Autowired
	private ImageDetectionDao imageDetectionDao;
	
	@Override
	public ResultVO imageProcessing(MultipartFile file) {
		//분석 결과 저장
		ResultVO resultVo = null;
		//이미지 인식 결과 저장
		ImageDto imageDto = null;
		
		if(imageUploadDao.upload(file)) {
			System.out.println("## 이미지 업로드 성공 ##");
			
			//이미지 감지
			imageDto = imageDetectionDao.doDection("이미지 더미 데이터");

			//이미지 인식(이미지 분석 후 ResulVO를 추출하는 곳인듯??)
			resultVo = imageRecognitionDao.doRecognition(imageDto);
			if(resultVo == null) {
				System.out.println("## 이미지 인식 실패 ##");
			}
			
		} else {
			System.out.println("## 이미지 업로드 실패 ##");
			return null;
		}

		
		return resultVo;
	}
}
