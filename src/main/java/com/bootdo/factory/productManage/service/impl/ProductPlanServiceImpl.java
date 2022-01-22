package com.bootdo.factory.productManage.service.impl;

import com.bootdo.common.config.Constant;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.factory.contractManage.domain.OrderContentDO;
import com.bootdo.factory.contractManage.domain.OrderManageDO;
import com.bootdo.factory.contractManage.service.OrderContentService;
import com.bootdo.factory.contractManage.service.OrderManageService;
import com.bootdo.factory.productManage.domain.ProductDefinitionDO;
import com.bootdo.factory.productManage.service.ProductDefinitionService;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.bootdo.factory.productManage.dao.ProductPlanDao;
import com.bootdo.factory.productManage.domain.ProductPlanDO;
import com.bootdo.factory.productManage.service.ProductPlanService;



@Service
public class ProductPlanServiceImpl implements ProductPlanService {
	@Autowired
	private ProductPlanDao productPlanDao;
	@Autowired
	private OrderContentService orderContentService;
	@Autowired
	private ProductDefinitionService productDefinitionService;
	@Autowired
	private DictService dictService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderManageService orderManageService;
	@Override
	public ProductPlanDO get(String planId){
		return productPlanDao.get(planId);
	}
	
	@Override
	public List<ProductPlanDO> list(Map<String, Object> map){
//		List<OrderContentDO> orderContentDOS=orderContentService.list(map);
//		List<ProductPlanDO> productPlanDOS=new ArrayList<>();
//		Iterator<OrderContentDO> orderContentIterator=orderContentDOS.iterator();
//		while(orderContentIterator.hasNext()){
//			OrderContentDO orderContentDO=orderContentIterator.next();
//			Map<String, Object> planMap=new HashMap<>();
//			planMap.put("orderContentId",orderContentDO.getContentId());
//			List<ProductPlanDO> tempList=productPlanDao.list(planMap);
//			ProductPlanDO productPlan=null;
//			if(!tempList.isEmpty()){//已有生产计划
//				productPlan=tempList.get(0);
//				String dutyPersonId=productPlan.getDutyPersonId();
//				UserDO  user=null;
//				if(StringUtils.isNotBlank(dutyPersonId)){
//					user=userService.get(Long.valueOf(dutyPersonId));
//				}
//				if(user!=null){
//					productPlan.setDutyPersonName(user.getName());
//				}
//			}else{//没有生产计划
//				productPlan=new ProductPlanDO();
//			}
//			OrderManageDO orderManage=orderManageService.get(orderContentDO.getOrderId());
//			if(orderManage!=null){
//				productPlan.setOrderName(orderManage.getOrderName());
//				productPlan.setOrderContentDO(orderContentDO);
//				productPlan.setProductDefinitionDO(productDefinitionService.get(orderContentDO.getProductId()));
//				productPlanDOS.add(productPlan);
//			}else{
//				productPlan.setOrderName("订单已被删除");
//				orderContentIterator.remove();
//			}
//		}
//		//提取查询条件
//		Object planCode=map.get("planCode");
//		Object productName=map.get("productName");
//		Object startDateStr=map.get("startDate");
//		Object endDateStr=map.get("endDate");
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//		Date startDate = null;
//		Date endDate = null;;
//		try {
//			startDate=StringUtils.isBlank((String)startDateStr) ?null:sdf.parse((String)startDateStr);
//			endDate=StringUtils.isBlank((String)endDateStr)?null:sdf.parse((String)endDateStr);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		//根据查询条件过滤
//		Iterator<ProductPlanDO> iterator=productPlanDOS.iterator();
//		while(iterator.hasNext()){
//			ProductPlanDO productPlan=iterator.next();
//			//筛选产品名称
//			if(StringUtils.isNotBlank((String)productName)
//					&&
//					(productPlan.getProductDefinitionDO()==null	//对应产品ID无效
//					||!productPlan.getProductDefinitionDO().getProductName().contains(productName.toString()))){
//				iterator.remove();
//				continue;
//			}
//			//筛选生产计划
//			if(StringUtils.isNotBlank((String)planCode)
//					&&
//					(productPlan.getPlanId()==null		//没有生产计划
//					 ||!productPlan.getPlanCode().contains( planCode.toString() ) ) ){
//				iterator.remove();
//				continue;
//			}
//			//时间范围
//			if(startDate!=null||endDate!=null){
//				if(startDate!=null
//						&&
//						(productPlan.getStartDate()==null	//未设置开始时间
//						||productPlan.getStartDate().getTime()<startDate.getTime())){
//					iterator.remove();
//					continue;
//				}
//				if(endDate!=null
//						&&
//						(productPlan.getEndDate()==null		//未设置结束时间
//						||productPlan.getEndDate().getTime()>endDate.getTime())){
//					iterator.remove();
//					continue;
//				}
//			}
//		}
//		//处理字典值
//		for(ProductPlanDO productPlan:productPlanDOS){
//			String priority=productPlan.getPriority();
//			if(priority!=null){
//				String priorityName=dictService.getName(Constant.PRODUCT_PLAN_PRIORITY,priority);
//				productPlan.setPriority(priorityName);
//			}
//			String planState=productPlan.getPlanState();
//			if(planState!=null){
//				String planStateName=dictService.getName(Constant.PRODUCT_PLAN_STATE,planState);
//				productPlan.setPlanState(planStateName);
//			}
//		}
		List<ProductPlanDO> productPlanDOS=productPlanDao.list(map);
		return productPlanDOS;
	}
	
	@Override
	public int count(Map<String, Object> map){
		return productPlanDao.count(map);
	}
	
	@Override
	public int save(ProductPlanDO productPlan){
		productPlan.setIsDeleted(false);
		productPlan.setCreatUserId(ShiroUtils.getUserId()+"");
		productPlan.setCreatTime(new Date());
		return productPlanDao.save(productPlan);
	}
	
	@Override
	public int update(ProductPlanDO productPlan){
		Map<String,Object> map=new HashMap<>();
		map.put("planId",productPlan.getPlanId());
		if(productPlanDao.count(map)>0){//已有计划，更新
			return productPlanDao.update(productPlan);
		}else{//没有计划，新建
			return productPlanDao.save(productPlan);
		}
	}
	
	@Override
	public int remove(String planId){
		return productPlanDao.remove(planId);
	}
	
	@Override
	public int batchRemove(String[] planIds){
		return productPlanDao.batchRemove(planIds);
	}
	
}
