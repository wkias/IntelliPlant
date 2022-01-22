package com.bootdo.factory.contractManage.dao;

import com.bootdo.common.utils.Query;
import com.bootdo.factory.contractManage.domain.OrderContentDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author xgq
 * @email 1992lcg@163.com
 * @date 2020-03-28 16:33:20
 */
@Mapper
public interface OrderContentDao {

	OrderContentDO get(String contentId);
	
	List<OrderContentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrderContentDO orderContent);
	
	int update(OrderContentDO orderContent);
	
	int remove(String content_id);
	
	int batchRemove(String[] contentIds);

	List<OrderContentDO> listByComplete(Map<String, Object> params);
}
