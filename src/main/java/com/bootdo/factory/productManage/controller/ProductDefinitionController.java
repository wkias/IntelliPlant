package com.bootdo.factory.productManage.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.common.config.Constant;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.ShiroUtils;
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

import com.bootdo.factory.productManage.domain.ProductDefinitionDO;
import com.bootdo.factory.productManage.service.ProductDefinitionService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author zrj
 * @email 1992lcg@163.com
 * @date 2020-03-24 11:12:52
 */
 
@Controller
@RequestMapping("/productManage/productDefinition")
public class ProductDefinitionController {
	@Autowired
	private ProductDefinitionService productDefinitionService;
	@Autowired
	private DictService dictService;
	
	@GetMapping()
	@RequiresPermissions("productManage:productDefinition:productDefinition")
	String ProductDefinition(){
	    return "factory/productManage/productDefinition/productDefinition";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("productManage:productDefinition:productDefinition")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ProductDefinitionDO> productDefinitionList = productDefinitionService.list(query);
		if(productDefinitionList.size()!=0){
			for (ProductDefinitionDO product:productDefinitionList) {
				product.setWeightUnitName(dictService.getName("weight_unit",product.getWeightUnit()));
				product.setUnitName(dictService.getName("unit",product.getUnit()));
			}
		}
		int total = productDefinitionService.count(query);
		PageUtils pageUtils = new PageUtils(productDefinitionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("productManage:productDefinition:add")
	String add(){
	    return "factory/productManage/productDefinition/add";
	}

	@GetMapping("/edit/{productId}")
	@RequiresPermissions("productManage:productDefinition:edit")
	String edit(@PathVariable("productId") String productId,Model model){
		ProductDefinitionDO productDefinition = productDefinitionService.get(productId);
		model.addAttribute("productDefinition", productDefinition);
	    return "factory/productManage/productDefinition/edit";
	}

    /**
     * 详情
     */
    @GetMapping("/details/{productId}")
    @RequiresPermissions("productManage:productDefinition:details")
    public String details(@PathVariable("productId") String productId, Model model) {
        ProductDefinitionDO productDefinition = productDefinitionService.get(productId);
		productDefinition.setWeightUnitName(dictService.getName("weight_unit",productDefinition.getWeightUnit()));
		productDefinition.setUnitName(dictService.getName("unit",productDefinition.getUnit()));
        model.addAttribute("productDefinition", productDefinition);
        return "factory/productManage/productDefinition/details";
    }


	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("productManage:productDefinition:add")
	public R save( ProductDefinitionDO productDefinition){
		Long userId = ShiroUtils.getUserId();
		productDefinition.setCreateUserId(userId + "");
		productDefinition.setCreateDate(new Date().getTime());
		if(productDefinitionService.save(productDefinition)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("productManage:productDefinition:edit")
	public R update( ProductDefinitionDO productDefinition){
		productDefinitionService.update(productDefinition);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("productManage:productDefinition:remove")
	public R remove( String productId){
		if(productDefinitionService.remove(productId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("productManage:productDefinition:batchRemove")
	public R remove(@RequestParam("ids[]") String[] productIds){
		productDefinitionService.batchRemove(productIds);
		return R.ok();
	}

	@PostMapping("/changeState")
	@ResponseBody
	public R changeState(String productId, String cmd) {

		String label = "停用";
		if ("start".equals(cmd)) {
			label = "启用";
		} else {
			label = "停用";
		}
		try {
            productDefinitionService.changeState(productId, cmd);
			return R.ok("产品" + label + "成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return R.ok("产品" + label + "失败");
	}

}

