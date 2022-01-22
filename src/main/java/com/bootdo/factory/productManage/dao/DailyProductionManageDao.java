package com.bootdo.factory.productManage.dao;

import com.bootdo.factory.productManage.domain.DailyProductionManageDO;

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
public interface DailyProductionManageDao {

	DailyProductionManageDO get(String dailyProductionId);
	
	List<DailyProductionManageDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(DailyProductionManageDO dailyProductionManage);
	
	int update(DailyProductionManageDO dailyProductionManage);
	
	int remove(String daily_production_id);
	
	int batchRemove(String[] dailyProductionIds);
}
