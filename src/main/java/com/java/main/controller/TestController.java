package com.java.main.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
public class TestController {
	
	//테스트 api
	@RequestMapping("/test")
	public Map<String, Object> test(){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("result", "testOK");
		
		return resultMap;
	}

}
