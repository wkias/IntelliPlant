package com.bootdo.factory.productManage.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.factory.productManage.domain.CraftDefinitionDO;
import com.bootdo.factory.productManage.domain.ProductDefinitionDO;
import com.bootdo.factory.productManage.service.CraftDefinitionService;
import com.bootdo.factory.productManage.service.ProductDefinitionService;
import org.apache.fop.area.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bootdo.factory.productManage.domain.ProductCraftDO;
import com.bootdo.factory.productManage.service.ProductCraftService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-03-26 11:53:33
 */
 
@Controller
@RequestMapping("/factory/productCraft")
public class ProductCraftController {
	@Autowired
	private ProductCraftService productCraftService;
	@Autowired
	private ProductDefinitionService productDefinitionService;
	@Autowired
	private CraftDefinitionService craftDefinitionService;
	@GetMapping()
	@RequiresPermissions("factory:productCraft:productCraft")
	String ProductCraft(){
	    return "factory/productCraft/productCraft";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("factory:productCraft:productCraft")
	public PageUtils list(@RequestParam Map<String, Object> params){
		Query query=new Query(params);
		//查询列表数据
		List<ProductCraftDO> productCrafts = productCraftService.list(query);
		int total=productCraftService.count(query);
		PageUtils pageUtils=new PageUtils(productCrafts,total);
		return pageUtils;
	}

	@ResponseBody
	@GetMapping("/productList")
	@RequiresPermissions("factory:productCraft:productCraft")
	public List<ProductDefinitionDO>  productList(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<ProductDefinitionDO> products = productDefinitionService.list(params);
		return products;
	}
	@ResponseBody
	@GetMapping("/craftList")
	@RequiresPermissions("factory:craftDefinition:craftDefinition")
	public List<CraftDefinitionDO> ajaxList(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<CraftDefinitionDO> craftDefinitionList = craftDefinitionService.list(params);
		return craftDefinitionList;
	}
	@GetMapping("/add")
	@RequiresPermissions("factory:productCraft:add")
	String add(){
	    return "factory/productCraft/add";
	}

	@GetMapping("/edit/{productCraftId}")
	@RequiresPermissions("factory:productCraft:edit")
	String edit(@PathVariable("productCraftId") String productCraftId,Model model){
		ProductCraftDO productCraft = productCraftService.get(productCraftId);
		model.addAttribute("productCraft", productCraft);
	    return "factory/productCraft/edit";
	}
	@GetMapping("/detail/{productCraftId}")
	String detail(@PathVariable("productCraftId") String productCraftId,Model model){
		ProductCraftDO productCraft = productCraftService.get(productCraftId);
		model.addAttribute("productCraft", productCraft);
		return "factory/productCraft/detail";
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("factory:productCraft:add")
	public R save( ProductCraftDO productCraft){
		if(productCraftService.save(productCraft)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("factory:productCraft:edit")
	public R update( ProductCraftDO productCraft){
		productCraftService.update(productCraft);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("factory:productCraft:remove")
	public R remove( String productCraftId){
		if(productCraftService.remove(productCraftId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("factory:productCraft:batchRemove")
	public R remove(@RequestParam("ids[]") String[] productCraftIds){
		productCraftService.batchRemove(productCraftIds);
		return R.ok();
	}
	
}
