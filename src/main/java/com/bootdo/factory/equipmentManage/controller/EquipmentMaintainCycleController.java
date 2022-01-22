package com.bootdo.factory.equipmentManage.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.factory.equipmentManage.domain.EquipmentMaintainCycleDO;
import com.bootdo.factory.equipmentManage.service.EquipmentMaintainCycleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zrj
 * @email 1992lcg@163.com
 * @date 2020-02-19 21:11:33
 */

@Controller
@RequestMapping("/factory/equipmentMaintainCycle")
public class EquipmentMaintainCycleController {
    @Autowired
    private EquipmentMaintainCycleService equipmentMaintainCycleService;



    @GetMapping()
    @RequiresPermissions("factory:equipmentMaintainCycle:equipmentMaintainCycle")
    String EquipmentMaintainCycle() {
        return "factory/equipmentMaintainCycle/equipmentMaintainCycle";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("factory:equipmentMaintainCycle:equipmentMaintainCycle")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<EquipmentMaintainCycleDO> equipmentMaintainCycleList = equipmentMaintainCycleService.list(query);
        int total = equipmentMaintainCycleService.count(query);
        PageUtils pageUtils = new PageUtils(equipmentMaintainCycleList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("factory:equipmentMaintainCycle:add")
    String add() {
        return "factory/equipmentMaintainCycle/add";
    }

    @GetMapping("/edit/{maintainCycleId}")
    @RequiresPermissions("factory:equipmentMaintainCycle:edit")
    String edit(@PathVariable("maintainCycleId") String maintainCycleId, Model model) {
        EquipmentMaintainCycleDO equipmentMaintainCycle = equipmentMaintainCycleService.get(maintainCycleId);
        model.addAttribute("equipmentMaintainCycle", equipmentMaintainCycle);
        return "factory/equipmentMaintainCycle/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("factory:equipmentMaintainCycle:add")
    public R save(EquipmentMaintainCycleDO equipmentMaintainCycle) {
        Long userId = ShiroUtils.getUserId();
        equipmentMaintainCycle.setCreateUserId(userId + "");
        equipmentMaintainCycle.setCreateTime(new Date().getTime());
        if (equipmentMaintainCycleService.save(equipmentMaintainCycle) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("factory:equipmentMaintainCycle:edit")
    public R update(EquipmentMaintainCycleDO equipmentMaintainCycle) {
        equipmentMaintainCycleService.update(equipmentMaintainCycle);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("factory:equipmentMaintainCycle:remove")
    public R remove(String maintainCycleId) {
        if (equipmentMaintainCycleService.remove(maintainCycleId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("factory:equipmentMaintainCycle:batchRemove")
    public R remove(@RequestParam("ids[]") String[] maintainCycleIds) {
        equipmentMaintainCycleService.batchRemove(maintainCycleIds);
        return R.ok();
    }

}
