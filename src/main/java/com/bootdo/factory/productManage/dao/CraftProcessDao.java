package com.bootdo.factory.productManage.dao;

import com.bootdo.factory.productManage.domain.CraftProcessDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-03-26 11:53:33
 */
@Mapper
public interface CraftProcessDao {

	CraftProcessDO get(String id);
	
	List<CraftProcessDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(CraftProcessDO craftProcess);
	
	int update(CraftProcessDO craftProcess);

	int removeByCraftId(String craftId);

	int remove(String id);
	
	int batchRemove(String[] ids);
}
