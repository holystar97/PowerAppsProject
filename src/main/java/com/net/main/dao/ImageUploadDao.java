package com.net.main.dao;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageUploadDao {
	
	public boolean upload(MultipartFile file) {
		
		System.out.println("이미지 업로드 dao 호출");
		System.out.println(file.getOriginalFilename());
		String originalfileName = file.getOriginalFilename();
		//String path = "C:/Users/mkrice/Desktop/testimg/" + originalfileName;
		String path = new File("src/main/resources/static/images/").getAbsolutePath() +"/"+ originalfileName;
		System.out.println(path);
		File dest = new File(path);
		try {
			file.transferTo(dest);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

}
