package com.java.main.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.main.bo.BoardBo;
import com.java.main.mapper.BoardDao;
import com.java.main.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;
	
	@Override
	public BoardBo selectBoardList(BoardBo param) throws Exception {
		
		BoardBo resultBo = new BoardBo();

		//페이지당 글 수 10개로 고정.
		int rowPerPage = 10;
		//현재 페이지 - 파라미터
		int currentPage = param.getCurrentPage();
		
		
		BoardBo countBo =  boardDao.selectBoardListCount(param);
		
		if(null == countBo) {
			resultBo.setList(null);
			return resultBo;
		}
		
		int totalCount = countBo.getCount();
		
		//전체 페이지 수.
		int totalPage = totalCount / rowPerPage;
		
		//페이지 수 보정.
		if(totalCount % rowPerPage != 0) {
			totalPage++;
		}
		
		//게시글의 끝
		int limit = currentPage * rowPerPage;
		//게시글의 시작
		int offset = (currentPage-1) * rowPerPage;
		
		resultBo.setLimit(limit);
		resultBo.setOffset(offset);
		resultBo.setTitle(param.getTitle());
		resultBo.setCount(totalCount);
		resultBo.setTotalPage(totalPage);
		
		List<BoardBo> list = boardDao.selectBoardList(resultBo);
		
		if(null != list) {
			resultBo.setList(list);
		}
	
		return resultBo;
	}

	@Override
	public BoardBo selectText(BoardBo param) throws Exception {
		return boardDao.selectText(param);
	}

	@Override
	public  Map<String, Object> insertText(BoardBo param) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		
		try {
			
			int result = boardDao.insertText(param);
			
			if(result == 1) {
				resultMap.put("message", "등록되었습니다.");
			}else {
				resultMap.put("message", "에러발생. 히트야에게 문의");
			}
			
		} catch(Exception e) {
			resultMap.put("message", "에러발생. 히트야에게 문의 "+e.getMessage());
		}

		return resultMap;
				
	}

}
