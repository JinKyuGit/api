package com.java.main.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.java.main.bo.AdminBo;
import com.java.main.bo.CharacterBo;
import com.java.main.common.CommonDao;

@Repository
public class AdminDao extends CommonDao{
	
	public AdminDao() {
		this.nameSpace = "adminDao";
	}
	
	public AdminBo selectOneAdmin(AdminBo param) throws Exception {
		return selectOne("selectOneAdmin", param);
	}
	
	public List<CharacterBo> selectCharacterList() throws Exception {
		return selectList("selectCharacterList");
	}

}
