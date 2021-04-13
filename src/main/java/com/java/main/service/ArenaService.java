package com.java.main.service;

import java.util.List;

import com.java.main.bo.ArenaInfoBo;

public interface ArenaService {

	public ArenaInfoBo sort(ArenaInfoBo param) throws Exception;
	
	public List<ArenaInfoBo> searchArena(ArenaInfoBo param) throws Exception;
}
