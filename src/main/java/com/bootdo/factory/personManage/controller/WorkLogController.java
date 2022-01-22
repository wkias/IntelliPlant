package com.bootdo.factory.personManage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.*;
import com.bootdo.factory.personManage.vo.WorkLogVO;
import com.bootdo.system.domain.UserDO;
import org.apache.shiro.SecurityUtils;
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

import com.bootdo.factory.personManage.domain.WorkLogDO;
import com.bootdo.factory.personManage.service.WorkLogService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 
 * @author zrj
 * @email 1992lcg@163.com
 * @date 2020-03-13 17:22:04
 */
 
@Controller
@RequestMapping("/factory/workLog")
public class WorkLogController {
	@Autowired
	private WorkLogService workLogService;
    @Autowired
    private DictService dictService;
	@GetMapping()
	@RequiresPermissions("factory:workLog:workLog")
	String WorkLog(){
	    return "factory/workLog/workLog";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("factory:workLog:workLog")
	public PageUtils list(@RequestParam Map<String, Object> params){
	    if (params.get("queryAll").toString().equals("0")) {
            UserDO user = (UserDO) SecurityUtils.getSubject().getPrincipal();
            String userId = user.getUserId().toString();
            params.put("userId", userId);
        }
		//查询列表数据
        Query query = new Query(params);
		List<WorkLogVO> workLogList = workLogService.list(query);
		int total = workLogService.count(query);
		PageUtils pageUtils = new PageUtils(workLogList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("factory:workLog:add")
	String add(){
	    return "factory/workLog/add";
	}

	/*@GetMapping("/edit/{workLogId}")
	@RequiresPermissions("factory:workLog:edit")
	String edit(@PathVariable("workLogId") String workLogId,Model model){
		WorkLogDO workLog = workLogService.get(workLogId);
		model.addAttribute("workLog", workLog);
	    return "factory/workLog/edit";
	}*/

	@GetMapping("/details/{workLogId}")
	@RequiresPermissions("factory:workLog:details")
	String details(@PathVariable("workLogId") String workLogId,Model model){
		WorkLogDO workLog = workLogService.get(workLogId);
        workLog.setTimeTypeName(dictService.getName("work_time_type",workLog.getWorkTimeType()));
        workLog.setHourName(dictService.getName("hour_period",workLog.getHourPeriod()));
		model.addAttribute("workLog", workLog);
		return "factory/workLog/details";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("factory:workLog:add")
	public R save( WorkLogDO workLog){
		Long userId = ShiroUtils.getUserId();
        workLog.setUserId(userId + "");
		workLog.setCreateUserId(userId + "");
		workLog.setCreateDate(new Date().getTime());
		if(workLogService.save(workLog)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("factory:workLog:edit")
	public R update( WorkLogDO workLog){
		workLogService.update(workLog);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	/*@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("factory:workLog:remove")
	public R remove( String workLogId){
		if(workLogService.remove(workLogId)>0){
		return R.ok();
		}
		return R.error();
	}*/
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("factory:workLog:batchRemove")
	public R remove(@RequestParam("ids[]") String[] workLogIds){
		workLogService.batchRemove(workLogIds);
		return R.ok();
	}

    @RequestMapping(value = "/exportFile")
    @RequiresPermissions("factory:workLog:exportFile")
    public void Export(HttpServletResponse response, HttpServletRequest request) throws Exception {

        Map<String, Object> params = new HashMap<>();
        String userName = request.getParameter("userName");
        String dateBefore = request.getParameter("dateBefore");
        String dateLater = request.getParameter("dateLater");
        params.put("userName", userName);
        params.put("dateBefore", dateBefore);
        params.put("dateLater", dateLater);
        List<WorkLogVO> workLogVOList = workLogService.list(params);
        ExportUtils<WorkLogVO> exportUtils = new ExportUtils<WorkLogVO>();
        exportUtils.exportFile(response, request, "详细信息", workLogVOList);


    }


    /**
     * 详情
     */
    /*@GetMapping("/details/{workLogId}")
    @RequiresPermissions("factory:workLog:details")
    public String details(@PathVariable String workLogId, Model model) {
        //查询列表数据

        WorkLogDO workLog = workLogService.get(workLogId);
        model.addAttribute("workLog", workLog);

        return "factory/workLog/details";
    }*/


}
