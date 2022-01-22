package com.bootdo.factory.productManage.controller;

import java.util.HashMap;
import java.util.Random;
import java.util.List;
import java.util.Map;

import com.bootdo.factory.productManage.dao.CraftProcessDao;
import com.bootdo.factory.productManage.domain.CraftProcessDO;
import com.bootdo.factory.domain.ProcessDefinitionDO;
import com.bootdo.factory.service.ProcessDefinitionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.factory.productManage.domain.CraftDefinitionDO;
import com.bootdo.factory.productManage.service.CraftDefinitionService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-03-26 11:53:33
 */
 
@Controller
@RequestMapping("/factory/craftDefinition")
public class CraftDefinitionController {
	@Autowired
	private CraftDefinitionService craftDefinitionService;
	@Autowired
	private CraftProcessDao craftProcessDao;
	@Autowired
	private ProcessDefinitionService processDefinitionService;
	@GetMapping()
	@RequiresPermissions("factory:craftDefinition:craftDefinition")
	String CraftDefinition(){
	    return "factory/craftDefinition/craftDefinition";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("factory:craftDefinition:craftDefinition")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CraftDefinitionDO> craftDefinitionList = craftDefinitionService.list(query);
		int total = craftDefinitionService.count(query);
		PageUtils pageUtils = new PageUtils(craftDefinitionList, total);
		return pageUtils;
	}
	@ResponseBody
	@GetMapping("/processList/{craftId}")
	@RequiresPermissions("factory:craftDefinition:craftDefinition")
	public List<CraftProcessDO> processList(@PathVariable("craftId") String craftId){
		//查询列表数据
		Map<String,Object> map=new HashMap<>();
		map.put("craftId",craftId);
		List<CraftProcessDO> craftProcessDOS=craftProcessDao.list(map);
		for(CraftProcessDO craftProcess:craftProcessDOS){
			ProcessDefinitionDO processDefinition=processDefinitionService.get(craftProcess.getProcessId());
			craftProcess.setProcessDefinition(processDefinition);
		}
		return craftProcessDOS;
	}
	@GetMapping("/add")
	@RequiresPermissions("factory:craftDefinition:add")
	String add(){
	    return "factory/craftDefinition/add";
	}

	@GetMapping("/edit/{craftId}")
	@RequiresPermissions("factory:craftDefinition:edit")
	String edit(@PathVariable("craftId") String craftId,Model model){
		CraftDefinitionDO craftDefinition = craftDefinitionService.get(craftId);
		model.addAttribute("craftDefinition", craftDefinition);
		Map<String,Object> map=new HashMap<>();
		map.put("craftId",craftId);
		List<CraftProcessDO> craftProcessDOS=craftProcessDao.list(map);
		for(CraftProcessDO craftProcess:craftProcessDOS){
			ProcessDefinitionDO processDefinition=processDefinitionService.get(craftProcess.getProcessId());
			craftProcess.setProcessDefinition(processDefinition);
		}
		model.addAttribute("craftProcessDOS",craftProcessDOS);
	    return "factory/craftDefinition/edit";
	}
	@GetMapping("/details/{craftId}")
	@RequiresPermissions("factory:craftDefinition:edit")
	String details(@PathVariable("craftId") String craftId,Model model){
		CraftDefinitionDO craftDefinition = craftDefinitionService.get(craftId);
		model.addAttribute("craftDefinition", craftDefinition);
		Map<String,Object> map=new HashMap<>();
		map.put("craftId",craftId);
		List<CraftProcessDO> craftProcessDOS=craftProcessDao.list(map);
		for(CraftProcessDO craftProcess:craftProcessDOS){
			ProcessDefinitionDO processDefinition=processDefinitionService.get(craftProcess.getProcessId());
			craftProcess.setProcessDefinition(processDefinition);
		}
		model.addAttribute("craftProcessDOS",craftProcessDOS);
		return "factory/craftDefinition/details";
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("factory:craftDefinition:add")
	public R save(CraftDefinitionDO craftDefinition, HttpServletRequest request){
		if(craftDefinitionService.save(craftDefinition,request)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("factory:craftDefinition:edit")
	public R update( CraftDefinitionDO craftDefinition, HttpServletRequest request){
		craftDefinitionService.update(craftDefinition,request);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("factory:craftDefinition:remove")
	public R remove( String craftId){
		if(craftDefinitionService.remove(craftId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("factory:craftDefinition:batchRemove")
	public R remove(@RequestParam("ids[]") String[] craftIds){
		craftDefinitionService.batchRemove(craftIds);
		return R.ok();
	}
	@GetMapping("/processSelect")
	@RequiresPermissions("factory:craftDefinition:craftDefinition")
	String processSelect(Model model){
		return "factory/craftDefinition/processSelect";
	}
}
