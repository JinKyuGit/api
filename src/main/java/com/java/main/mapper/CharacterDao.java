package com.java.main.mapper;

import org.springframework.stereotype.Repository;

import com.java.main.bo.CharacterBo;
import com.java.main.common.CommonDao;

@Repository
public class CharacterDao extends CommonDao {

	public CharacterDao() {
		this.nameSpace = "characterDao";
	}
	
	public CharacterBo selectCharacterById(CharacterBo param) throws Exception{
		return selectOne("selectCharacterById", param);
	}
	
}
