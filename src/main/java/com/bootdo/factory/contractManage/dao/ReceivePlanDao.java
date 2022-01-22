package com.bootdo.factory.contractManage.dao;

import com.bootdo.factory.contractManage.domain.ReceivePlanDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author willice
 * @email 2782546116@qq.com
 * @date 2020-03-06 12:08:15
 */
@Mapper
public interface ReceivePlanDao {

	ReceivePlanDO get(String receiveId);
	
	List<ReceivePlanDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ReceivePlanDO receivePlan);
	
	int update(ReceivePlanDO receivePlan);
	
	int remove(String receive_id);
	
	int batchRemove(String[] receiveIds);
}
