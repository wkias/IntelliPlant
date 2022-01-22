package com.bootdo.factory.contractManage.dao;

import com.bootdo.factory.contractManage.domain.OrderManageDO;

import java.util.List;
import java.util.Map;

import com.bootdo.factory.contractManage.vo.OrderManageVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author xgq
 * @email 1992lcg@163.com
 * @date 2020-03-11 16:26:25
 */
@Mapper
public interface OrderManageDao {

	OrderManageDO get(String orderId);
	
	List<OrderManageDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(OrderManageDO orderManage);
	
	int update(OrderManageDO orderManage);
	
	int remove(String order_id);
	
	int batchRemove(String[] orderIds);

    OrderManageVO getVO(String id);

    List<OrderManageVO> listVO(Map<String, Object> map);

    String getMaxPeriodById(String contractId);
}
