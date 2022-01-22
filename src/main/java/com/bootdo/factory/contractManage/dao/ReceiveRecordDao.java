package com.bootdo.factory.contractManage.dao;

import com.bootdo.factory.contractManage.domain.ReceiveRecordDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author willice
 * @email 2782546116@qq.com
 * @date 2020-03-07 16:38:35
 */
@Mapper
public interface ReceiveRecordDao {

	ReceiveRecordDO get(String recordId);
	
	List<ReceiveRecordDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ReceiveRecordDO receiveRecord);
	
	int update(ReceiveRecordDO receiveRecord);
	
	int remove(String record_id);
	
	int batchRemove(String[] recordIds);
}
