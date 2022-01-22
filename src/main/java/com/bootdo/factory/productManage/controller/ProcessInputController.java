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

import com.bootdo.factory.productManage.domain.ProcessInputDO;
import com.bootdo.factory.productManage.service.ProcessInputService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 工序投入
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-04-26 18:30:11
 */
 
@Controller
@RequestMapping("/productManage/processInput")
public class ProcessInputController {
	@Autowired
	private ProcessInputService processInputService;
	
	@GetMapping()
	@RequiresPermissions("productManage:processInput:processInput")
	String ProcessInput(){
	    return "productManage/processInput/processInput";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("productManage:processInput:processInput")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ProcessInputDO> processInputList = processInputService.list(query);
		int total = processInputService.count(query);
		PageUtils pageUtils = new PageUtils(processInputList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("productManage:processInput:add")
	String add(){
	    return "productManage/processInput/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("productManage:processInput:edit")
	String edit(@PathVariable("id") String id,Model model){
		ProcessInputDO processInput = processInputService.get(id);
		model.addAttribute("processInput", processInput);
	    return "productManage/processInput/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("productManage:processInput:add")
	public R save( ProcessInputDO processInput){
		if(processInputService.save(processInput)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("productManage:processInput:edit")
	public R update( ProcessInputDO processInput){
		processInputService.update(processInput);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("productManage:processInput:remove")
	public R remove( String id){
		if(processInputService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("productManage:processInput:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		processInputService.batchRemove(ids);
		return R.ok();
	}
	
}
