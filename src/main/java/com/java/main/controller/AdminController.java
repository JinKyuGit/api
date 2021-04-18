package com.java.main.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.main.bo.AdminBo;
import com.java.main.bo.ArenaInfoBo;
import com.java.main.service.AdminService;
import com.java.main.service.ArenaService;

@RestController
public class AdminController {

	
		@Autowired
		private AdminService adminService;
		
		@Autowired
		private ArenaService arenaSerivce;
	   
		@RequestMapping("/adminLogin")
		public Map<String, Object> adminLogin(@RequestBody AdminBo param) throws Exception{
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			
			resultMap.put("result", adminService.selectOneAdmin(param));
			
			return resultMap;
		}
		
		@RequestMapping("/getChacterInfo")
		public Map<String, Object> getChacterInfo() throws Exception {
			
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			
			resultMap.put("result", adminService.selectCharacterList());
			
			return resultMap;
			
		}
		
		@RequestMapping("/searchArenaAdmin")
		public Map<String, Object> searchArenaAdmin(@RequestBody ArenaInfoBo param) throws Exception{
			
			
			Map<String, Object> resultMap = new HashMap<String, Object>();

			resultMap.put("result", arenaSerivce.searchArenaAdmin(param));
			
			return resultMap;
		}
		
		@RequestMapping("/deleteArena")
		public Map<String, Object> deleteArena(@RequestBody ArenaInfoBo param) throws Exception{
			
			
			Map<String, Object> resultMap = new HashMap<String, Object>();

			resultMap.put("result", arenaSerivce.deleteArenaInfo(param));
			
			return resultMap;
		}
		
		@RequestMapping("/readFile")
	    public Map<String, Object> readFile() throws Exception{
			
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			
			arenaSerivce.translateOne();
			
			resultMap.put("result", "OK");
			
			return resultMap;
		}
		
		@Transactional
		@RequestMapping("/insertByFile")
	    public Map<String, Object> insertByFile() throws Exception{
			
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			
			arenaSerivce.insertByFile();
			
			resultMap.put("result", "OK");
			
			return resultMap;
		}
}
