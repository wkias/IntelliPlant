package com.bootdo.factory.productManage.service;

import com.bootdo.factory.productManage.domain.DailyProductionManageDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xgq
 * @email 1992lcg@163.com
 * @date 2020-04-04 14:47:59
 */
public interface DailyProductionManageService {
	
	DailyProductionManageDO get(String dailyProductionId);
	
	List<DailyProductionManageDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	String save(DailyProductionManageDO dailyProductionManage);
	
	int update(DailyProductionManageDO dailyProductionManage);
	
	int remove(String dailyProductionId);
	
	int batchRemove(String[] dailyProductionIds);
}
