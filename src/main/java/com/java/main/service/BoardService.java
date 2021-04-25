package com.java.main.service;

import java.util.Map;

import com.java.main.bo.BoardBo;

public interface BoardService {

	public BoardBo selectBoardList(BoardBo param) throws Exception;
	
	public BoardBo selectText(BoardBo param) throws Exception;
	
	public Map<String, Object> insertText(BoardBo param) throws Exception;
}
