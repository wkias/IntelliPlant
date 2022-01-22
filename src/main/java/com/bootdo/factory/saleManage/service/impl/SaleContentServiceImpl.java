package com.bootdo.factory.saleManage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.factory.saleManage.dao.SaleContentDao;
import com.bootdo.factory.saleManage.domain.SaleContentDO;
import com.bootdo.factory.saleManage.service.SaleContentService;



@Service
public class SaleContentServiceImpl implements SaleContentService {
	@Autowired
	private SaleContentDao saleContentDao;
	
	@Override
	public SaleContentDO get(String saleContentId){
		return saleContentDao.get(saleContentId);
	}
	
	@Override
	public List<SaleContentDO> list(Map<String, Object> map){
		return saleContentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return saleContentDao.count(map);
	}
	
	@Override
	public int save(SaleContentDO saleContent){
		return saleContentDao.save(saleContent);
	}
	
	@Override
	public int update(SaleContentDO saleContent){
		return saleContentDao.update(saleContent);
	}
	
	@Override
	public int remove(String saleContentId){
		return saleContentDao.remove(saleContentId);
	}
	
	@Override
	public int batchRemove(String[] saleContentIds){
		return saleContentDao.batchRemove(saleContentIds);
	}

	@Override
	public List<SaleContentDO> getBySaleId(String saleId) {
		return saleContentDao.getBySaleId(saleId);
	}

}
