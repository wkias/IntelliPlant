package com.bootdo.factory.productManage.service;

import com.bootdo.factory.productManage.domain.DailyProductionContentDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xgq
 * @email 1992lcg@163.com
 * @date 2020-04-04 14:47:59
 */
public interface DailyProductionContentService {
	
	DailyProductionContentDO get(String dailyProductionContentId);
	
	List<DailyProductionContentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DailyProductionContentDO dailyProductionContent);
	
	int update(DailyProductionContentDO dailyProductionContent);
	
	int remove(String dailyProductionContentId);
	
	int batchRemove(String[] dailyProductionContentIds);
}
