package com.bootdo.factory.contractManage.service.impl;

import com.bootdo.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.factory.contractManage.dao.OrderContentDao;
import com.bootdo.factory.contractManage.domain.OrderContentDO;
import com.bootdo.factory.contractManage.service.OrderContentService;



@Service
public class OrderContentServiceImpl implements OrderContentService {
	@Autowired
	private OrderContentDao orderContentDao;
	
	@Override
	public OrderContentDO get(String contentId){
		return orderContentDao.get(contentId);
	}
	
	@Override
	public List<OrderContentDO> list(Map<String, Object> map){
		return orderContentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return orderContentDao.count(map);
	}
	
	@Override
	public int save(OrderContentDO orderContent){
		return orderContentDao.save(orderContent);
	}
	
	@Override
	public int update(OrderContentDO orderContent){
		return orderContentDao.update(orderContent);
	}
	
	@Override
	public int remove(String contentId){
		return orderContentDao.remove(contentId);
	}
	
	@Override
	public int batchRemove(String[] contentIds){
		return orderContentDao.batchRemove(contentIds);
	}

	@Override
	public List<OrderContentDO> listByComplete(Map<String, Object> params) {
		return orderContentDao.listByComplete(params);
	}



}
