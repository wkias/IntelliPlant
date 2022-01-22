package com.bootdo.factory.productManage.service.impl;

import com.bootdo.factory.productManage.domain.ProductDetailDO;
import com.bootdo.factory.productManage.service.ProductDetailService;
import com.bootdo.factory.warehouseManagement.controller.OutboundOrderController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.factory.productManage.dao.MaterialGetDao;
import com.bootdo.factory.productManage.domain.MaterialGetDO;
import com.bootdo.factory.productManage.service.MaterialGetService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MaterialGetServiceImpl implements MaterialGetService {
	@Autowired
	private MaterialGetDao materialGetDao;
	@Autowired
	private ProductDetailService productDetailService;
	@Autowired
	private OutboundOrderController outboundOrderController;

	@Override
	public MaterialGetDO get(String id){
		return materialGetDao.get(id);
	}
	
	@Override
	public List<MaterialGetDO> list(Map<String, Object> map){
		List<MaterialGetDO> materialGetDOS= materialGetDao.list(map);
		for(MaterialGetDO materialGet:materialGetDOS){
			String productId=materialGet.getMateralId();
			ProductDetailDO productDetail=productDetailService.get(productId);
			materialGet.setMateralName(productDetail.getProductName());
			materialGet.setMateralModel(productDetail.getModel());
		}
		return materialGetDOS;
	}
	
	@Override
	public int count(Map<String, Object> map){
		return materialGetDao.count(map);
	}
	
	@Override
	public int save(MaterialGetDO materialGet){
		return materialGetDao.save(materialGet);
	}
	
	@Override
	public int update(MaterialGetDO materialGet){
		return materialGetDao.update(materialGet);
	}
	
	@Override
	public int remove(String id){
		return materialGetDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return materialGetDao.batchRemove(ids);
	}
	
}
