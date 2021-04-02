package com.java.main.cotroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.main.service.ScheduleService;

@RestController
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;
	
	@RequestMapping(value="/getSchedule", method=RequestMethod.GET)
	public Map<String, Object> getSchedule() throws Exception{
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("result", scheduleService.getSchedule());
		
		return resultMap;
	}
	
}
