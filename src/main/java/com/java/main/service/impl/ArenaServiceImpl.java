package com.java.main.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.main.bo.ArenaInfoBo;
import com.java.main.mapper.ArenaDao;
import com.java.main.service.ArenaService;

@Service
public class ArenaServiceImpl implements ArenaService {

	
	@Autowired
	private ArenaDao arenaDao;
	
	
	//정렬. -> 캐릭터 순서에 관계없이 항상 같은 결과값을 보장.
	@Override
	public ArenaInfoBo sort(ArenaInfoBo param) throws Exception {
		
		int [] numArray = new int[5];
		
		numArray[0] = Integer.parseInt(param.getDf_1_character_id().substring(3, param.getDf_1_character_id().length()));
		numArray[1] = Integer.parseInt(param.getDf_2_character_id().substring(3, param.getDf_2_character_id().length()));
		numArray[2] = Integer.parseInt(param.getDf_3_character_id().substring(3, param.getDf_3_character_id().length()));
		numArray[3] = Integer.parseInt(param.getDf_4_character_id().substring(3, param.getDf_4_character_id().length()));
		numArray[4] = Integer.parseInt(param.getDf_5_character_id().substring(3, param.getDf_5_character_id().length()));
		
		Arrays.sort(numArray);
		
		ArenaInfoBo sorted = new ArenaInfoBo();
		
		String prefix = "ch_";
		
		sorted.setDf_1_character_id(prefix+numArray[0]);
		sorted.setDf_2_character_id(prefix+numArray[1]);
		sorted.setDf_3_character_id(prefix+numArray[2]);
		sorted.setDf_4_character_id(prefix+numArray[3]);
		sorted.setDf_5_character_id(prefix+numArray[4]);
		
		
		
		return sorted;
		
	}

	@Override
	public List<ArenaInfoBo> searchArena(ArenaInfoBo param) throws Exception {
		
		//정렬
		ArenaInfoBo sorted = this.sort(param);
		
		return arenaDao.searchArena(sorted);
		
	}

	
	
}
