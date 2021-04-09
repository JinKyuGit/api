package com.java.main.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.main.bo.AdminBo;
import com.java.main.service.AdminService;

@RestController
public class AdminController {

	
		@Autowired
		private AdminService adminService;
	   
		@RequestMapping("/adminLogin")
		public Map<String, Object> test(@RequestBody AdminBo param) throws Exception{
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			
			//임시.
			resultMap.put("result", null);
			
			//resultMap.put("result", adminService.selectOneAdmin(param));
			
			return resultMap;
		}
}
