package com.bootdo.factory.contractManage.service.impl;

import com.bootdo.factory.contractManage.domain.OrderContentDO;
import com.bootdo.factory.contractManage.service.OrderContentService;
import com.bootdo.factory.contractManage.vo.OrderManageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bootdo.factory.contractManage.dao.OrderManageDao;
import com.bootdo.factory.contractManage.domain.OrderManageDO;
import com.bootdo.factory.contractManage.service.OrderManageService;



@Service
public class OrderManageServiceImpl implements OrderManageService {
	@Autowired
	private OrderManageDao orderManageDao;

	@Autowired
	private OrderContentService orderContentService;

	@Override
	public OrderManageDO get(String orderId){
		return orderManageDao.get(orderId);
	}
	
	@Override
	public List<OrderManageDO> list(Map<String, Object> map){
		return orderManageDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return orderManageDao.count(map);
	}
	
	@Override
	public String save(OrderManageDO orderManage){
		String id = UUID.randomUUID().toString();
		orderManage.setOrderId(id);
		orderManageDao.save(orderManage);
		return id;
	}
	
	@Override
	public int update(OrderManageVO orderManage){
		orderManageDao.update(orderManage);
		Map map = new HashMap();
		map.put("orderId",orderManage.getOrderId());
		List<OrderContentDO> list = orderContentService.list(map);
		if (list.size()!=0){
			for(int i = 0;i<list.size();i++){
				orderContentService.remove(list.get(i).getContentId());
			}
		}
		List<OrderContentDO> clist = orderManage.getNoBatchOrderContentS();
		if (clist.size()!=0){
			for (OrderContentDO oc:clist) {
				oc.setContentId(UUID.randomUUID().toString());
				oc.setOrderId(orderManage.getOrderId());
				oc.setIsBatched("0");
				oc.setComplete(0);
				orderContentService.save(oc);
			}
		}



		return 0;

	}
	
	@Override
	public int remove(String orderId){
		return orderManageDao.remove(orderId);
	}
	
	@Override
	public int batchRemove(String[] orderIds){
		return orderManageDao.batchRemove(orderIds);
	}

	@Override
	public OrderManageVO getVO(String id) {
		return orderManageDao.getVO(id);
	}

	@Override
	public List<OrderManageVO> listVO(Map<String, Object> map) {
		return orderManageDao.listVO(map);
	}

	@Override
	public String getMaxPeriodById(String contractId) {
		return orderManageDao.getMaxPeriodById(contractId);
	}

}
