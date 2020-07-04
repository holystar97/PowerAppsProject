package com.net.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.net.main.dto.ResultVO;
import com.net.main.service.ImageProcessingServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
public class ImageUploadController {
	@Autowired
	private ImageProcessingServiceImpl imageProcessingServiceImpl;
	
	//Multipartform/data 형식
	@RequestMapping(path = "/upload", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	//Swagger-ui에 등록 어노테이션
	//@ApiOperation(value = "ImageUploadSample", tags="sample")
	public @ResponseBody ResultVO add(@RequestParam("image") MultipartFile file) throws Exception {
	    
		ResultVO resultVo = imageProcessingServiceImpl.imageProcessing(file);
		
		System.out.println("## 클라이언트 반환 전 확인 ##");
		System.out.println(resultVo.toString());
		
	    //Return
	    return resultVo;
	}
}
