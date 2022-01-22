package com.bootdo.factory.productManage.dao;

import com.bootdo.factory.productManage.domain.CraftDefinitionDO;

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
public interface CraftDefinitionDao {

	CraftDefinitionDO get(String craftId);
	
	List<CraftDefinitionDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(CraftDefinitionDO craftDefinition);
	
	int update(CraftDefinitionDO craftDefinition);
	
	int remove(String craft_id);
	
	int batchRemove(String[] craftIds);
}
