package com.bootdo.factory.warehouseManagement.service;

import com.bootdo.factory.warehouseManagement.domain.StockCheckDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author zrj
 * @email 1992lcg@163.com
 * @date 2020-03-22 18:08:32
 */
public interface StockCheckService {
	
	StockCheckDO get(String stockCheckId);

	List<StockCheckDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(StockCheckDO stockCheck);

	int update(StockCheckDO stockCheck);

	int remove(String stockCheckId);

	int removeByProduct(StockCheckDO stockCheck);

	int batchRemove(String[] stockCheckIds);
}
