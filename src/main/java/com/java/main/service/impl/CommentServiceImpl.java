package com.java.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.main.bo.CommentBo;
import com.java.main.mapper.CommentDao;
import com.java.main.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;
	
	
	@Override
	public CommentBo insertComment(CommentBo param) throws Exception {

		CommentBo resultBo = new CommentBo();
		
		try {
			
			int result = commentDao.insertComment(param);
			
			if(result == 1) {
				resultBo.setResultMsg("댓글을 달았습니다.");
			}else {
				resultBo.setResultMsg("오류 발생. 히트야에게 문의");
			}
			
		} catch(Exception e) {
			resultBo.setResultMsg("오류 발생. 히트야에게 문의 "+e.getMessage());
		}
		
		
		return resultBo;
	}

}
