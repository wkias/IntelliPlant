package com.bootdo.factory.productManage.dao;

import com.bootdo.factory.productManage.domain.ProcessInspectionDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author zrj
 * @email 1992lcg@163.com
 * @date 2020-03-26 21:55:29
 */
@Mapper
public interface ProcessInspectionDao {

	ProcessInspectionDO get(String processInspectionId);
	
	List<ProcessInspectionDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ProcessInspectionDO processInspection);
	
	int update(ProcessInspectionDO processInspection);
	
	int remove(String process_inspection_id);
	
	int batchRemove(String[] processInspectionIds);
}
