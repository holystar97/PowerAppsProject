package com.net.main;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.net.main.dao.TextUploadDao;
import com.net.main.dto.ResultVO;

import io.swagger.annotations.ApiOperation;

@RestController
public class TextUploadController {
	@Autowired
	//private ImageProcessingServiceImpl imageProcessingServiceImpl;
	private TextUploadDao textUploadDao;
	
	@RequestMapping(path = "/textupload", method = RequestMethod.POST)
	//Swagger-ui에 등록 어노테이션
	@ApiOperation(value = "TextUploadSample", tags="TextUploadSample")
	public @ResponseBody void add(@RequestParam HashMap<String, String> resultObj) throws Exception {
		System.out.println("### TextUpload 진행 ###");
		for(String key : resultObj.keySet()) {
			System.out.println(key + " : " + resultObj.get(key));
		}
		//결과물 텍스트 업로드 진행
		textUploadDao.upload(resultObj);
		
		//System.out.println(resultVo.toString());
		
	    //Return
	}
}
