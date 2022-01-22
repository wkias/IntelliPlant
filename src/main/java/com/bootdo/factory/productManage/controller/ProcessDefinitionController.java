package com.bootdo.factory.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.factory.equipmentManage.domain.EquipmentCheckHistoryDO;
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

import com.bootdo.factory.domain.ProcessDefinitionDO;
import com.bootdo.factory.service.ProcessDefinitionService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * @author Casper
 * @email 1992lcg@163.com
 * @date 2020-03-24 11:16:31
 */

@Controller
@RequestMapping("/factory/processDefinition")
public class ProcessDefinitionController {
    @Autowired
    private ProcessDefinitionService processDefinitionService;

    @GetMapping()
    @RequiresPermissions("factory:processDefinition:processDefinition")
    String ProcessDefinition() {
        return "factory/processDefinition/processDefinition";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("factory:processDefinition:processDefinition")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<ProcessDefinitionDO> processDefinitionList = processDefinitionService.list(query);
        int total = processDefinitionService.count(query);
        PageUtils pageUtils = new PageUtils(processDefinitionList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("factory:processDefinition:add")
    String add() {
        return "factory/processDefinition/add";
    }

    @GetMapping("/edit/{processId}")
    @RequiresPermissions("factory:processDefinition:edit")
    String edit(@PathVariable("processId") String processId, Model model) {
        ProcessDefinitionDO processDefinition = processDefinitionService.get(processId);
        model.addAttribute("processDefinition", processDefinition);
        return "factory/processDefinition/edit";
    }

    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("factory:processDefinition:add")
    public R save(ProcessDefinitionDO processDefinition) {
        Long userId = ShiroUtils.getUserId();
        processDefinition.setCreateUserId(userId + "");
        processDefinition.setCreateTime(new Date());
        processDefinition.setIsDeleted(false);
        if (processDefinitionService.save(processDefinition) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("factory:processDefinition:edit")
    public R update(ProcessDefinitionDO processDefinition) {
        processDefinitionService.update(processDefinition);
        return R.ok();
    }

    @PostMapping("/changeState")
    @ResponseBody
    public R changeState(String processId, String cmd) {
        String label = "停用";
        if ("start".equals(cmd)) {
            label = "启用";
        } else {
            label = "停用";
        }
        try {
            processDefinitionService.changeState(processId, cmd);
            return R.ok("产品" + label + "成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.ok("产品" + label + "失败");
    }

    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("factory:processDefinition:remove")
    public R remove(String processId) {
        if (processDefinitionService.remove(processId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("factory:processDefinition:batchRemove")
    public R remove(@RequestParam("ids[]") String[] processIds) {
        processDefinitionService.batchRemove(processIds);
        return R.ok();
    }
}
