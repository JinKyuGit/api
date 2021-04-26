package com.java.main.bo;

import java.util.List;

public class BoardBo extends CommonBo {

	private int textNo;
	private String title;
	private String content;
	private String regNick;
	private String regDate;
	private int count;
	private int currentPage;
	private int limit;
	private int offset;
	private int totalPage;
	private String resultMsg;
	private String password;
	private List<BoardBo> list;
	private List<CommentBo> commentList;
	
	
	
	
	public List<CommentBo> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<CommentBo> commentList) {
		this.commentList = commentList;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	
	public List<BoardBo> getList() {
		return list;
	}
	public void setList(List<BoardBo> list) {
		this.list = list;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTextNo() {
		return textNo;
	}
	public void setTextNo(int textNo) {
		this.textNo = textNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegNick() {
		return regNick;
	}
	public void setRegNick(String regNick) {
		this.regNick = regNick;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	
	
}
