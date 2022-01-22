package com.bootdo.factory.productManage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.factory.productManage.dao.ProcessRelationshipDao;
import com.bootdo.factory.productManage.domain.InspectionItemsDO;

import com.bootdo.factory.productManage.domain.ProcessRelationshipDO;
import com.bootdo.factory.productManage.service.InspectionItemsService;
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

import com.bootdo.factory.productManage.domain.ProcessInspectionDO;
import com.bootdo.factory.productManage.service.ProcessInspectionService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 
 * @author zrj
 * @email 1992lcg@163.com
 * @date 2020-03-26 21:55:29
 */
 
@Controller
@RequestMapping("/productManage/processInspection")
public class ProcessInspectionController {
	@Autowired
	private ProcessInspectionService processInspectionService;
	@Autowired
	private com.bootdo.factory.service.ProcessDefinitionService processDefinitionService;
    @Autowired
    private InspectionItemsService inspectionItemsService;
    @Autowired
    private ProcessRelationshipDao processRelationshipDao;
    @Autowired
    private DictService dictService;
	@GetMapping()
	@RequiresPermissions("productManage:processInspection:processInspection")
	String ProcessInspection(){
	    return "factory/processInspection/processInspection";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("productManage:processInspection:processInspection")
	public PageUtils list(@RequestParam Map<String, Object> params ){
		//查询列表数据
        Query query = new Query(params);
		List<ProcessInspectionDO> processInspectionList = processInspectionService.list(query);
		int total = processInspectionService.count(query);
		PageUtils pageUtils = new PageUtils(processInspectionList, total);

		for(ProcessInspectionDO processInspection : processInspectionList){
            Map<String,Object> promap=new HashMap<>();
            promap.put("processInspectionId",processInspection.getProcessInspectionId());
            List<ProcessRelationshipDO> processRelationshipDOS=processRelationshipDao.list(promap);
            for(ProcessRelationshipDO processRelationship:processRelationshipDOS){
                InspectionItemsDO items=inspectionItemsService.get(processRelationship.getInspectionItemsId());
                items.setUnitName(dictService.getName("unit",items.getUnit()));
                items.setValueTypeName(dictService.getName("valueType",items.getValueType()));
                processInspection.setItems(items);
            }

        }


		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("productManage:processInspection:add")
	String add(){
	    return "factory/processInspection/add";
	}



	@GetMapping("/edit/{processInspectionId}")
	@RequiresPermissions("productManage:processInspection:edit")
	String edit(@PathVariable("processInspectionId") String processInspectionId,Model model){
        /*Map<String,Object> map = new HashMap<>();
        map.put("processInspectionId",processInspectionId);
        ProcessInspectionDO processInspection = processInspectionService.list(map).get(0);
        model.addAttribute("processInspection", processInspection);*/
		ProcessInspectionDO processInspection = processInspectionService.get(processInspectionId);
		model.addAttribute("processInspection", processInspection);
		Map<String,Object> map=new HashMap<>();
		map.put("processInspectionId",processInspectionId);
		List<ProcessRelationshipDO> processRelationshipDOS=processRelationshipDao.list(map);
		for(ProcessRelationshipDO processRelationship:processRelationshipDOS){
			InspectionItemsDO items=inspectionItemsService.get(processRelationship.getInspectionItemsId());
			processRelationship.setItems(items);
		}
		model.addAttribute("processRelationshipDOS",processRelationshipDOS);
		return "factory/processInspection/edit";
	}

    @GetMapping("/details/{processInspectionId}")
    @RequiresPermissions("productManage:processInspection:edit")
    String details(@PathVariable("processInspectionId") String processInspectionId,Model model){
        /*Map<String,Object> map = new HashMap<>();
        map.put("processInspectionId",processInspectionId);
        ProcessInspectionDO processInspection = processInspectionService.list(map).get(0);
        model.addAttribute("processInspection", processInspection);*/
        ProcessInspectionDO processInspection = processInspectionService.get(processInspectionId);
        model.addAttribute("processInspection", processInspection);
        Map<String,Object> map=new HashMap<>();
        map.put("processInspectionId",processInspectionId);
        List<ProcessRelationshipDO> processRelationshipDOS=processRelationshipDao.list(map);
        for(ProcessRelationshipDO processRelationship:processRelationshipDOS){
            InspectionItemsDO items=inspectionItemsService.get(processRelationship.getInspectionItemsId());
            processRelationship.setItems(items);
        }
        model.addAttribute("processRelationshipDOS",processRelationshipDOS);
        return "factory/processInspection/details";
    }


	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("productManage:processInspection:add")
	public R save( ProcessInspectionDO processInspection, HttpServletRequest request){
		if(processInspectionService.save(processInspection,request)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("productManage:processInspection:edit")
	public R update( ProcessInspectionDO processInspection, HttpServletRequest request){
		processInspectionService.update(processInspection,request);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("productManage:processInspection:remove")
	public R remove( String processInspectionId){
		if(processInspectionService.remove(processInspectionId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("productManage:processInspection:batchRemove")
	public R remove(@RequestParam("ids[]") String[] processInspectionIds){
		processInspectionService.batchRemove(processInspectionIds);
		return R.ok();
	}



	@ResponseBody
	@GetMapping("/processList")
	@RequiresPermissions("productManage:processInspection:processInspection")
	public List<com.bootdo.factory.domain.ProcessDefinitionDO>  processList(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<com.bootdo.factory.domain.ProcessDefinitionDO> processes = processDefinitionService.list(params);
		return processes;
	}
    @ResponseBody
    @GetMapping("/itemsList/{processInspectionId}")
    @RequiresPermissions("productManage:processInspection:processInspection")
    public List<ProcessRelationshipDO> list(@PathVariable("processInspectionId") String processInspectionId){
        //查询列表数据
        Map<String,Object> map=new HashMap<>();
        map.put("processInspectionId",processInspectionId);
        List<ProcessRelationshipDO> processRelationshipDOS=processRelationshipDao.list(map);
        for(ProcessRelationshipDO processRelationship:processRelationshipDOS){
            InspectionItemsDO items=inspectionItemsService.get(processRelationship.getInspectionItemsId());
            processRelationship.setItems(items);
			items.setUnitName(dictService.getName("unit",items.getUnit()));
            items.setValueTypeName(dictService.getName("valueType",items.getValueType()));
        }
        return processRelationshipDOS;
    }
    @GetMapping("/itemsSelect")
    @RequiresPermissions("productManage:processInspection:processInspection")
     String itemsSelect(Model model){

	    return  "factory/processInspection/itemsSelect";
    }


}

