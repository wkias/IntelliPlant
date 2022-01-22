package com.bootdo.common.service;

import com.bootdo.common.domain.CodeNumberDO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-04-28 10:50:15
 */
public interface CodeNumberService {
	
	CodeNumberDO get(String id);

	Integer getCodeNumberByCodeType(String codeType);

	int increaseCode(String codeType);

	List<CodeNumberDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CodeNumberDO codeNumber);
	
	int update(CodeNumberDO codeNumber);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
