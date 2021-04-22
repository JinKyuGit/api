package com.java.main.service;

import java.util.List;
import java.util.Map;

import com.java.main.bo.AdminBo;
import com.java.main.bo.CharacterBo;
import com.java.main.bo.TraslationBo;

public interface AdminService {
	
	public AdminBo selectOneAdmin(AdminBo param) throws Exception;

	public List<CharacterBo> selectCharacterList() throws Exception;
	
	public Map<String, Object> translate(TraslationBo param) throws Exception;
	
	public Map<String, Object> arenaRegisterByAdmin(TraslationBo param) throws Exception;
}
