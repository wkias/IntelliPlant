package com.bootdo.factory.contractManage.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.service.DictService;
import com.bootdo.factory.productManage.domain.ProductDefinitionDO;
import com.bootdo.factory.productManage.domain.ProductDetailDO;
import com.bootdo.factory.productManage.service.ProductDefinitionService;
import com.bootdo.factory.productManage.service.ProductDetailService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.factory.contractManage.domain.OrderContentDO;
import com.bootdo.factory.contractManage.service.OrderContentService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author xgq
 * @email 1992lcg@163.com
 * @date 2020-03-28 16:33:20
 */
 
@Controller
@RequestMapping("/contractManage/orderContent")
public class OrderContentController {
	@Autowired
	private OrderContentService orderContentService;
	@Autowired
	private ProductDetailService productDetailService;
	@Autowired
	private DictService dictService;
	@Autowired
	private ProductDefinitionService productDefinitionService;

	@GetMapping()
	@RequiresPermissions("contractManage:orderContent:orderContent")
	String OrderContent(){
	    return "contractManage/orderContent/orderContent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OrderContentDO> orderContentList = orderContentService.list(query);
		if (orderContentList.size()!=0){
			for (OrderContentDO order:orderContentList
				 ) {
				order.setProductDefinitionDO(productDefinitionService.get(order.getProductId()));

			}

		}
		int total = orderContentService.count(query);
		PageUtils pageUtils = new PageUtils(orderContentList, total);
		return pageUtils;
	}

	@ResponseBody
	@RequestMapping("/listByComplete")
	public PageUtils listByComplete(@RequestParam Map<String, Object> params){
		//查询列表数据
		System.out.println(params.get("orderId")+"     orderid");
		List<OrderContentDO> orderContentList = orderContentService.listByComplete(params);
		if (orderContentList.size()!=0){
			for (OrderContentDO order:orderContentList
			) {
				ProductDefinitionDO productDefinitionDO = productDefinitionService.get(order.getProductId());
//				productDefinitionDO.setProductTypeName(dictService.getName("product_type",productDetailDO.getProductType()));
//				productDefinitionDO.setQuantityUnitName(dictService.getName("quantity_unit",productDetailDO.getQuantityUnit()));
				order.setProductDefinitionDO(productDefinitionDO);

			}

		}
		int total = orderContentService.count(params);
		PageUtils pageUtils = new PageUtils(orderContentList, total);
		return pageUtils;

	}

	@GetMapping("/add")
	String add(){
	    return "contractManage/orderContent/add";
	}

	@GetMapping("/edit/{contentId}")
	String edit(@PathVariable("contentId") String contentId,Model model){
		OrderContentDO orderContent = orderContentService.get(contentId);
		model.addAttribute("orderContent", orderContent);
	    return "contractManage/orderContent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( OrderContentDO orderContent){
		if(orderContentService.save(orderContent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( OrderContentDO orderContent){
		orderContentService.update(orderContent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( String contentId){
		if(orderContentService.remove(contentId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") String[] contentIds){
		orderContentService.batchRemove(contentIds);
		return R.ok();
	}
	
}
