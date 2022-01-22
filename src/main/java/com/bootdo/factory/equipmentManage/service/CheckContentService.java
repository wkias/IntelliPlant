package com.bootdo.factory.equipmentManage.service;

import com.bootdo.common.domain.DictDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.factory.equipmentManage.domain.CheckContentDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xgq
 * @email 1992lcg@163.com
 * @date 2020-02-25 14:32:31
 */
public interface CheckContentService {
	
	CheckContentDO get(int checkId);
	
	List<CheckContentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CheckContentDO checkContent);
	
	int update(CheckContentDO checkContent);
	
	int remove(int checkId);
	
	int batchRemove(int[] checkIds);

	Tree<DictDO> getTree();
}
