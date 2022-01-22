package com.bootdo.factory.contractManage.service;

import com.bootdo.common.utils.Query;
import com.bootdo.factory.contractManage.domain.OrderManageDO;
import com.bootdo.factory.contractManage.vo.OrderManageVO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xgq
 * @email 1992lcg@163.com
 * @date 2020-03-11 16:26:25
 */
public interface OrderManageService {
	
	OrderManageDO get(String orderId);
	
	List<OrderManageDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	String save(OrderManageDO orderManage);
	
	int update(OrderManageVO orderManage);
	
	int remove(String orderId);
	
	int batchRemove(String[] orderIds);

	OrderManageVO getVO(String id);

	List<OrderManageVO> listVO(Map<String, Object> map);

	String getMaxPeriodById(String contractId);
}
