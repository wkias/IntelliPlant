package com.bootdo.factory.equipmentManage.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.factory.equipmentManage.domain.RepairPushDO;
import com.bootdo.factory.equipmentManage.service.RepairPushService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author willice
 * @date 2020-02-19 17:15:02
 */

@Controller
@RequestMapping("/factory/repairPush")
public class RepairPushController {
    @Autowired
    private RepairPushService repairPushService;

    @GetMapping()
    @RequiresPermissions("factory:repairPush:repairPush")
    String RepairPush(@RequestParam Map<String, Object> params, Model model) {
        int amount = repairPushService.count(params);
        model.addAttribute("equipmentRepairAmount", amount);
        return "factory/repairPush/repairPush";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("factory:repairPush:repairPush")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<RepairPushDO> repairPushList = repairPushService.list(query);
        int total = repairPushService.count(query);
        PageUtils pageUtils = new PageUtils(repairPushList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("factory:repairPush:add")
    String add() {
        return "factory/repairPush/add";
    }

    @GetMapping("/affirm/{pushInfoId}")
    @RequiresPermissions("factory:repairPush:affirm")
    String affirm(@PathVariable("pushInfoId") String pushInfoId, Model model) {
        RepairPushDO repairPush = repairPushService.get(pushInfoId);
        model.addAttribute("repairPush", repairPush);
        return "factory/repairPush/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("factory:repairPush:add")
    public R save(RepairPushDO repairPush) {
        if (repairPushService.save(repairPush) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("factory:repairPush:affirm")
    public R update(RepairPushDO repairPush) {
        repairPushService.update(repairPush);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("factory:repairPush:remove")
    public R remove(String pushInfoId) {
        if (repairPushService.remove(pushInfoId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("factory:repairPush:batchRemove")
    public R remove(@RequestParam("ids[]") String[] pushInfoIds) {
        repairPushService.batchRemove(pushInfoIds);
        return R.ok();
    }

}
