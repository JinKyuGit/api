package com.java.main.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.main.bo.AdminBo;
import com.java.main.bo.ArenaInfoBo;
import com.java.main.bo.CharacterBo;
import com.java.main.bo.TraslationBo;
import com.java.main.mapper.AdminDao;
import com.java.main.mapper.ArenaDao;
import com.java.main.mapper.CharacterDao;
import com.java.main.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private ArenaDao arenaDao;
	@Autowired
	private CharacterDao characterDao;
	
	@Override
	public AdminBo selectOneAdmin(AdminBo param) throws Exception {
		return adminDao.selectOneAdmin(param);
	}

	@Override
	public List<CharacterBo> selectCharacterList() throws Exception {
		return adminDao.selectCharacterList();
	}

	@Override
	public Map<String, Object> translate(TraslationBo param) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		String dfTranslate1 = "";
		String ofTranslate1 = "";
		
		
		//1차 변환
		
		for(int i = 0; i < param.getDfAlias().length(); i++) {
			
			String alias = param.getDfAlias().substring(i, i+1);
			
			CharacterBo chBo = new CharacterBo();
			chBo.setCharacterAlias(alias);
			List<CharacterBo> list = arenaDao.selectCharacterNameByAlias(chBo);
			
			if(null == list || list.size() > 1 || list.size() == 0) {
				dfTranslate1+="?";
			}else {
				dfTranslate1+=list.get(0).getCharacterName();
			}
			dfTranslate1+=",";		
		}
		
		for(int i = 0; i < param.getOfAlias().length(); i++) {
			
			String alias = param.getOfAlias().substring(i, i+1);
			
			CharacterBo chBo = new CharacterBo();
			chBo.setCharacterAlias(alias);
			List<CharacterBo> list = arenaDao.selectCharacterNameByAlias(chBo);
			
			if(null == list || list.size() > 1 || list.size() == 0) {
				ofTranslate1+="?";
			}else {
				ofTranslate1+=list.get(0).getCharacterName();
			}
			ofTranslate1+=",";		
		}
		
		
		//2차 변환
		
		String [] dfList = dfTranslate1.split(",");
		String [] ofList = ofTranslate1.split(",");
		
		String dfTranslate2 = "";
		String ofTranslate2 = "";
				
		for(int i = 0; i < dfList.length; i++) {
			
			if("?".equals(dfList[i])) {
				
				//prev, next를 각각 구해야함.
				CharacterBo priorityParam = new CharacterBo();
				priorityParam.setCharacterAlias(param.getDfAlias().substring(i, i+1));
				
				//prev
				if(i != 0 && !"?".equals(dfList[i-1])) {
					
					CharacterBo prevParam = new CharacterBo();
					prevParam.setCharacterName(dfList[i-1]);
					
					CharacterBo prev = characterDao.selectCharacterByName(prevParam);
					priorityParam.setPrev(prev.getPriority());

				}
					//next
				if(i != 4 && !"?".equals(dfList[i+1])) {
					
					CharacterBo nextParam = new CharacterBo();
					nextParam.setCharacterName(dfList[i+1]);
					
					CharacterBo next = characterDao.selectCharacterByName(nextParam);
					priorityParam.setNext(next.getPriority());	
				}

				//거리값에 따라 특정.
				List<CharacterBo> list = characterDao.selectCharacterByPriority(priorityParam);
				
				if(null != list && list.size() == 1) {						
					dfList[i] = list.get(0).getCharacterName();											
				}
			}
			dfTranslate2 += (dfList[i]+",");
		} // for 방덱
		
		
		//공덱
		for(int i = 0; i < ofList.length; i++) {
			
			if("?".equals(ofList[i])) {
				
				//prev, next를 각각 구해야함.
				CharacterBo priorityParam = new CharacterBo();
				priorityParam.setCharacterAlias(param.getOfAlias().substring(i, i+1));
				
				//prev
				if(i != 0 && !"?".equals(ofList[i-1])) {
					
					CharacterBo prevParam = new CharacterBo();
					prevParam.setCharacterName(ofList[i-1]);
					
					CharacterBo prev = characterDao.selectCharacterByName(prevParam);
					priorityParam.setPrev(prev.getPriority());

				}
					//next
				if(i != 4 && !"?".equals(ofList[i+1])) {
					
					CharacterBo nextParam = new CharacterBo();
					nextParam.setCharacterName(ofList[i+1]);
					
					CharacterBo next = characterDao.selectCharacterByName(nextParam);
					priorityParam.setNext(next.getPriority());	
				}

				//거리값에 따라 특정.
				List<CharacterBo> list = characterDao.selectCharacterByPriority(priorityParam);
				
				if(null != list && list.size() == 1) {						
					ofList[i] = list.get(0).getCharacterName();											
				}
			}
			ofTranslate2 += (ofList[i]+",");
		} // for 공덱
		

		resultMap.put("df", dfTranslate2);
		resultMap.put("of", ofTranslate2);
		
		return resultMap;
	}

	@Override
	public Map<String, Object> arenaRegisterByAdmin(TraslationBo param) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		String [] dfList = param.getDfFull().split(",");
		String [] ofList = param.getOfFull().split(",");
		
		ArenaInfoBo arenaBo = new ArenaInfoBo();
		
		for(int i = 0; i < dfList.length; i++) {
			
			CharacterBo chbo = new CharacterBo();
			chbo.setCharacterName(dfList[i]);
			CharacterBo idBo = characterDao.selectCharacterByName(chbo);		
			
			switch(i) {
			case 0:
				arenaBo.setDf_1_character_id(idBo.getCharacterId());
				break;
			case 1:
				arenaBo.setDf_2_character_id(idBo.getCharacterId());
				break;
			case 2:
				arenaBo.setDf_3_character_id(idBo.getCharacterId());
				break;
			case 3:
				arenaBo.setDf_4_character_id(idBo.getCharacterId());
				break;
			case 4:
				arenaBo.setDf_5_character_id(idBo.getCharacterId());
				break;
			}
		}
		
		for(int i = 0; i < ofList.length; i++) {
			
			CharacterBo chbo = new CharacterBo();
			chbo.setCharacterName(ofList[i]);
			CharacterBo idBo = characterDao.selectCharacterByName(chbo);		
			
			switch(i) {
			case 0:
				arenaBo.setOf_1_character_id(idBo.getCharacterId());
				break;
			case 1:
				arenaBo.setOf_2_character_id(idBo.getCharacterId());
				break;
			case 2:
				arenaBo.setOf_3_character_id(idBo.getCharacterId());
				break;
			case 3:
				arenaBo.setOf_4_character_id(idBo.getCharacterId());
				break;
			case 4:
				arenaBo.setOf_5_character_id(idBo.getCharacterId());
				break;
			}
		}
		
		
		//중복체크
		ArenaInfoBo dup = arenaDao.duplicateCheck(arenaBo);
		
		if(null != param.getNick()) {
			arenaBo.setRegNick(param.getNick());
		}
		
		if(null != dup) {
			resultMap.put("result", "중복된 정보가 있습니다.");
		}else {
			try {
				int insertResult = arenaDao.insertArenaInfo(arenaBo);	
				
				if(insertResult < 1) {
					resultMap.put("result", "에러가 발생하였습니다. 히트야에게 문의하세요.");
				}else {
					resultMap.put("result", "등록에 성공하였습니다.");
				}				
			} catch(Exception e) {
				resultMap.put("result", "에러가 발생하였습니다. 히트야에게 문의하세요.");
			}	
		}
		
		
		
		
		
		return resultMap;
	}

}
