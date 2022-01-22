package com.bootdo.factory.productManage.dao;

import com.bootdo.factory.productManage.domain.ProcessRelationshipDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author zrj
 * @email 1992lcg@163.com
 * @date 2020-03-27 10:52:40
 */
@Mapper
public interface ProcessRelationshipDao {

	ProcessRelationshipDO get(String id);
	
	List<ProcessRelationshipDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ProcessRelationshipDO processRelationship);
	
	int update(ProcessRelationshipDO processRelationship);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	int removeByProcessInspectionId(String processInspectionId);
}
