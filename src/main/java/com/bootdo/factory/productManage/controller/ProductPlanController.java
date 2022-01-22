package com.bootdo.factory.productManage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bootdo.common.config.Constant;
import com.bootdo.common.service.DictService;
import com.bootdo.factory.contractManage.service.OrderContentService;
import com.bootdo.factory.productManage.service.ProductDefinitionService;
import com.bootdo.factory.productManage.service.ProductDetailService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bootdo.factory.productManage.domain.ProductPlanDO;
import com.bootdo.factory.productManage.service.ProductPlanService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-04-07 20:22:08
 */
 
@Controller
@RequestMapping("/factory/productPlan")
public class ProductPlanController {
	@Autowired
	private ProductPlanService productPlanService;
	@Autowired
	private DictService dictService;
	@Autowired
	private OrderContentService orderContentService;
	@GetMapping()
	@RequiresPermissions("factory:productPlan:productPlan")
	String ProductPlan(){
	    return "factory/productPlan/productPlan";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("factory:productPlan:productPlan")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ProductPlanDO> productPlanList = productPlanService.list(query);
		//int total = productPlanService.count(query);
		int total=orderContentService.count(new HashMap<>());
		PageUtils pageUtils = new PageUtils(productPlanList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("factory:productPlan:add")
	String add(){
	    return "factory/productPlan/add";
	}

	@GetMapping("/edit/{orderContentId}")
	@RequiresPermissions("factory:productPlan:edit")
	String edit(@PathVariable("orderContentId") String orderContentId,Model model){
		Map<String,Object> map =new HashMap<>();
		map.put("contentId",orderContentId);
		List<ProductPlanDO> tempList = productPlanService.list(map);
		ProductPlanDO productPlan=null;
		productPlan=tempList.get(0);
		if(productPlan.getPlanId()==null){
			//在这里初始化生产计划的ID和编号
			productPlan.setPlanId(UUID.randomUUID().toString());
			productPlan.setPlanCode(UUID.randomUUID().toString());
			productPlan.setOrderContentId(orderContentId);
		}
		model.addAttribute("productPlan", productPlan);
		return "factory/productPlan/edit";
	}
	@GetMapping("/detail/{orderContentId}")
	String detail(@PathVariable("orderContentId") String orderContentId,Model model){
		Map<String,Object> map =new HashMap<>();
		map.put("contentId",orderContentId);
		List<ProductPlanDO> tempList = productPlanService.list(map);
		ProductPlanDO productPlan=null;
		productPlan=tempList.get(0);
		model.addAttribute("productPlan", productPlan);
		return "factory/productPlan/detail";
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("factory:productPlan:add")
	public R save( ProductPlanDO productPlan){
		if(productPlanService.save(productPlan)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("factory:productPlan:edit")
	public R update( ProductPlanDO productPlan){
		productPlanService.update(productPlan);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("factory:productPlan:remove")
	public R remove( String planId){
		if(productPlanService.remove(planId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("factory:productPlan:batchRemove")
	public R remove(@RequestParam("ids[]") String[] planIds){
		productPlanService.batchRemove(planIds);
		return R.ok();
	}
	/**
	 * 批量完成
	 */
	@PostMapping( "/complete")
	@ResponseBody
	@RequiresPermissions("factory:productPlan:complete")
	public R complete(@RequestBody ProductPlanDO[] productPlanDOS){
		for(ProductPlanDO productPlanDO:productPlanDOS){
			productPlanService.update(productPlanDO);
		}
		return R.ok();
	}
	/**
	 * 生产计划图表
	 *
	 * */
	@GetMapping("/planChart")
	@ResponseBody
	Map getPlanChart(){
		Map<String,Map<String,Integer>> chartMap=new HashMap<>();
		List<ProductPlanDO> planDOS=productPlanService.list(new HashMap<>());
		for(ProductPlanDO plan:planDOS){
			Map<String,Integer> countMap=new HashMap<>();
			String product=plan.getProductDefinitionDO().getProductName();
			int complete=0;
			int demand=0;
			if(chartMap.get(product)!=null){
				complete=chartMap.get(product).get("complete")==null?0:chartMap.get(product).get("complete");
				demand=chartMap.get(product).get("demand")==null?0:chartMap.get(product).get("demand");
			}
			countMap.put("complete",complete+(plan.getCompletedQuantity()==null?0:plan.getCompletedQuantity()));
			countMap.put("demand",demand+(plan.getOrderContentDO().getCount()==null?0:plan.getOrderContentDO().getCount()));
			chartMap.put(product,countMap);
		}
		return chartMap;
	}
}
