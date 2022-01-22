package com.bootdo.factory.productManage.service.impl;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.factory.productManage.domain.CraftDefinitionDO;
import com.bootdo.factory.productManage.domain.ProductDefinitionDO;
import com.bootdo.factory.productManage.service.CraftDefinitionService;
import com.bootdo.factory.productManage.service.ProductDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.bootdo.factory.productManage.dao.ProductCraftDao;
import com.bootdo.factory.productManage.domain.ProductCraftDO;
import com.bootdo.factory.productManage.service.ProductCraftService;



@Service
public class ProductCraftServiceImpl implements ProductCraftService {
	@Autowired
	private ProductCraftDao productCraftDao;
	@Autowired
	private ProductDefinitionService productDefinitionService;
	@Autowired
	private CraftDefinitionService craftDefinitionService;
	@Override
	public ProductCraftDO get(String productCraftId){
		return productCraftDao.get(productCraftId);
	}
	
	@Override
	public List<ProductCraftDO> list(Map<String, Object> map){
		if(map.get("productId")==null||StringUtils.isBlank(map.get("productId").toString())){
			//首先获取所有产品
			List<ProductDefinitionDO> products=productDefinitionService.list(map);
			List<ProductCraftDO> productCrafts=new ArrayList<>();
			for(ProductDefinitionDO product:products){

				map.put("productId",product.getProductId());
				List<ProductCraftDO>  temp=productCraftDao.list(map);
				if(!temp.isEmpty()&&StringUtils.isNotBlank(temp.get(0).getCraftId())){
					//该产品有工艺绑定
					ProductCraftDO productCraft=temp.get(0);
					Map<String ,Object> craftMap=new HashMap<>();
					craftMap.put("craftId",productCraft.getCraftId());
					List<CraftDefinitionDO> crafts=craftDefinitionService.list(craftMap);
					if(!crafts.isEmpty()){
						productCraft.setCraft(crafts.get(0));
					}
					productCraft.setProduct(product);
					productCrafts.add(productCraft);
				}else{
					//改产品暂无工艺绑定
					ProductCraftDO productCraft=new ProductCraftDO();
					productCraft.setProduct(product);
					productCraft.setProductId(product.getProductId());
					productCrafts.add(productCraft);
				}
			}
			return productCrafts;
		}
		return productCraftDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return productCraftDao.count(map);
	}
	
	@Override
	public int save(ProductCraftDO productCraft){
		productCraft.setProductCraftId(UUID.randomUUID().toString());
		productCraft.setIsDeleted(false);
		productCraft.setCreateUserId(ShiroUtils.getUserId()+"");
		productCraft.setCreateTime(new Date());
		return productCraftDao.save(productCraft);
	}
	
	@Override
	public int update(ProductCraftDO productCraft){
		return productCraftDao.update(productCraft);
	}
	
	@Override
	public int remove(String productCraftId){
		return productCraftDao.remove(productCraftId);
	}
	
	@Override
	public int batchRemove(String[] productCraftIds){
		return productCraftDao.batchRemove(productCraftIds);
	}
	
}
