package com.java.main.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.main.bo.ArenaInfoBo;
import com.java.main.bo.CharacterBo;
import com.java.main.mapper.ArenaDao;
import com.java.main.mapper.CharacterDao;
import com.java.main.service.ArenaService;
import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

@Service
public class ArenaServiceImpl implements ArenaService {

	
	@Autowired
	private ArenaDao arenaDao;
	
	@Autowired
	private CharacterDao characterDao;
	
	
	//정렬. -> 캐릭터 순서에 관계없이 항상 같은 결과값을 보장.
	//정렬기준 -> 캐릭터 priority
	@Override
	public ArenaInfoBo sort(ArenaInfoBo param) throws Exception {
		
		ArenaInfoBo sorted = new ArenaInfoBo();
		
		CharacterBo dfParam = new CharacterBo();
		
		CharacterBo [] dfList = new CharacterBo[5];
		
		dfParam.setCharacterId(param.getDf_1_character_id());
		dfList[0] = characterDao.selectCharacterById(dfParam);
		
		dfParam.setCharacterId(param.getDf_2_character_id());
		dfList[1] = characterDao.selectCharacterById(dfParam);
		
		dfParam.setCharacterId(param.getDf_3_character_id());
		dfList[2] = characterDao.selectCharacterById(dfParam);
		
		dfParam.setCharacterId(param.getDf_4_character_id());
		dfList[3] = characterDao.selectCharacterById(dfParam);
		
		dfParam.setCharacterId(param.getDf_5_character_id());
		dfList[4] = characterDao.selectCharacterById(dfParam);
		
		
		//삽입정렬 이용.
		CharacterBo [] dfListSorted = this.insertion(dfList);
		
		sorted.setDf_1_character_id(dfListSorted[0].getCharacterId());
		sorted.setDf_2_character_id(dfListSorted[1].getCharacterId());
		sorted.setDf_3_character_id(dfListSorted[2].getCharacterId());
		sorted.setDf_4_character_id(dfListSorted[3].getCharacterId());
		sorted.setDf_5_character_id(dfListSorted[4].getCharacterId());
		
		
		//공덱 데이터가 있을 경우 마찬가지로 처리.
		if(null != param.getOf_1_character_id() && !"".equals(param.getOf_1_character_id())) {
			
	        CharacterBo [] ofList = new CharacterBo[5];
	    	CharacterBo ofParam = new CharacterBo();
	        
	    	ofParam.setCharacterId(param.getOf_1_character_id());
			ofList[0] = characterDao.selectCharacterById(ofParam);
			
			ofParam.setCharacterId(param.getOf_2_character_id());
			ofList[1] = characterDao.selectCharacterById(ofParam);
			
			ofParam.setCharacterId(param.getOf_3_character_id());
			ofList[2] = characterDao.selectCharacterById(ofParam);
			
			ofParam.setCharacterId(param.getOf_4_character_id());
			ofList[3] = characterDao.selectCharacterById(ofParam);
			
			ofParam.setCharacterId(param.getOf_5_character_id());
			ofList[4] = characterDao.selectCharacterById(ofParam);

			//삽입정렬 이용.
			CharacterBo [] ofListSorted = this.insertion(ofList);
			
			sorted.setOf_1_character_id(ofListSorted[0].getCharacterId());
			sorted.setOf_2_character_id(ofListSorted[1].getCharacterId());
			sorted.setOf_3_character_id(ofListSorted[2].getCharacterId());
			sorted.setOf_4_character_id(ofListSorted[3].getCharacterId());
			sorted.setOf_5_character_id(ofListSorted[4].getCharacterId());
			
		}
		
		
		return sorted;
		
	}
	
	//삽입정렬 구현.
	public CharacterBo[] insertion(CharacterBo [] list) {
		
		int i, j;
		
		for(i = 1; i < list.length; i++) {
			
			CharacterBo key = new CharacterBo();
			key.setCharacterId(list[i].getCharacterId());
			key.setPriority(list[i].getPriority());
			
			for(j = i-1; j >= 0 && list[j].getPriority() > key.getPriority(); j--) {	
					list[j+1] = list[j];
			}
			list[j+1] = key;		
		}
		
		return list;
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

	@Override
	public void translateOne() throws Exception {
		
	
		String fileDir = "C://Users//DELL//Desktop//original.txt";
		String outFileDir = "C://Users//DELL//Desktop//translate1.txt";
		
		File file = new File(fileDir);
		File outFile = new File(outFileDir);
		
		BufferedReader inFile = new BufferedReader(new FileReader(file)); 
		
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outFileDir));
		
