package com.bootdo.activiti.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.activiti.domain.SalaryDO;
import com.bootdo.activiti.domain.Variable;
import com.bootdo.activiti.domain.Variables;
import com.bootdo.activiti.utils.ActivitiUtils;
import com.bootdo.activiti.vo.LeaveBillVO;
import com.bootdo.common.utils.*;
import com.bootdo.factory.equipmentManage.vo.EquipmentCheckSetVO;
import org.activiti.engine.FormService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.apache.commons.configuration.interpol.ExprLookup;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bootdo.activiti.domain.LeaveBillDO;
import com.bootdo.activiti.service.LeaveBillService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 *请假流程，批注在动态表单属性中设置，封装到Varaibles对象中存储到activiti的流程变量里
 * @author xh
 * @email 1992lcg@163.com
 * @date 2020-03-02 09:38:31
 */

@Controller
@RequestMapping("/act/leaveBill")
public class LeaveBillController {
	@Autowired
	private LeaveBillService leaveBillService;
	@Autowired
	private FormService formService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private ActivitiUtils activitiUtils;
	@GetMapping()
	String LeaveBill(){
	    return "act/leaveBill/leaveBill";
	}

	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//如果没有查询所有人请假单的权限，就只查询自己的
		boolean canQueryAll=ShiroUtils.getSubjct().isPermitted("act:leaveBill:queryAll");
		if(!canQueryAll){
			params.put("userId",ShiroUtils.getUserId());
		}
		//查询列表数据
        Query query = new Query(params);
		List<LeaveBillVO> leaveBillList = leaveBillService.list(query);
		int total = leaveBillService.count(query);
		PageUtils pageUtils = new PageUtils(leaveBillList, total);
		return pageUtils;
	}

	@RequestMapping("/form")
	String add(@RequestAttribute("procdefId") String procdefId,Model model){
		model.addAttribute("procdefId",procdefId);
		return "act/leaveBill/add";
	}

//	@GetMapping("/edit/{billId}")
//	String edit(@PathVariable("billId") String billId,Model model){
//		LeaveBillDO leaveBill = leaveBillService.get(billId);
//		TaskFormData taskFormData=formService.getTaskFormData(leaveBill.getTaskId());
//		List<FormProperty> formProperties=taskFormData.getFormProperties();
//		Variables variables=taskService.getVariable(leaveBill.getTaskId(),"comments", Variables.class);
//		model.addAttribute("leaveBill", leaveBill);
//		model.addAttribute("comments",variables.getVariables());
//		model.addAttribute("formProperties",formProperties);
//		return "act/leaveBill/edit";
//	}
	@GetMapping("/form/{taskId}")
	String edit(@PathVariable("taskId") String taskId, Model model) {
		Map<String,Object> map = new HashMap<>();
		map.put("billId",activitiUtils.getBusinessKeyByTaskId(taskId));
		LeaveBillVO leaveBill = leaveBillService.list(map).get(0);
		leaveBill.setTaskId(taskId);
		TaskFormData taskFormData=formService.getTaskFormData(taskId);
		List<FormProperty> formProperties=taskFormData.getFormProperties();
		Variables formVariables=new Variables();
		for(FormProperty formProperty:formProperties){
			Variable var=new Variable();
			var.setKeys(formProperty.getName());
			var.setValues(formProperty.getValue());
			formVariables.getVariables().add(var);
		}

		Variables variables=taskService.getVariable(taskId,"comments", Variables.class);
		model.addAttribute("leaveBill", leaveBill);
		model.addAttribute("comments",variables==null?null:variables.getVariables());
		//model.addAttribute("formProperties",formProperties);
		model.addAttribute("formVariables",formVariables);

		Long curUserId=ShiroUtils.getUserId();
		model.addAttribute("currentUserId",curUserId);
		return "act/leaveBill/edit";
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( LeaveBillDO leaveBill,String procdefId){
		if(leaveBillService.save(leaveBill,procdefId)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update(LeaveBillDO leaveBill, @ModelAttribute(value = "variables")Variables variables){
		String taskID=leaveBill.getTaskId();
		System.out.println("variables:"+variables);
		Variables comments=taskService.getVariable(taskID,"comments",Variables.class);
		if(comments==null){comments=new Variables();}
		comments.getVariables().addAll(variables.getVariables());

		taskService.setVariable(taskID,"comments",comments);
//		Variables Ncomments=taskService.getVariable(taskID,"comments",Variables.class);
//		System.out.println("Ncomments:"+Ncomments);
		leaveBillService.update(leaveBill);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( String billId){
		if(leaveBillService.remove(billId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") String[] billIds){
		leaveBillService.batchRemove(billIds);
		return R.ok();
	}
	@GetMapping(value = "/exportFile")
	public void Export(@RequestParam Map<String,Object> params, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ExportUtils<EquipmentCheckSetVO> exportUtils = new ExportUtils<>();
//        Map<String,Object> query=new HashMap<>();
//        query.put("code",request.getParameter("code"));
//        query.put("name",request.getParameter("name"));
		List<LeaveBillVO> leaveBillVOS = leaveBillService.list(params);
//		System.out.println("params:");
//		for(Map.Entry<String,Object> param:params.entrySet()){
//			System.out.println(param.getKey()+"-------"+param.getValue());
//		}
//		for(LeaveBillVO item:leaveBillVOS){
//			System.out.println("item:"+item);
//		}
		exportUtils.exportFile(response,request,"bootdo导出文档",leaveBillVOS);
	}
}
