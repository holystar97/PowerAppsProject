package com.net.main.dao;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.imgscalr.Scalr;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.net.main.dto.ImageDto;
import com.net.main.dto.ResultVO;

@Service
public class ImageRecognitionDao {

	public ResultVO doRecognition(List<ImageDto> imageDtoList, String filePath) throws IOException {
		System.out.println("이미지 인식 dao 호출");

		ResultVO resultVo = new ResultVO();
		/*
		 * if(true) { resultVo = new ResultVO(); resultVo.setBizno("111-45-84729");
		 * resultVo.setMerchant("옥돌상회"); resultVo.setType("유통업");
		 * System.out.println("## 이미지 인식 성공 ##"); }
		 */

		MatOfByte buffer = new MatOfByte();

		File rectFoundImageFile = new File(filePath);
		if (!rectFoundImageFile.exists()) {
			rectFoundImageFile.createNewFile();
		}
		FileUtils.writeByteArrayToFile(rectFoundImageFile, buffer.toArray());

		BufferedImage rectFoundImage = ImageIO.read(rectFoundImageFile);

		// 정의준 : cropped img를 base64형태로 저장
		// 수정 필요, 그대로는 못씁니다
		List<String> imageBase64List = new ArrayList<String>();
		// BASE64Encoder encoder = new BASE64Encoder();
		Encoder encoder = Base64.getEncoder();
		// crop Line은 무시해야합니다.
		// imgDto로 받은 list size를 cropLine대신 줘야할듯
		for(int i = 0; i < imageDtoList.size(); i++) {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			// Scalr.crop을 적절하게 이용하여 이미지 crop
			// ex> Scalr.crop(img, x, y, w, h, ??이부분은 몰라요)

//					int[] array_x=new int[imageDto.getX().size()];
//					for(int k=0;k<array_x.length;k++) {
//				         array_x[i] = imageDto.getX().get(i);
//				      }
//					int[] array_y=new int[imageDto.getY().size()];
//					for(int k=0;k<array_y.length;k++) {
//				         array_y[i] = imageDto.getY().get(i);
//				      }
//					int[] array_w=new int[imageDto.getW().size()];
//					for(int k=0;k<array_w.length;k++) {
//				         array_w[i] = imageDto.getW().get(i);
//				      }
//					int[] array_h=new int[imageDto.getH().size()];
//					for(int k=0;k<array_h.length;k++) {
//				         array_h[i] = imageDto.getH().get(i);
//				      }
			ImageDto tempDto = imageDtoList.get(i);
			BufferedImage scaledImage = Scalr.crop(rectFoundImage, tempDto.getX(), tempDto.getY(), tempDto.getW(),
					tempDto.getH(), null);
			ImageIO.write(scaledImage, "jpg", bos);

//					for(int j=0; j<array_x.length;j++) {
//						BufferedImage scaledImage = Scalr.crop(rectFoundImage,array_x[j],array_y[j],array_w[j],array_h[j],null);	
//						ImageIO.write(scaledImage, "jpg", bos);
//					}

			// File tempFile = new File(croppedImg + "_" + (i+1) + ".jpg");
			// ImageIO.write(scaledImage, "jpg", tempFile);
			imageBase64List.add(encoder.encode(bos.toByteArray()).toString());
		}
		// http로 base64 image 전송
		String paramHead = "{\"instances\":[{\"image\":{\"b64\":\"";
		String paramTail = "\"}}]}";
		String paramJson = "";
		String requestURL = "http://101.101.211.5:8501/v1/models/ocr:predict";
		String result = "";

		// 이미지 전송 및 리턴 코드
		List<String> list = null;
		for (String base64 : imageBase64List) {
			base64 = base64.replace("\r\n", "");
			paramJson = paramHead + base64 + paramTail;
			try {
//						uploadedImageFile.delete();
				rectFoundImageFile.delete();

				// 이미지 전송
				URL postUrl = new URL(requestURL);
				HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
				connection.setDoOutput(true);
				connection.setInstanceFollowRedirects(false);
				connection.setRequestMethod("POST");
				connection.setRequestProperty("Content-Type", "application/json");
				OutputStream os = connection.getOutputStream();
				os.write(paramJson.getBytes("UTF-8"));
				os.flush();
				////////////////////////////////////////////////////////////////////////////////////////

				// 결과 리턴
				BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

				String output = "";

				while ((output = br.readLine()) != null) {
					result += output;
				}
				connection.disconnect();
			} catch (Exception e) {
				e.printStackTrace();

			}

			/// 결과를 list에 담는 부분
			list = new ArrayList<String>();
			Map predictResultMap = new Gson().fromJson(result, Map.class);
			String predictResultString = "";
			if (predictResultMap.containsKey("predictions") && predictResultMap.get("predictions") != null) {
				List<List<Double>> temp = (List<List<Double>>) predictResultMap.get("predictions");
				List<Double> unicodes = temp.get(0);
				if (unicodes == null || unicodes.isEmpty()) {

				}

				for (Double unicode : unicodes) {
					Long l = Long.parseUnsignedLong("" + Math.round(unicode));
					String hex = Long.toHexString(l);
					int hash = Integer.decode("0x" + hex);
					if (hash < 44032 || hash > 55203) {
						continue;
					}
					predictResultString += new String(Character.toChars(hash));
				}
			}
			// 최종 결과 list
			list.add(predictResultString);
		}

		resultVo.addAll(list);

		return resultVo;
	}
}
