package com.java.main.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.java.main.bo.CommentBo;
import com.java.main.common.CommonDao;

@Repository
public class CommentDao extends CommonDao {

	public CommentDao() {
		this.nameSpace = "commentDao";
	}
	
	public List<CommentBo> selectListComment(CommentBo param) throws Exception{
		return selectList("selectListComment", param);
	}
	
	public int insertComment(CommentBo param) throws Exception {
		return insert("insertComment", param);
	}
	
	public CommentBo selectCountComment(CommentBo param) throws Exception {
		return selectOne("selectCountComment", param);
	}
	
}
