package com.bootdo.factory.productManage.service;

import com.bootdo.factory.productManage.domain.ProcessInspectionDO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author zrj
 * @email 1992lcg@163.com
 * @date 2020-03-26 21:55:29
 */
public interface ProcessInspectionService {
	
	ProcessInspectionDO get(String processInspectionId);
	
	List<ProcessInspectionDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ProcessInspectionDO processInspection, HttpServletRequest request);
	
	int update(ProcessInspectionDO processInspection, HttpServletRequest request);
	
	int remove(String processInspectionId);
	
	int batchRemove(String[] processInspectionIds);
}
