package com.net.util;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.net.main.dto.ResultVO;

public class JsonUtil {   
   /*
    * Object의 멤버변수들을 Json형태로 변환해줌
    * @param  Object
    * @return String
    */
   public static String objectToJson(Object obj) {
      ObjectMapper mapper = new ObjectMapper();
      String jsonStr = "";
      
      try {
         jsonStr = mapper.writeValueAsString(obj);
         System.out.println(jsonStr);
      } catch (JsonGenerationException e) { 
         e.printStackTrace();
      } catch (JsonMappingException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
      
      return jsonStr;
   }

   /*
    * Object의 필드/값을 Json형태로 파일로 저장함
    * @param  Object
    * @return String
    */
   public static String writeJsonToFile(Object obj) {
      ObjectMapper mapper = new ObjectMapper();
      String jsonStr = "";
      File resultFile = new File("C:/Users/leeka/Desktop/jsontest/jsonresult.txt");
      try {
         mapper.writeValue(resultFile, obj);
      } catch (JsonGenerationException e) { 
         e.printStackTrace();
      } catch (JsonMappingException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
      
      return jsonStr;
   }

   /*
    * 문자열Json형태를 object로 변환
    * @param  Object
    * @return String
    */
   public static ResultVO jsonToObject(String jsonStr) {
      ObjectMapper mapper = new ObjectMapper();
      ResultVO resultObj = null;
      
      try {
    	 resultObj = mapper.readValue(jsonStr, ResultVO.class);
      } catch (JsonGenerationException e) { 
         e.printStackTrace();
      } catch (JsonMappingException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
      
      return resultObj;
   }
   
   
}