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
		
		//공덱이 있는 경우 공덱도 정렬	
		if(null != param.getOf_1_character_id() && !"".equals(param.getOf_1_character_id())){
			
			int [] ofArray = new int[5];
			
			ofArray[0] = Integer.parseInt(param.getOf_1_character_id().substring(3, param.getOf_1_character_id().length()));
			ofArray[1] = Integer.parseInt(param.getOf_2_character_id().substring(3, param.getOf_2_character_id().length()));
			ofArray[2] = Integer.parseInt(param.getOf_3_character_id().substring(3, param.getOf_3_character_id().length()));
			ofArray[3] = Integer.parseInt(param.getOf_4_character_id().substring(3, param.getOf_4_character_id().length()));
			ofArray[4] = Integer.parseInt(param.getOf_5_character_id().substring(3, param.getOf_5_character_id().length()));
			
			Arrays.sort(ofArray);
			
			sorted.setOf_1_character_id(prefix+ofArray[0]);
			sorted.setOf_2_character_id(prefix+ofArray[1]);
			sorted.setOf_3_character_id(prefix+ofArray[2]);
			sorted.setOf_4_character_id(prefix+ofArray[3]);
			sorted.setOf_5_character_id(prefix+ofArray[4]);
		}
		
		
		
		return sorted;
		
	}

	//조회
	@Override
	public List<ArenaInfoBo> searchArena(ArenaInfoBo param) throws Exception {
		
		//정렬
		ArenaInfoBo sorted = this.sort(param);
		
		return arenaDao.searchArena(sorted);
		
	}

	//등록
	@Override
	public ArenaInfoBo register(ArenaInfoBo param) throws Exception {
		
		ArenaInfoBo result = new ArenaInfoBo();
		
		result.setResultCode("OK");
		
		ArenaInfoBo sorted = this.sort(param);
		
		//닉네임 가져감
		if(null != param.getRegNick() && !"".equals(param.getRegNick())) {
			sorted.setRegNick(param.getRegNick());
		}
		
		//중복 체크
		
		ArenaInfoBo dup = arenaDao.duplicateCheck(sorted);
		
		if(null != dup && dup.getCnt() > 0) {
			result.setResultCode("DUPLICATE");
		}else {
			try {
				int insertResult = arenaDao.insertArenaInfo(sorted);	
				
				if(insertResult < 1) {
					result.setResultCode("ERROR");
				}else {
					result.setResultCode("OK");
				}				
			} catch(Exception e) {
				result.setResultCode("ERROR");
			}	
		}
		
		return result;
		
	}

	@Override
	public List<ArenaInfoBo> searchArenaAdmin(ArenaInfoBo param) throws Exception {
		
		//관리자페이지에서는 숫자만 받음. prefix처리
		String prefix = "ch_";
		
		param.setDf_1_character_id(prefix+param.getDf_1_character_id());
		param.setDf_2_character_id(prefix+param.getDf_2_character_id());
		param.setDf_3_character_id(prefix+param.getDf_3_character_id());
		param.setDf_4_character_id(prefix+param.getDf_4_character_id());
		param.setDf_5_character_id(prefix+param.getDf_5_character_id());
		
		
		//정렬
		ArenaInfoBo sorted = this.sort(param);
		
		return arenaDao.searchArenaAdmin(sorted);
		
		
	}

	@Override
	public ArenaInfoBo deleteArenaInfo(ArenaInfoBo param) throws Exception {
		
		ArenaInfoBo result =  new ArenaInfoBo();
		
		int deleteResult = arenaDao.deleteArenaInfo(param);
		
		if(deleteResult > 0) {
			result.setResultCode("OK");
		}else {
			result.setResultCode("ERROR");
		}
		
		
		return result;
	}

	
	
}
