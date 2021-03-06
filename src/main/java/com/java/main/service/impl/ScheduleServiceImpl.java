package com.java.main.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

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
		
		Calendar cal = new GregorianCalendar(Locale.KOREA);
		
		int hour = Integer.parseInt(checkSdf.format(cal.getTime()));
		String today = sdf.format(cal.getTime());
		cal.add(Calendar.DATE, 1);
		String tomorrow = sdf.format(cal.getTime());
		
		//오전 5시 이전 -> 하루를 뺀다.
		if(hour < 5) {
			cal = new GregorianCalendar(Locale.KOREA);
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
		
	    //DB 시간 조회.
	    ScheduleBo base = scheduleDao.selectDate();
	    
	    String today;
	    String tomorrow;
	    String todayPrefix;
	    String tomorrowPrefix;
	    
	    //오전 5시 -> 하루가 지나지 않은 것으로 한다.
	    if(base.getCurrentHour() < 5) { 	
	    	today = base.getYesterday();
	    	tomorrow = base.getToday();	
	    }else {
	    	today = base.getToday();
	    	tomorrow = base.getTomorrow();
	    }
	    
	    todayPrefix = today.substring(4, 6)+"/"+today.substring(6, 8);
	    tomorrowPrefix = tomorrow.substring(4, 6)+"/"+tomorrow.substring(6, 8);
	    
		ScheduleBo param = new ScheduleBo();
		param.setToday(today);
		
		//오늘 일정.
		ScheduleBo todayBo = scheduleDao.selectSchedule(param);
	
		param.setToday(tomorrow);
		
		//내일 일정.
		ScheduleBo tomorrowBo = scheduleDao.selectSchedule(param);
	    
	    
		//예외처리.
		if(null == todayBo || null == todayBo.getJob()) {
			result.setToday("오늘("+todayPrefix+") : "+"미정(공지없음)");
		}else {
			result.setToday("오늘("+todayPrefix+") : "+todayBo.getJob());
		}
		
		if(null == tomorrowBo || null == tomorrowBo.getJob()) {
			result.setTomorrow("내일("+tomorrowPrefix+") : "+"미정(공지없음)");
		}else {
			result.setTomorrow("내일("+tomorrowPrefix+") : "+tomorrowBo.getJob());
		}
	    
		return result;
	}

}