		String sLine = null; 
		while( (sLine = inFile.readLine()) != null ) {
			
			if(null == sLine || sLine.length() != 5) {
				bufferedWriter.write("?,?,?,?,?");	
			}else {
				for(int i = 0; i < sLine.length(); i++) {
					String temp = sLine.substring(i, i+1);
					
					CharacterBo param = new CharacterBo();
					param.setCharacterAlias(temp);
					List<CharacterBo> list = arenaDao.selectCharacterNameByAlias(param);
					
					if(null == list || list.size() > 1 || list.size() == 0) {
						bufferedWriter.write("?");
					}else {
						bufferedWriter.write(list.get(0).getCharacterName());
					}
					bufferedWriter.write(",");	
				}
			}
			
			
			bufferedWriter.newLine();
		} // while
		
		bufferedWriter.close();
		
		
		//1차 변환 후, 2차 변환을 실시해본다.
		inFile = new BufferedReader(new FileReader(file));
		File newFile = new File(outFileDir); // test
		BufferedReader inFile2 = new BufferedReader(new FileReader(newFile)); //new 
		
		File outFile2 = new File("C://Users//DELL//Desktop//translate2.txt");
		BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(outFile2));
		String alias = "";
		String [] translate1 = new String[5];
		
		while((alias = inFile.readLine()) != null){
			
			String temp = inFile2.readLine();
			
			translate1 = temp.split(",");		
			
			for(int i = 0; i < translate1.length; i++) {
				
				if("?".equals(translate1[i])) {
					
						//prev, next를 각각 구해야함.
						CharacterBo priorityParam = new CharacterBo();
						priorityParam.setCharacterAlias(alias.substring(i, i+1));
						
						//prev
						if(i != 0 && !"?".equals(translate1[i-1])) {
							
							CharacterBo prevParam = new CharacterBo();
							prevParam.setCharacterName(translate1[i-1]);
							
							CharacterBo prev = characterDao.selectCharacterByName(prevParam);
							priorityParam.setPrev(prev.getPriority());

						}
							//next
						if(i != 4 && !"?".equals(translate1[i+1])) {
							
							CharacterBo nextParam = new CharacterBo();
							nextParam.setCharacterName(translate1[i+1]);
							
							CharacterBo next = characterDao.selectCharacterByName(nextParam);
							priorityParam.setNext(next.getPriority());	
						}

						List<CharacterBo> list = characterDao.selectCharacterByPriority(priorityParam);
						
						if(null != list && list.size() == 1) {						
							translate1[i] = list.get(0).getCharacterName();											
						}
				} //if
				bufferedWriter2.write(translate1[i]+",");
			} // for
			bufferedWriter2.newLine();
		} // while
		
		bufferedWriter2.close();
		

	}

	@Override
	public void insertByFile() throws Exception {
		
		String fileDir = "C://Users//DELL//Desktop//insert2.txt";
		
		File file = new File(fileDir);
		
		BufferedReader inFile = new BufferedReader(new FileReader(file)); 
		
		String sLine = null; 
		int index = 1;
		
		while( (sLine = inFile.readLine()) != null ) {
			
			
			String [] args = sLine.split(",");
			
			if(args.length != 10) {
				System.out.println(sLine);
				throw new Exception();
			}
			
			ArenaInfoBo arenaInfoBo = new ArenaInfoBo();
			
			for(int i = 0; i < args.length; i++) {
				CharacterBo param = new CharacterBo();
				
				param.setCharacterName(args[i].replaceAll(" ", ""));
				
				CharacterBo idInfo = arenaDao.selectChracterIdByName(param);

				if(null == idInfo || idInfo.getCharacterId() == null) {
					System.out.println(args[i]+" "+(index)+"번째 줄");
					throw new Exception();
					
				}
				
				
			switch(i) {
				case 0:
					arenaInfoBo.setDf_1_character_id(idInfo.getCharacterId());
					break;
				case 1:
					arenaInfoBo.setDf_2_character_id(idInfo.getCharacterId());
					break;
				case 2:
					arenaInfoBo.setDf_3_character_id(idInfo.getCharacterId());
					break;
				case 3:
					arenaInfoBo.setDf_4_character_id(idInfo.getCharacterId());
					break;
				case 4:
					arenaInfoBo.setDf_5_character_id(idInfo.getCharacterId());
					break;
				case 5:
					arenaInfoBo.setOf_1_character_id(idInfo.getCharacterId());
					break;
				case 6:
					arenaInfoBo.setOf_2_character_id(idInfo.getCharacterId());
					break;
				case 7:
					arenaInfoBo.setOf_3_character_id(idInfo.getCharacterId());
					break;
				case 8:
					arenaInfoBo.setOf_4_character_id(idInfo.getCharacterId());
					break;
				case 9:
					arenaInfoBo.setOf_5_character_id(idInfo.getCharacterId());
					break;		
				}
			} // for
			index++;
			arenaInfoBo.setRegNick("단풍나무클랜");
			//정렬할 필요 없음.
			arenaDao.insertArenaInfo(arenaInfoBo);
	
		}
		
	}

	
	
}
