package com.bootdo.factory.warehouseManagement.dao;

import com.bootdo.factory.warehouseManagement.domain.StockCheckDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author zrj
 * @email 1992lcg@163.com
 * @date 2020-03-22 18:08:32
 */
@Mapper
public interface StockCheckDao {

	StockCheckDO get(String stockCheckId);

	List<StockCheckDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(StockCheckDO stockCheck);

	int update(StockCheckDO stockCheck);

	int remove(String stock_check_id);

	int removeByProduct(String productId);

	int batchRemove(String[] stockCheckIds);
}
