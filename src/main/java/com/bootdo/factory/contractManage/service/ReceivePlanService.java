package com.bootdo.factory.contractManage.service;

import com.bootdo.factory.contractManage.domain.ReceivePlanDO;

import java.util.List;
import java.util.Map;

/**
 * @author willice
 * @email 2782546116@qq.com
 * @date 2020-03-06 12:08:15
 */
public interface ReceivePlanService {
	
	ReceivePlanDO get(String receiveId);
	
	List<ReceivePlanDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ReceivePlanDO receivePlan);
	
	int update(ReceivePlanDO receivePlan);
	
	int remove(String receiveId);
	
	int batchRemove(String[] receiveIds);
}
