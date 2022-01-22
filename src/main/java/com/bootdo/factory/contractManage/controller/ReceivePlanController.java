package com.bootdo.factory.contractManage.controller;

import com.bootdo.common.utils.*;
import com.bootdo.factory.contractManage.domain.ReceivePlanDO;
import com.bootdo.factory.contractManage.service.ReceivePlanService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author willice
 * @email 2782546116@qq.com
 * @date 2020-03-06 12:08:15
 */

@Controller
@RequestMapping("/factory/receivePlan")
public class ReceivePlanController {
    @Autowired
    private ReceivePlanService receivePlanService;

    @GetMapping()
    @RequiresPermissions("factory:receivePlan:receivePlan")
    String ReceivePlan() {
        return "factory/receivePlan/receivePlan";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("factory:receivePlan:receivePlan")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<ReceivePlanDO> receivePlanList = receivePlanService.list(query);
        int total = receivePlanService.count(query);
        PageUtils pageUtils = new PageUtils(receivePlanList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("factory:receivePlan:add")
    String add() {
        return "factory/receivePlan/add";
    }

    @GetMapping("/receiveRecord/{contractCode}")
    @RequiresPermissions("factory:receivePlan:receiveRecord")
    String receiveRecord(@PathVariable("contractCode") String contractCode, Model model) {
        model.addAttribute(contractCode);
        return "factory/receivePlan/receiveRecord";
    }

    @GetMapping("/edit/{receiveId}")
    @RequiresPermissions("factory:receivePlan:edit")
    String edit(@PathVariable("receiveId") String receiveId, Model model) {
        ReceivePlanDO receivePlan = receivePlanService.get(receiveId);
        model.addAttribute("receivePlan", receivePlan);
        return "factory/receivePlan/edit";
    }

    @GetMapping("/detail/{receiveId}")
    @RequiresPermissions("factory:receivePlan:receivePlan")
    String detail(@PathVariable("receiveId") String receiveId, Model model) {
        ReceivePlanDO receivePlan = receivePlanService.get(receiveId);
        model.addAttribute("receivePlan", receivePlan);
        return "factory/receivePlan/detail";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("factory:receivePlan:add")
    public R save(ReceivePlanDO receivePlan) {
        receivePlan.setCreateUserId(ShiroUtils.getUserId());
        receivePlan.setCreateTime(new Timestamp(new Date().getTime()));
        receivePlan.setIsDeleted(false);
        if (receivePlanService.save(receivePlan) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("factory:receivePlan:edit")
    public R update(ReceivePlanDO receivePlan) {
        receivePlanService.update(receivePlan);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("factory:receivePlan:remove")
    public R remove(String receiveId) {
        if (receivePlanService.remove(receiveId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("factory:receivePlan:batchRemove")
    public R remove(@RequestParam("ids[]") String[] receiveIds) {
        receivePlanService.batchRemove(receiveIds);
        return R.ok();
    }

    /**
     * 导出
     */
    @GetMapping("/export")
    @RequiresPermissions("factory:receivePlan:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ExportUtils<ReceivePlanDO> exportUtils = new ExportUtils<>();
        List<ReceivePlanDO> receivePlanDOList = receivePlanService.list(params);
        exportUtils.exportFile(response, request, "回款计划", receivePlanDOList);
    }

    @GetMapping("/contractSelect")
    @RequiresPermissions("factory:receivePlan:add")
    public String contractSelect() {
        return "factory/receivePlan/contractSelect";
    }
}
