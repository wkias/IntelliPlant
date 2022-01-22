package com.bootdo.factory.productManage.service;

import com.bootdo.factory.productManage.domain.ProcessInputDO;

import java.util.List;
import java.util.Map;

/**
 * 工序投入
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-04-26 18:30:11
 */
public interface ProcessInputService {
	
	ProcessInputDO get(String id);
	
	List<ProcessInputDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ProcessInputDO processInput);
	
	int update(ProcessInputDO processInput);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
