package com.java.main.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.java.main.bo.ArenaInfoBo;
import com.java.main.common.CommonDao;

@Repository
public class ArenaDao extends CommonDao {
	
	public ArenaDao() {
		this.nameSpace = "arenaDao";
	}
	
	public List<ArenaInfoBo> searchArena(ArenaInfoBo param) throws Exception{
		return selectList("searchArena", param);
	}

}
