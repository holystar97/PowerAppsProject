package com.net.main.dto;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ImageDto {
	
	private int x;
	private int y;
	private int w; 
	private int h; 
	
	public ImageDto(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public ImageDto() {
	}
	
	public ImageDto(String[] valueArray) {
		Field[] fields = this.getClass().getDeclaredFields();
		if(fields.length == valueArray.length) {
			for(int i = 0; i < valueArray.length; i++) {
				try {
					fields[i].set(this, Integer.parseInt(valueArray[i]));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setW(int w) {
		this.w = w;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getY() {
		return y;
	}
	public int getW() {
		return w;
	}
	public int getH() {
		return h;
	}

}
