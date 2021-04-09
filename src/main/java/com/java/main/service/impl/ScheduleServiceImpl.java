package com.java.main.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.main.bo.ScheduleBo;
import com.java.main.mapper.ScheduleDao;
import com.java.main.service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleDao scheduleDao;
	
	@Override
	public ScheduleBo getSchedule() throws Exception {
		
		ScheduleBo result = new ScheduleBo();
		
		SimpleDateFormat checkSdf = new SimpleDateFormat("HH");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		Calendar cal = new GregorianCalendar();
		
		int hour = Integer.parseInt(checkSdf.format(cal.getTime()));
		String today = sdf.format(cal.getTime());
		cal.add(Calendar.DATE, 1);
		String tomorrow = sdf.format(cal.getTime());
		
		//오전 5시 이전 -> 하루를 뺀다.
		if(hour < 5) {
			cal = new GregorianCalendar();
			tomorrow = today;
			cal.add(Calendar.DATE, -1); // 오늘날짜로부터 -1
			today = sdf.format(cal.getTime());		
		}
		
		
		ScheduleBo param = new ScheduleBo();
		param.setToday(today);
		
		//오늘 일정.
		ScheduleBo todayBo = scheduleDao.selectSchedule(param);
	
		param.setToday(tomorrow);
		
		//내일 일정.
		ScheduleBo tomorrowBo = scheduleDao.selectSchedule(param);
		
		//예외처리.
		if(null == todayBo || null == todayBo.getJob()) {
			result.setToday("미정");
		}else {
			result.setToday(todayBo.getJob());
		}
		
		if(null == tomorrowBo || null == tomorrowBo.getJob()) {
			result.setTomorrow("미정");
		}else {
			result.setTomorrow(tomorrowBo.getJob());
		}
		
		
		
		return result;
	}

	@Override
	public ScheduleBo getScheduleNew() throws Exception {
		
	ScheduleBo result = new ScheduleBo();
		
		SimpleDateFormat checkSdf = new SimpleDateFormat("HH");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		SimpleDateFormat prefixSdf = new SimpleDateFormat("MM/dd");
		String todayPrefix = "";
		String tomorrowPrefix = "";
		
		Calendar cal = new GregorianCalendar();
		
		int hour = Integer.parseInt(checkSdf.format(cal.getTime()));
		String today = sdf.format(cal.getTime());
		todayPrefix = prefixSdf.format(cal.getTime());

		cal.add(Calendar.DATE, 1);
		String tomorrow = sdf.format(cal.getTime());
		tomorrowPrefix = prefixSdf.format(cal.getTime());
		
		//오전 5시 이전 -> 하루를 뺀다.
		if(hour < 5) {
			cal = new GregorianCalendar();
			tomorrow = today;
			tomorrowPrefix = todayPrefix;
			cal.add(Calendar.DATE, -1); // 오늘날짜로부터 -1
			today = sdf.format(cal.getTime());	
			todayPrefix = prefixSdf.format(cal.getTime());
		}
		
		
		ScheduleBo param = new ScheduleBo();
		param.setToday(today);
		
		//오늘 일정.
		ScheduleBo todayBo = scheduleDao.selectSchedule(param);
	
		param.setToday(tomorrow);
		
		//내일 일정.
		ScheduleBo tomorrowBo = scheduleDao.selectSchedule(param);
		
		//예외처리.
		if(null == todayBo || null == todayBo.getJob()) {
			result.setToday("미정");
		}else {
			result.setToday("오늘("+todayPrefix+") : "+todayBo.getJob());
		}
		
		if(null == tomorrowBo || null == tomorrowBo.getJob()) {
			result.setTomorrow("미정");
		}else {
			result.setTomorrow("내일("+tomorrowPrefix+") : "+tomorrowBo.getJob());
		}
		
		
		
		return result;
	}

}
