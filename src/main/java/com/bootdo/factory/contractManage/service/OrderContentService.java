package com.bootdo.factory.contractManage.service;

import com.bootdo.common.utils.Query;
import com.bootdo.factory.contractManage.domain.OrderContentDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xgq
 * @email 1992lcg@163.com
 * @date 2020-03-28 16:33:20
 */
public interface OrderContentService {
	
	OrderContentDO get(String contentId);
	
	List<OrderContentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrderContentDO orderContent);
	
	int update(OrderContentDO orderContent);
	
	int remove(String contentId);
	
	int batchRemove(String[] contentIds);


	List<OrderContentDO> listByComplete(Map<String, Object> params);
}
