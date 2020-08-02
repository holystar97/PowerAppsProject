package com.net.main.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResultVO {
	
	
	private List<String> resultVO;
	private String resultFlag;

	public ResultVO() {
		resultVO = new ArrayList<String>();
	}
	
	public String getResultFlag() {
		return resultFlag;
	}

	public void setResultFlag(String resultFlag) {
		this.resultFlag = resultFlag;
	}
	public List<String> getResultVO() {
		return resultVO;
	}

	public void setResultVO(List<String> resultVO) {
		this.resultVO = resultVO;
	}

	public void addAll(List data) {
		//매개변수로 List<DTO>가 옴
		data = Objects.requireNonNull(data);
		resultVO.addAll(data);
	}
	
}
