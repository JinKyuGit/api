package com.java.main.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.main.bo.AdminBo;
import com.java.main.bo.ArenaInfo;

@RestController
public class ArenaController {

	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/searchArena")
	public Map<String, Object> test(@RequestBody ArenaInfo param) throws Exception{
		
		logger.error("check : >>>>");
		logger.error(param.getDf_1_character_id());
		logger.error(param.getDf_2_character_id());
		logger.error(param.getDf_3_character_id());
		logger.error(param.getDf_4_character_id());
		logger.error(param.getDf_5_character_id());
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		//임시.
		resultMap.put("result", new ArrayList<ArenaInfo>());
		
		//resultMap.put("result", adminService.selectOneAdmin(param));
		
		return resultMap;
	}
	
	
}