package com.net.main.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.net.main.dao.ImageDetectionDao;
import com.net.main.dao.ImageRecognitionDao;
import com.net.main.dao.ImageUploadDao;
import com.net.main.dao.TextUploadDao;
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
	
	@Autowired
	private TextUploadDao textUploadDao;
	
	//분석 데이터 저장 경로
	private static final String usePath = "pretext";

	@Override
	public ResultVO imageProcessing(MultipartFile file)  {
		//분석 결과 저장
		ResultVO resultVo = null;
		//이미지 인식 결과 저장
		List<ImageDto> imageDtoList = new ArrayList();
		
		//이미지 저장 경로
		String filePath = "";
		try {
			filePath = imageUploadDao.upload(file);
		} catch (Exception e) { e.printStackTrace(); }
		if(!filePath.equals("")) {
			System.out.println("## 이미지 업로드 성공 ##");

			//이미지 감지결과 저장
			imageDtoList = imageDetectionDao.doDection(filePath);
			
			//이미지 인식(이미지 분석 후 ResulVO를 추출하는 곳)
			try {
				resultVo = imageRecognitionDao.doRecognition(imageDtoList,filePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(resultVo == null) {
				System.out.println("## 이미지 인식 실패 ##");
			}
			//분석 데이터 Text 업로드 진행
			HashMap<String, String> resultMap = new HashMap<String,String>();
			
			textUploadDao.upload(resultMap, usePath);
			
		} else {
			System.out.println("## 이미지 업로드 실패 ##");
			return null;
		}
	
		
		return resultVo;
	}
}
