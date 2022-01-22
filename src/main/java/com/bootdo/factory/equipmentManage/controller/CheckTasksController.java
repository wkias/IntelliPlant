package com.bootdo.factory.equipmentManage.controller;

import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.factory.equipmentManage.domain.CheckContentDO;
import com.bootdo.factory.equipmentManage.domain.CheckTasksDO;
import com.bootdo.factory.equipmentManage.service.CheckContentService;
import com.bootdo.factory.equipmentManage.service.CheckTasksService;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author willice
 * @date 2020-02-25 13:11:24
 */

@Controller
@RequestMapping("/factory/checkTasks")
public class CheckTasksController {
    @Autowired
    private CheckTasksService checkTasksService;
    @Autowired
    private CheckContentService checkContentService;
    @Autowired
    private UserService userService;
    @Autowired
    private DictService dictService;

    @GetMapping()
    @RequiresPermissions("factory:checkTasks:checkTasks")
    String checkTasks(@RequestParam Map<String, Object> params, Model model) {
        UserDO user = (UserDO) SecurityUtils.getSubject().getPrincipal();
        String userId = user.getUserId().toString();
        params.put("checkerId", userId);
        int amount = checkTasksService.count(params);
        model.addAttribute("checkTasksAmount", amount);
        return "factory/checkTasks/checkTasks";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("factory:checkTasks:checkTasks")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        if (params.get("viewAll").toString().equals("0")) {
            UserDO user = (UserDO) SecurityUtils.getSubject().getPrincipal();
            String userId = user.getUserId().toString();
            params.put("checkerId", userId);
        }
        params.put("state", 0);
        //查询列表数据
        Query query = new Query(params);
        List<CheckTasksDO> checkTasksList = checkTasksService.list(query);
        int total = checkTasksService.count(query);
        return new PageUtils(checkTasksList, total);
    }

    @GetMapping("/check/{checkTaskId}")
    @RequiresPermissions("factory:checkTasks:check")
    String check(@PathVariable("checkTaskId") String checkTaskId, Model model) {
        CheckTasksDO checkTask = checkTasksService.get(checkTaskId);
        model.addAttribute("checkTask", checkTask);
        /* <----------------分割线---------------------> */
        //获取设备类型 “洗衣机” 格式
        String equipmentType = checkTasksService.get(checkTaskId).getEquipmentType();
        Map<String, Object> query = new LinkedHashMap<>();
        query.put("name", equipmentType);
        //从字典表获取设备类型DO
        DictDO equipmentTypeDict = dictService.list(query).get(0);
        query.clear();
        query.put("equipmentType", equipmentTypeDict.getId());
        //通过DO中的设备类型（“126” Long）获取点检内容
        List<CheckContentDO> checkContentList = checkContentService.list(query);
        String photoPrefix = "/factory/checkContent/photo/";
        if (checkContentList.size() != 0) {
            int i = 1;
            for (CheckContentDO check : checkContentList) {
                String user = userService.get(Long.parseLong(check.getCreatUserId())).getName();
                check.setCreatUserId(user);
                String type = dictService.get(Long.parseLong(check.getEquipmentType())).getName();
                check.setEquipmentType(type);
                check.setOrder(i++);
                check.setPhoto(photoPrefix + check.getPhoto());
            }
        }
        model.addAttribute("checkContentList", checkContentList);
        return "factory/checkTasks/check";
    }

    @GetMapping("/add")
    @RequiresPermissions("factory:checkTasks:add")
    String add() {
        return "factory/checkTasks/add";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("factory:checkTasks:add")
    public R save(CheckTasksDO checkTasks) {
        if (checkTasksService.save(checkTasks) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("factory:checkTasks:edit")
    public R update(CheckTasksDO checkTasks) {
        checkTasksService.update(checkTasks);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("factory:checkTasks:remove")
    public R remove(String checkId) {
        if (checkTasksService.remove(checkId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("factory:checkTasks:batchRemove")
    public R remove(@RequestParam("ids[]") String[] checkIds) {
        checkTasksService.batchRemove(checkIds);
        return R.ok();
    }

}
