package com.bootdo.factory.productManage.controller;

import java.util.List;
import java.util.Map;

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

import com.bootdo.factory.productManage.domain.MaterialGetDO;
import com.bootdo.factory.productManage.service.MaterialGetService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-04-27 14:06:39
 */
 
@Controller
@RequestMapping("/productManage/materialGet")
public class MaterialGetController {
	@Autowired
	private MaterialGetService materialGetService;
	
	@GetMapping()
	@RequiresPermissions("productManage:materialGet:materialGet")
	String MaterialGet(){
	    return "productManage/materialGet/materialGet";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("productManage:materialGet:materialGet")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<MaterialGetDO> materialGetList = materialGetService.list(query);
		int total = materialGetService.count(query);
		PageUtils pageUtils = new PageUtils(materialGetList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("productManage:materialGet:add")
	String add(){
	    return "productManage/materialGet/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("productManage:materialGet:edit")
	String edit(@PathVariable("id") String id,Model model){
		MaterialGetDO materialGet = materialGetService.get(id);
		model.addAttribute("materialGet", materialGet);
	    return "productManage/materialGet/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("productManage:materialGet:add")
	public R save( MaterialGetDO materialGet){
		if(materialGetService.save(materialGet)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("productManage:materialGet:edit")
	public R update( MaterialGetDO materialGet){
		materialGetService.update(materialGet);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("productManage:materialGet:remove")
	public R remove( String id){
		if(materialGetService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("productManage:materialGet:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		materialGetService.batchRemove(ids);
		return R.ok();
	}
	
}
