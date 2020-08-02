package com.net.main.dao;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.opencv.core.MatOfByte;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import java.util.Base64.*;

@Service
public class ImageUploadDao {

	private final static String FILE_PATH = new File("src/main/resources/static/images/").getAbsolutePath();
	private final static String FILE_TYPE = "attachment";

	public String upload(MultipartFile file) throws Exception {

		System.out.println("이미지 업로드 dao 호출");
		System.out.println(file.getOriginalFilename());
		String originalFileName = file.getOriginalFilename();
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		/*
		 * 현재시각(yyyyMMdd_HHmmss)을 기반으로 저장함 저장 시 ';'로 문자열 구분
		 * 20200712_145947_attachment.jpg
		 */
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String fileName = sdf.format(now) + "_" + FILE_TYPE + extension;

		// String path = "C:/Users/mkrice/Desktop/testimg/" + originalfileName;
		String path = FILE_PATH + "/" + fileName;
		System.out.println(path);
		File dest = new File(path);
		try {
			file.transferTo(dest);
		} catch (Exception e) {
			// 저장 실패 시 empty string 반환
			return "";
		}


		return path;
		}
}
