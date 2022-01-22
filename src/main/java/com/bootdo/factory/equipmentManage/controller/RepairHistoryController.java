package com.bootdo.factory.equipmentManage.controller;

import com.bootdo.common.utils.ExportUtils;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.factory.equipmentManage.domain.RepairHistoryDO;
import com.bootdo.factory.equipmentManage.service.RepairHistoryService;
import com.bootdo.factory.equipmentManage.service.RepairPushService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-02-18 14:06:55
 */

@Controller
@RequestMapping("/factory/repairHistory")
public class RepairHistoryController {
    @Autowired
    private RepairHistoryService repairHistoryService;
    @Autowired
    private RepairPushService repairPushService;

    @GetMapping()
    @RequiresPermissions("factory:repairHistory:repairHistory")
    String RepairHistory() {
        return "factory/repairHistory/repairHistory";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("factory:repairHistory:repairHistoryList")
    public PageUtils list(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        //查询列表数据
        Query query = new Query(params);
        List<RepairHistoryDO> repairHistoryList = repairHistoryService.list(query);
        int total = repairHistoryService.count(query);
        PageUtils pageUtils = new PageUtils(repairHistoryList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("factory:repairHistory:add")
    String add() {
        return "factory/repairHistory/add";
    }

    @GetMapping("/edit/{repairId}")
    @RequiresPermissions("factory:repairHistory:edit")
    String edit(@PathVariable("repairId") String repairId, Model model) {
        RepairHistoryDO repairHistory = repairHistoryService.get(repairId);
        model.addAttribute("repairHistory", repairHistory);
        return "factory/repairHistory/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("factory:repairHistory:add")
    public R save(RepairHistoryDO repairHistory) {
        if (repairHistoryService.save(repairHistory) > 0 && repairPushService.repair(repairHistory.getRepairId())) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("factory:repairHistory:edit")
    public R update(RepairHistoryDO repairHistory) {
        repairHistoryService.update(repairHistory);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("factory:repairHistory:remove")
    public R remove(String repairId) {
        if (repairHistoryService.remove(repairId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("factory:repairHistory:batchRemove")
    public R remove(@RequestParam("ids[]") String[] repairIds) {
        repairHistoryService.batchRemove(repairIds);
        return R.ok();
    }

    @RequestMapping(value = "/exportFile")
    @RequiresPermissions("factory:repairHistory:exportFile")
    public void Export(HttpServletResponse response, HttpServletRequest request) throws Exception {

        Map<String, Object> params = new HashMap<>();
        String name = request.getParameter("name");
        String searchType = request.getParameter("searchType");
        String dateBefore = request.getParameter("dateBefore");
        String dateLater = request.getParameter("dateLater");
        params.put("name", name);
        params.put("searchType", searchType);
        params.put("dateBefore", dateBefore);
        params.put("dateLater", dateLater);
        List<RepairHistoryDO> repairHistoryList = repairHistoryService.list(params);
        ExportUtils<RepairHistoryDO> exportUtils = new ExportUtils<RepairHistoryDO>();
        exportUtils.exportFile(response, request, "详细信息", repairHistoryList);


    }

}
