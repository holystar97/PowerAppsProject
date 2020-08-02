package com.net.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;

public class FileUtility {
   
   /* 파일 저장 날짜 형식 지정 */
   private final static SimpleDateFormat FILE_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd-HHmmss");
   /* 디렉터리 저장 날짜 형식 지정 */
   private final static SimpleDateFormat DIR_DATE_FORMAT = new SimpleDateFormat("yyyyMM");
   
   private final static FileType[] filetype = FileType.values();

   public static void main(String[] args) throws InterruptedException {
/*	   
      System.out.println("###### Path List ######");
      System.out.println(FileType.IMAGE_PATH.getType());
      System.out.println(FileType.PRE_TEXT_PATH.getType());
      System.out.println(FileType.RESULT_TEXT_PATH.getType());
      System.out.println("###### File List ######");
      
      String origin = "/Users/mkrice/Desktop/test.png";
      System.out.println(getFileExtension(origin));
      System.out.println(getFilePathByType(origin));
      System.out.println(readTextFile(origin));

      System.out.println("###### Directory List ######");
      
      String root = "/Users/mkrice/Desktop/resource/image";
      System.out.println(isDirectoryExist(root));
      System.out.println(makeDirectory(root));

      for (int i = 0; i < 10; i++) {
         System.out.println("filename: " + getDateFormat());
         Thread.sleep(1000);
      }
      */
	   String filename=FileUtility.getFileName("image");
	   
	   System.out.println(filename);
	   
	   String origin = "/Users/mkrice/Desktop/test.png";	   
	   String extension = FileUtility.getFileExtension(origin);
	   
	   String full = filename + "." + extension;
	   System.out.println(full);
	      
   }
   
   public static String getFilePathByType(String type) {
      String path = "";
      
      return path;
   }
   /*
    * 디렉토리 존재 파악
    * 월별 첨부파일 디렉토리 여부를 파악한다.
    * image/
    * └202007
    *    └20200710_111111_attachment.jpg
    *    └20200710_111112_attachment.jpg
    * └202008
    *    └20200812_111111_attachment.jpg
    *    └20200813_111112_attachment.jpg
    */
   public static boolean isDirectoryExist(String root) {
      File directory = new File(root);
      File[] fileList = directory.listFiles();
      
      for(int i = 0; i < fileList.length; i++) {
         File file = fileList[i];
         if(file.isDirectory()) {
            System.out.println("디렉토리:" + file.getName());
         }
      }
      return false;
   }

   public static boolean makeDirectory(String root) {
      boolean result = false;
      String dirName = DIR_DATE_FORMAT.format(new java.util.Date());
      System.out.println("root : " + root);
      System.out.println("dirName : " + dirName);
      
      if(!isDirectoryExist(root)) {
         File directory = new File(root + "/" + dirName);
         result = directory.mkdir();
      }
      
      return result;
   }
   

   /*
    * 파일을 지정된 경로에 저장
    */
   public static boolean saveTextFile(String type, String content) {
      
      String path = getFilePathByType(type);
      File file = new File(path);
      
      try (FileOutputStream     fos = new FileOutputStream(file);
          BufferedOutputStream bos = new BufferedOutputStream(fos)) 
      {
         bos.write(content.getBytes());
         bos.flush();
      } catch (IOException e) {
         e.printStackTrace();
         return false;
      }
      return true;
   }
   
   /*
    * 파일의 내용을 읽어옴
    */
   public static String readTextFile(String fileName) {
      
      if(fileName.contains(FileType.IMAGE.getType())) {
         System.out.println("이미지입니다.");
      } else if(fileName.contains(FileType.PRE_TEXT.getType())) {
         System.out.println("원본 텍스트입니다.");
      } else if(fileName.contains(FileType.RESULT_TEXT.getType())) {
         System.out.println("결과 텍스트입니다.");
      } else {
         System.out.println("없는 파일 유형입니다.");
      }
      
      return "";
   }
   
   /*
    *@return 파일의 확장자 
    */
   public static String getFileExtension(String fullFileName) {
      return fullFileName.substring(fullFileName.lastIndexOf(".") + 1);
   }
   
   /*
    * 현재 일자를 문자열로 반환
    * @return 현재일자
    */
   public static String getDateFormat() {
      return FILE_DATE_FORMAT.format(new java.util.Date());
   }
   
   /*
    * 파일명을 생성하여 문자열로 반환
    * @param  파일유형
    * @return 파일명
    */
   public static String getFileName(String type) {
      String fileName = "";
      String date = "";
      date=FileUtility.getDateFormat();
      if(type.equals("image")) {
    	  fileName=date+"_attachment";
      } else if(type.equals("pretext")) {
    	  fileName=date+"text";
      }else {
    	  fileName=date+"text";
      }
     
      return fileName;
   }
   
   public static boolean imageUpload(String path) {
      boolean result = false;
      
      return result;
   }
   
   public static boolean textUpload(String path) {
      boolean result = false;
      
      return result;      
   }
   
}