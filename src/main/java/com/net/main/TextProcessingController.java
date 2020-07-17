package com.net.main;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.net.main.service.TextProcessingServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
public class TextProcessingController {
	@Autowired
	private TextProcessingServiceImpl textProcessingServiceImpl;

	@RequestMapping(path = "/textupload", method = RequestMethod.POST)
	@ApiOperation(value = "TextUploadSample", tags="TextUploadSample")
	public void add(@RequestParam HashMap<String, String> resultObj) throws Exception {
		System.out.println("### TextProcessingController ###");
		textProcessingServiceImpl.textProcessing(resultObj);
	}
}
