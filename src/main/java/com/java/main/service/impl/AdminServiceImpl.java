package com.java.main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.main.bo.AdminBo;
import com.java.main.bo.CharacterBo;
import com.java.main.mapper.AdminDao;
import com.java.main.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	
	@Autowired
	private AdminDao adminDao;
	
	@Override
	public AdminBo selectOneAdmin(AdminBo param) throws Exception {
		return adminDao.selectOneAdmin(param);
	}

	@Override
	public List<CharacterBo> selectCharacterList() throws Exception {
		return adminDao.selectCharacterList();
	}

}
