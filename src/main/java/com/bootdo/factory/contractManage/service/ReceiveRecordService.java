package com.bootdo.factory.contractManage.service;

import com.bootdo.factory.contractManage.domain.ReceiveRecordDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author willice
 * @email 2782546116@qq.com
 * @date 2020-03-07 16:38:35
 */
public interface ReceiveRecordService {
	
	ReceiveRecordDO get(String recordId);
	
	List<ReceiveRecordDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ReceiveRecordDO receiveRecord);
	
	int update(ReceiveRecordDO receiveRecord);
	
	int remove(String recordId);
	
	int batchRemove(String[] recordIds);
}
