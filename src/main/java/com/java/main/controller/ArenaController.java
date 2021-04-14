package com.java.main.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.main.bo.AdminBo;
import com.java.main.bo.ArenaInfoBo;
import com.java.main.service.ArenaService;

@RestController
public class ArenaController {

	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ArenaService arenaSerivce;
	
	@RequestMapping("/searchArena")
	public Map<String, Object> searchArena(@RequestBody ArenaInfoBo param) throws Exception{
		
		
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("result", arenaSerivce.searchArena(param));
		
		return resultMap;
	}
	
	@Transactional
	@RequestMapping("/registerArena")
	public Map<String, Object> registerArena(@RequestBody ArenaInfoBo param) throws Exception{
		
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("result", arenaSerivce.register(param));
		
		return resultMap;
	}
	
	
}
