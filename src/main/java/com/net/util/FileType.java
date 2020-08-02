package com.net.util;

public enum FileType {
	   IMAGE("image"),
	   PRE_TEXT("pretext"),
	   RESULT_TEXT("resulttext"),
	   
	   IMAGE_PATH("PATH A"),
	   PRE_TEXT_PATH("PATH B"),
	   RESULT_TEXT_PATH("PATH C");
	   
	   private String type = "";
	   
	   FileType(String type) {
	      this.type = type;
	   }
	   
	   public boolean equals(FileType filetype) {
	      return this.getType().equals(filetype);
	   }
	   
	   
	   public boolean checkFileType(String type) {
		      for(FileType filetype : FileType.values() ) {
		         if(filetype.getType().equals(type)) {
		            return true;
		         }
		      }
		      
		      return false;
		   }
	   
	   
	   public String getType() {
	      return type;
	   }
}