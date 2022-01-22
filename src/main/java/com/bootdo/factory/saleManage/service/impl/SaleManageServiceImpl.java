package com.bootdo.factory.saleManage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bootdo.factory.saleManage.dao.SaleManageDao;
import com.bootdo.factory.saleManage.domain.SaleManageDO;
import com.bootdo.factory.saleManage.service.SaleManageService;



@Service
public class SaleManageServiceImpl implements SaleManageService {
	@Autowired
	private SaleManageDao saleManageDao;
	
	@Override
	public SaleManageDO get(String saleId){
		return saleManageDao.get(saleId);
	}
	
	@Override
	public List<SaleManageDO> list(Map<String, Object> map){
		return saleManageDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return saleManageDao.count(map);
	}
	
	@Override
	public String save(SaleManageDO saleManage){


		String id = UUID.randomUUID().toString();
		saleManage.setSaleId(id);
		saleManageDao.save(saleManage);
		return id;
	}
	
	@Override
	public int update(SaleManageDO saleManage){
		return saleManageDao.update(saleManage);
	}
	
	@Override
	public int remove(String saleId){
		return saleManageDao.remove(saleId);
	}
	
	@Override
	public int batchRemove(String[] saleIds){
		return saleManageDao.batchRemove(saleIds);
	}
	
}
