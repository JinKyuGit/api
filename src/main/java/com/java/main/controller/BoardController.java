package com.java.main.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.main.bo.BoardBo;
import com.java.main.service.BoardService;

@RestController
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/selectBoardList")
	public Map<String, Object> selectBoardList(@RequestBody BoardBo param) throws Exception{
		
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("result", boardService.selectBoardList(param));
		
		return resultMap;
	}
	
	@RequestMapping("/selectText")
	public Map<String, Object> selectText(@RequestBody BoardBo param) throws Exception{
		
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("result", boardService.selectText(param));
		
		return resultMap;
	}
	
	@RequestMapping("/insertText")
	public Map<String, Object> insertText(@RequestBody BoardBo param) throws Exception{
		
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("result", boardService.insertText(param));
		
		return resultMap;

	}
	
	@RequestMapping("/updateText")
	public Map<String, Object> updateText(@RequestBody BoardBo param) throws Exception{
		
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("result", boardService.updateText(param));
		
		return resultMap;

	}
	
	@RequestMapping("/deleteText")
	public Map<String, Object> deleteText(@RequestBody BoardBo param) throws Exception{
		
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("result", boardService.deleteText(param));
		
		return resultMap;

	}
}
