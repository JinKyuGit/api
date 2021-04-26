package com.java.main.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.main.bo.CommentBo;
import com.java.main.service.CommentService;

@RestController
public class CommentController {

	@Autowired
	private CommentService commentSerivce;
	
	
	@RequestMapping("/insertComment")
	public Map<String, Object> insertComment(@RequestBody CommentBo param) throws Exception{
	
		Map<String, Object> resultMap = new HashMap<>();	
		
		resultMap.put("result", commentSerivce.insertComment(param));
		
		return resultMap;
	}
}
