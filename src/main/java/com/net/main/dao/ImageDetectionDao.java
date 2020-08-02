package com.net.main.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.net.main.dto.ImageDto;

@Service
public class ImageDetectionDao {
	
	// 이미지 폴더 경로를 파라미터로 준다	
	private static final Pattern DETECTION_RESULT_PAT = Pattern.compile("\\[(.*)\\]");
	
	public List<ImageDto> doDection(String filePath) {
		List<ImageDto> imageDtoList = new ArrayList<>();
		System.out.println("이미지 감지 DAO 호출");
		System.out.println("이미지 저장 경로: " + filePath);
		//filePath -> C:/.../
		//docker run -v /usr/local/apache-tomcat-9.0.24/bin/src/main/resources/static/images:/app/resource/imgs detection --filename=file.jpg
		
		String path = filePath.substring(0, filePath.lastIndexOf("/"));
		String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
		
		// docker 실행 명령을 주면 된다
		List<String> command = new ArrayList<String>();
		String detectionList = "";
		command.add("/bash/bin");
		command.add("-c");
		command.add("docker run -v /usr/local/apache-tomcat-9.0.24/bin/src/main/resources/static/images:/app/resource/imgs detection --filename=" + fileName);
		
		//Process 명령어 실행
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		Process process = null;
		BufferedReader outReader = null;
		try {
			process = processBuilder.start();
			outReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			String line;
			while((line = outReader.readLine()) != null) {
				//image detection 반환 결과 추출
				if(DETECTION_RESULT_PAT.matcher(line).matches()) {
					detectionList = line;
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			process.destroy();
			if(outReader != null) {
				try {
					outReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		//[[x1, y1, w1, h1],[x2, y2, w2, h2]]형태 
		detectionList = detectionList.trim();
		detectionList = detectionList.replaceAll("[\\[\\]\\s+]", "");
		
		//x1,y1,w1,h1,x2,y2,w2,h2
		String[] detectionArray = detectionList.split(",");
		int imageDto_fields_size = ImageDto.class.getDeclaredFields().length;
		String[] valueArray = new String[imageDto_fields_size];
		
		for(int i = 0; i < detectionArray.length; i++) {
			valueArray[i % imageDto_fields_size] = detectionArray[i];
			
			if(i % imageDto_fields_size == (imageDto_fields_size - 1) && i > 0) {
				imageDtoList.add(new ImageDto(valueArray));
			}
			
		}
		return imageDtoList;
		
		//Object cmdResult = execCommand(callCmd);
		//Field[] fields = cmdResult.getClass().getDeclaredFields();
		//Map<Integer, Object> cmdResultMap = new HashMap();
		//[x y w h] [x y w h] [x y w h]....
		
		
		//docker 명령어 실행 시 어떠한 형태로 반환되는지 확인 필요..
		// 추후 명령어 정리 필요
		//execcommand x,y,w,h 배열 반환 dto 에 넣어준다.
		// 
		
		//int[][] dummyData = {{1,2,3,4},{5,6,7,8}};
		//return imageDtoList;
		// [[x1,y1,w1,h1], [x2,y2,w2,h2], ...] 아마도 return을 이런형태로 받으시면 될듯합니다-->		
	}
}
