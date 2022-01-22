package com.bootdo.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bootdo.common.dao.CodeNumberDao;
import com.bootdo.common.domain.CodeNumberDO;
import com.bootdo.common.service.CodeNumberService;



@Service
public class CodeNumberServiceImpl implements CodeNumberService {
	@Autowired
	private CodeNumberDao codeNumberDao;
	
	@Override
	public CodeNumberDO get(String id){
		return codeNumberDao.get(id);
	}
	
	@Override
	public List<CodeNumberDO> list(Map<String, Object> map){
		return codeNumberDao.list(map);
	}

	@Override
	public int increaseCode(String codeType) {
		Map<String,Object> map=new HashMap<>();
		map.put("codeType",codeType);
		List<CodeNumberDO> numberDOS=list(map);
		if(numberDOS.isEmpty()){
			return 0;
		}else{
			CodeNumberDO numberDO=numberDOS.get(0);
			int newNumber=Integer.parseInt(numberDO.getCodeNumber())+1;
			numberDO.setCodeNumber(newNumber+"");
			return update(numberDO);
		}
	}

	@Override
	public Integer getCodeNumberByCodeType(String codeType) {
		Map<String,Object> map=new HashMap<>();
		map.put("codeType",codeType);
		List<CodeNumberDO> numberDOS=list(map);
		if(!numberDOS.isEmpty()){
			return Integer.parseInt(numberDOS.get(0).getCodeNumber());
		}else {
			CodeNumberDO codeNumber=new CodeNumberDO();
			codeNumber.setCodeType(codeType);
			codeNumber.setCodeNumber("1");
			codeNumber.setId(UUID.randomUUID().toString());
			save(codeNumber);
			return 1;
		}

	}

	@Override
	public int count(Map<String, Object> map){
		return codeNumberDao.count(map);
	}
	
	@Override
	public int save(CodeNumberDO codeNumber){
		return codeNumberDao.save(codeNumber);
	}
	
	@Override
	public int update(CodeNumberDO codeNumber){
		return codeNumberDao.update(codeNumber);
	}
	
	@Override
	public int remove(String id){
		return codeNumberDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return codeNumberDao.batchRemove(ids);
	}
	
}
