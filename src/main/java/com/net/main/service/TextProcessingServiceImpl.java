package com.net.main.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.net.main.dao.TextUploadDao;

@Service
public class TextProcessingServiceImpl implements TextProcessingService {

	@Autowired
	private TextUploadDao textUploadDao;
	//static 내부 저장 경로 지정
	private final static String usePath = "resulttext";

	@Override
	public void textProcessing(HashMap<String, String> resultObj) {
		System.out.println("### TextProcessingServiceImpl ###");
		for(String key : resultObj.keySet()) {
			System.out.println(key + " : " + resultObj.get(key));
		}
		//결과물 텍스트 업로드 진행
		textUploadDao.upload(resultObj, usePath);

	}
	
}
