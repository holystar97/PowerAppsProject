package com.net.main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.net.main.dto.ResultVO;
import com.net.main.dto.User;
import com.net.main.service.ImageProcessingServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
public class ImageUploadController {
	@Autowired
	private ImageProcessingServiceImpl imageProcessingServiceImpl;
	
	//Multipartform/data 형식
	@RequestMapping(path = "/upload", method = RequestMethod.POST)
	//Swagger-ui에 등록 어노테이션
	@ApiOperation(value = "ImageUploadSample", tags="sample")
	public ResultVO add(@RequestPart(value="file", required=true) MultipartFile file, 
			@RequestParam(name= "email", required =true) String email,
			@RequestParam(name="fullname", required=true) String fullname) throws Exception {
		System.out.println(email);
		System.out.println(fullname);	    
//		ResultVO resultVo = imageProcessingServiceImpl.imageProcessing(file);
//
//		System.out.println("## 클라이언트 반환 전 확인 ##");
//		System.out.println(resultVo.toString());

		ResultVO resultVo = new ResultVO();
		List list =new ArrayList<String>();
		list.add("사업자 번호: 김옥돌 짱 ");     
		
		resultVo.addAll(list);
		resultVo.setResultFlag("success");
	    //Return
		
	    return resultVo;
	}
}
