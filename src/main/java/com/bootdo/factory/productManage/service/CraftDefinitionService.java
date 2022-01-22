package com.bootdo.factory.productManage.service;

import com.bootdo.factory.productManage.domain.CraftDefinitionDO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-03-26 11:53:33
 */
public interface CraftDefinitionService {
	
	CraftDefinitionDO get(String craftId);
	
	List<CraftDefinitionDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CraftDefinitionDO craftDefinition, HttpServletRequest request);
	
	int update(CraftDefinitionDO craftDefinition, HttpServletRequest request);
	
	int remove(String craftId);
	
	int batchRemove(String[] craftIds);
}
