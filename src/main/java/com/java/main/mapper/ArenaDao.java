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
	
	public List<ArenaInfoBo> searchArenaAdmin(ArenaInfoBo param) throws Exception{
		return selectList("searchArenaAdmin", param);
	}
	
	public ArenaInfoBo duplicateCheck(ArenaInfoBo param) throws Exception {
		return selectOne("duplicateCheck", param);
	}
	
	public int insertArenaInfo(ArenaInfoBo param) throws Exception {
		return insert("insertArenaInfo", param);
	}
	
	public int deleteArenaInfo(ArenaInfoBo param) throws Exception {
		return delete("deleteArenaInfo", param);
	}

}
