package com.bootdo.factory.productManage.service;

import com.bootdo.factory.productManage.domain.MaterialGetDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-04-27 14:06:39
 */
public interface MaterialGetService {
	
	MaterialGetDO get(String id);
	
	List<MaterialGetDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MaterialGetDO materialGet);
	
	int update(MaterialGetDO materialGet);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
