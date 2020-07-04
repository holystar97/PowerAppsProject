package com.net.main.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.google.common.io.Files;
import com.net.main.dto.ResultVO;

@Service
public class TextUploadDao {
	
	
	
	public boolean upload(HashMap<String, String> resultMap) {
		System.out.println("### TextUploadDao ###");
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		
		
		String fileName = sdf.format(now) + ".txt";
		
		
		StringBuffer sb = new StringBuffer();
		for(String key : resultMap.keySet()) {
			String value = resultMap.get(key);
			System.out.println("!!" + key + " : " + value + "!!");
			sb.append(key + ":" + value + ";");
		}
		

		System.out.println("ResultString: " + sb.toString());
		
		String path = new File("src/main/resources/static/text").getAbsolutePath() + "/" + fileName;
		try {
			Files.write(sb.toString().getBytes(), new File(path));
		} catch (IOException e) {
			return false;
		}
		
		return true;
	}

}
