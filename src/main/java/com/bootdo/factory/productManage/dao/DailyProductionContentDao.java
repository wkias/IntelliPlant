package com.bootdo.factory.productManage.dao;

import com.bootdo.factory.productManage.domain.DailyProductionContentDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author xgq
 * @email 1992lcg@163.com
 * @date 2020-04-04 14:47:59
 */
@Mapper
public interface DailyProductionContentDao {

	DailyProductionContentDO get(String dailyProductionContentId);
	
	List<DailyProductionContentDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(DailyProductionContentDO dailyProductionContent);
	
	int update(DailyProductionContentDO dailyProductionContent);
	
	int remove(String daily_production_content_id);
	
	int batchRemove(String[] dailyProductionContentIds);
}
