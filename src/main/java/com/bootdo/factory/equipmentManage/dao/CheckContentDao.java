package com.bootdo.factory.equipmentManage.dao;

import com.bootdo.factory.equipmentManage.domain.CheckContentDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author xgq
 * @email 1992lcg@163.com
 * @date 2020-02-25 14:32:31
 */
@Mapper
public interface CheckContentDao {

	CheckContentDO get(int checkId);
	
	List<CheckContentDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(CheckContentDO checkContent);
	
	int update(CheckContentDO checkContent);
	
	int remove(int check_id);
	
	int batchRemove(int[] checkIds);
}
