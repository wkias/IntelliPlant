package com.bootdo.factory.equipmentManage.controller;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.ExportUtils;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.factory.equipmentManage.domain.CheckContentDO;
import com.bootdo.factory.equipmentManage.domain.EquipmentCheckHistoryDO;
import com.bootdo.factory.equipmentManage.service.CheckContentService;
import com.bootdo.factory.equipmentManage.service.CheckTasksService;
import com.bootdo.factory.equipmentManage.service.EquipmentCheckHistoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author Casper
 * @email 1992lcg@163.com
 * @date 2020-03-01 15:20:46
 */

@Controller
@RequestMapping("/factory/equipmentCheckHistory")
public class EquipmentCheckHistoryController {
    @Autowired
    private EquipmentCheckHistoryService equipmentCheckHistoryService;
    @Autowired
    private CheckContentService checkContentService;
    @Autowired
    private CheckTasksService checkTasksService;

    @Autowired
    private DictService dictService;

    @GetMapping()
    @RequiresPermissions("factory:equipmentCheckHistory:equipmentCheckHistory")
    String EquipmentCheckHistory() {
        return "factory/equipmentCheckHistory/equipmentCheckHistory";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("factory:equipmentCheckHistory:equipmentCheckHistory")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<EquipmentCheckHistoryDO> equipmentCheckHistoryList = equipmentCheckHistoryService.list(query);
        int total = equipmentCheckHistoryService.count(query);
        return new PageUtils(equipmentCheckHistoryList, total);
    }

    @GetMapping("/add")
    @RequiresPermissions("factory:equipmentCheckHistory:add")
    String add() {
        return "factory/equipmentCheckHistory/add";
    }

    @GetMapping("/checkDetails/{checkHistoryId}")
    @RequiresPermissions("factory:equipmentCheckHistory:checkDetails")
    String check(@PathVariable("checkHistoryId") String checkHistoryId, Model model) {
        EquipmentCheckHistoryDO checkHistory = equipmentCheckHistoryService.get(checkHistoryId);
        model.addAttribute("checkHistory", checkHistory);
        EquipmentCheckHistoryDO historyDO = equipmentCheckHistoryService.get(checkHistoryId);
        Map<String, Object> state = (Map<String, Object>) JSONObject.parse(historyDO.getState());
        List<CheckContentDO> checkContentDOList = new LinkedList<>();
        int i = 1;
        for (Map.Entry<String, Object> contentId : state.entrySet()) {
            CheckContentDO checkContentDO = checkContentService.get(Integer.parseInt(contentId.getKey()));
            checkContentDO.setCheckId(i++);
            if (contentId.getValue().toString().equals("1")) {
                checkContentDO.setState("异常");
            } else {
                checkContentDO.setState("正常");
            }
            checkContentDOList.add(checkContentDO);
        }
        model.addAttribute("checkContentList", checkContentDOList);
        return "factory/equipmentCheckHistory/checkDetails";
    }

    @GetMapping("/edit/{checkHistoryId}")
    @RequiresPermissions("factory:equipmentCheckHistory:edit")
    String edit(@PathVariable("checkHistoryId") String checkHistoryId, Model model) {
        EquipmentCheckHistoryDO equipmentCheckHistory = equipmentCheckHistoryService.get(checkHistoryId);
        model.addAttribute("equipmentCheckHistory", equipmentCheckHistory);
        return "factory/equipmentCheckHistory/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("factory:equipmentCheckHistory:add")
    public R save(@RequestParam Map<String, Object> map) {
        EquipmentCheckHistoryDO checkHistoryDO = new EquipmentCheckHistoryDO();
        checkHistoryDO.setCheckHistoryId(map.remove("checkTaskId").toString());
        String equipmentType = map.remove("equipmentType").toString();
        Map<String, Object> query = new LinkedHashMap<>();
        query.put("name", equipmentType);
        //从字典表获取设备类型DO
        DictDO equipmentTypeDict = dictService.list(query).get(0);
        checkHistoryDO.setEquipmentType(equipmentTypeDict.getValue());
        checkHistoryDO.setCode(map.remove("code").toString());
        checkHistoryDO.setName(map.remove("name").toString());
        checkHistoryDO.setCheckerId(map.remove("checkerId").toString());
        checkHistoryDO.setCheckerName(map.remove("checkerName").toString());
        checkHistoryDO.setRemarks(map.remove("remarks").toString());
        checkHistoryDO.setState(new JSONObject(map).toJSONString());
        if (equipmentCheckHistoryService.save(checkHistoryDO) > 0 && checkTasksService.check(checkHistoryDO.getCheckHistoryId()) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("factory:equipmentCheckHistory:edit")
    public R update(EquipmentCheckHistoryDO equipmentCheckHistory) {
        equipmentCheckHistoryService.update(equipmentCheckHistory);
        return R.ok();
    }

    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("factory:equipmentCheckHistory:remove")
    public R remove(String checkHistoryId) {
        if (equipmentCheckHistoryService.remove(checkHistoryId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("factory:equipmentCheckHistory:batchRemove")
    public R remove(@RequestParam("ids[]") String[] checkHistoryIds) {
        equipmentCheckHistoryService.batchRemove(checkHistoryIds);
        return R.ok();
    }

    @RequestMapping(value = "/exportFile")
    @RequiresPermissions("factory:equipmentCheckHistory:exportFile")
    public void Export(HttpServletResponse response, HttpServletRequest request) throws Exception {
        Map<String, Object> params = new HashMap<>();
        String name = request.getParameter("name");
        String searchType = request.getParameter("searchType");
        params.put("name", name);
        params.put("searchType", searchType);
        List<EquipmentCheckHistoryDO> equipmentCheckHistoryList = equipmentCheckHistoryService.list(params);
        ExportUtils<EquipmentCheckHistoryDO> exportUtils = new ExportUtils<EquipmentCheckHistoryDO>();
        exportUtils.exportFile(response, request, "详细信息", equipmentCheckHistoryList);
    }
}
