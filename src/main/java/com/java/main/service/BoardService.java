package com.java.main.service;

import java.util.Map;

import com.java.main.bo.BoardBo;

public interface BoardService {

	public BoardBo selectBoardList(BoardBo param) throws Exception;
	
	public BoardBo selectText(BoardBo param) throws Exception;
	
	public BoardBo insertText(BoardBo param) throws Exception;
	
	public BoardBo updateText(BoardBo param) throws Exception;
	
	public BoardBo deleteText(BoardBo param) throws Exception;
}
