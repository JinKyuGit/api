package com.java.main.service;

import java.util.List;

import com.java.main.bo.AdminBo;
import com.java.main.bo.CharacterBo;

public interface AdminService {
	
	public AdminBo selectOneAdmin(AdminBo param) throws Exception;

	public List<CharacterBo> selectCharacterList() throws Exception;
	
}
