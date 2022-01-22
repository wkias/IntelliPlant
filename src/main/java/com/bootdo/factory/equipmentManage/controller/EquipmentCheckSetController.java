package com.bootdo.factory.equipmentManage.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.ExportUtils;
import com.bootdo.factory.equipmentManage.domain.EquipmentManageDO;
import com.bootdo.factory.equipmentManage.vo.EquipmentCheckSetVO;
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

import com.bootdo.factory.equipmentManage.domain.EquipmentCheckSetDO;
import com.bootdo.factory.equipmentManage.service.EquipmentCheckSetService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-02-25 13:46:27
 */
 
@Controller
@RequestMapping("/factory/equipmentCheckSet")
public class EquipmentCheckSetController {
	@Autowired
	private EquipmentCheckSetService equipmentCheckSetService;
	
	@GetMapping()
	@RequiresPermissions("factory:equipmentCheckSet:equipmentCheckSet")
	String EquipmentCheckSet(){
	    return "factory/equipmentCheckSet/equipmentCheckSet";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("factory:equipmentCheckSet:queryAll")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<EquipmentCheckSetVO> equipmentCheckSetList = equipmentCheckSetService.list(query);
		System.out.println("checkSetList:");
		for(EquipmentCheckSetVO checkSet:equipmentCheckSetList){
			System.out.println("checkSet:"+checkSet);
		}
		int total = equipmentCheckSetService.count(query);
		System.out.println("total:"+total);
		PageUtils pageUtils = new PageUtils(equipmentCheckSetList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("factory:equipmentCheckSet:add")
	String add(){
	    return "factory/equipmentCheckSet/add";
	}

	@GetMapping("/edit/{checkSetId}")
	@RequiresPermissions("factory:equipmentCheckSet:edit")
	String edit(@PathVariable("checkSetId") String checkSetId,Model model){
		EquipmentCheckSetVO equipmentCheckSet = equipmentCheckSetService.getVO(checkSetId);
		model.addAttribute("equipmentCheckSet", equipmentCheckSet);
	    return "factory/equipmentCheckSet/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("factory:equipmentCheckSet:add")
	public R save( EquipmentCheckSetDO equipmentCheckSet){
		if(equipmentCheckSetService.save(equipmentCheckSet)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("factory:equipmentCheckSet:edit")
	public R update( EquipmentCheckSetDO equipmentCheckSet){
		equipmentCheckSetService.update(equipmentCheckSet);
		return R.ok();
	}

	/**
	 * 设置点检人
	 */
	@ResponseBody
	@RequestMapping("/setChecker")
	@RequiresPermissions("factory:equipmentCheckSet:setChecker")
	public R setChecker(@RequestParam("ids[]") String[] checkSetIds,@RequestParam("checkerId")String checkerId){
		equipmentCheckSetService.setChecker(checkSetIds,checkerId);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("factory:equipmentCheckSet:remove")
	public R remove( String checkSetId){
		if(equipmentCheckSetService.remove(checkSetId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("factory:equipmentCheckSet:batchRemove")
	public R remove(@RequestParam("ids[]") String[] checkSetIds){
		equipmentCheckSetService.batchRemove(checkSetIds);
		return R.ok();
	}
	@GetMapping(value = "/export")
	public void Export(@RequestParam Map<String,Object> params, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ExportUtils<EquipmentCheckSetVO> exportUtils = new ExportUtils<>();
//        Map<String,Object> query=new HashMap<>();
//        query.put("code",request.getParameter("code"));
//        query.put("name",request.getParameter("name"));
		List<EquipmentCheckSetVO> checkSetList = equipmentCheckSetService.list(params);
		System.out.println("params:");
		for(Map.Entry<String,Object> param:params.entrySet()){
			System.out.println(param.getKey()+"-------"+param.getValue());
		}
		for(EquipmentCheckSetVO item:checkSetList){
			System.out.println("item:"+item);
		}
		exportUtils.exportFile(response,request,"bootdo导出文档",checkSetList);
	}
	@GetMapping("/equipmentSelect")
	public String equipmentSelect(){
		return "factory/equipmentCheckSet/equipmentSelect";
	}
}
