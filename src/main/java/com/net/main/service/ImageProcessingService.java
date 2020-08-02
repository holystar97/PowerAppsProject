package com.net.main.service;

import org.springframework.web.multipart.MultipartFile;

import com.net.main.dto.ResultVO;

interface ImageProcessingService {
	public ResultVO imageProcessing(MultipartFile file);
}
