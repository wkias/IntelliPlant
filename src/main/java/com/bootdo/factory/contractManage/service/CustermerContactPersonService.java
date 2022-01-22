package com.bootdo.factory.contractManage.service;

import com.bootdo.factory.contractManage.domain.CustermerContactPersonDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author zrj
 * @email 1992lcg@163.com
 * @date 2020-03-10 18:39:57
 */
public interface CustermerContactPersonService {
	
	CustermerContactPersonDO get(String contactPersonId);
	
	List<CustermerContactPersonDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CustermerContactPersonDO custermerContactPerson);
	
	int update(CustermerContactPersonDO custermerContactPerson);
	
	int remove(String contactPersonId);
	
	int batchRemove(String[] contactPersonIds);


}
