package com.java.main.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.java.main.bo.BoardBo;
import com.java.main.common.CommonDao;

@Repository
public class BoardDao extends CommonDao {

	public BoardDao() {
		this.nameSpace = "boardDao";
	}
	
	public BoardBo selectBoardListCount(BoardBo param) throws Exception{
		return selectOne("selectBoardListCount", param);
	}
	
	public List<BoardBo> selectBoardList(BoardBo param) throws Exception{
		return selectList("selectBoardList", param);
	}
	
	public BoardBo selectText(BoardBo param) throws Exception{
		return selectOne("selectText", param);
	}
	
	public int insertText(BoardBo param) throws Exception{
		return insert("insertText", param);
	}
}
