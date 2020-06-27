package com.net.main.dto;

public class ResultVO {

	private String bizno;
	private String merchant;
	private String type;
	
	public ResultVO() { }
	
	public String getBizno() {
		return bizno;
	}
	public void setBizno(String bizno) {
		this.bizno = bizno;
	}
	public String getMerchant() {
		return merchant;
	}
	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ResultVO [bizno=" + bizno + ", merchant=" + merchant + ", type=" + type + "]";
	}
	
	
}
