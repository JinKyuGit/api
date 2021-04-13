package com.java.main.service.impl;

import java.util.Arrays;
import org.springframework.stereotype.Service;
import com.java.main.bo.ArenaInfo;
import com.java.main.service.ArenaService;

@Service
public class ArenaServiceImpl implements ArenaService {

	//정렬. -> 캐릭터 순서에 관계없이 항상 같은 결과값을 보장.
	@Override
	public ArenaInfo sort(ArenaInfo param) throws Exception {
		
		String [] list = new String[5];
		
		list[0] = param.getDf_1_character_id();
		list[1] = param.getDf_2_character_id();
		list[2] = param.getDf_3_character_id();
		list[3] = param.getDf_4_character_id();
		list[4] = param.getDf_5_character_id();
		
		Arrays.sort(list);
		
		ArenaInfo sortedBo = new ArenaInfo();
		
		sortedBo.setDf_1_character_id(list[0]);
		sortedBo.setDf_2_character_id(list[1]);
		sortedBo.setDf_3_character_id(list[2]);
		sortedBo.setDf_4_character_id(list[3]);
		sortedBo.setDf_5_character_id(list[4]);
		
		return sortedBo;
	}

	
	
}
