package com.java.main.mapper;

import org.springframework.stereotype.Repository;

import com.java.main.bo.ScheduleBo;
import com.java.main.common.CommonDao;

@Repository
public class ScheduleDao extends CommonDao{

	public ScheduleDao() {
		this.nameSpace = "scheduleDao";
	}
	
	public ScheduleBo selectDate() throws Exception {
		return selectOne("selectDate");
	}
	
	public ScheduleBo selectSchedule(ScheduleBo param) throws Exception {
		return selectOne("selectSchedule", param);
	}
	
}
