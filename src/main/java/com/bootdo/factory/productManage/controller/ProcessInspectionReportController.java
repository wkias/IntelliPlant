package com.bootdo.factory.productManage.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.factory.productManage.domain.ProcessInspectionDetailDO;
import com.bootdo.factory.productManage.domain.ProcessInspectionReportDO;
import com.bootdo.factory.productManage.service.ProcessInspectionDetailService;
import com.bootdo.factory.productManage.service.ProcessInspectionReportService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author willice
 * @email 2782546116@qq.com
 * @date 2020-04-10 13:28:34
 */

@Controller
@RequestMapping("/productManage/processInspectionReport")
public class ProcessInspectionReportController {
    @Autowired
    private ProcessInspectionReportService processInspectionReportService;
    @Autowired
    private ProcessInspectionDetailService processInspectionDetailService;

    @GetMapping()
    @RequiresPermissions("productManage:processInspectionReport:processInspectionReport")
    String ProcessInspectionReport() {
        return "factory/productManage/processInspectionReport/processInspectionReport";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("productManage:processInspectionReport:processInspectionReport")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<ProcessInspectionReportDO> processInspectionReportList = processInspectionReportService.list(query);
        int total = processInspectionReportService.count(query);
        return new PageUtils(processInspectionReportList, total);
    }

    @ResponseBody
    @GetMapping("/listItems")
    @RequiresPermissions("productManage:processInspectionReport:processInspectionReport")
    public PageUtils listDetails(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<ProcessInspectionDetailDO> processInspectionDetailDOList = processInspectionDetailService.list(query);
        int total = processInspectionDetailService.count(query);
        return new PageUtils(processInspectionDetailDOList, total);
    }

    @GetMapping("/add")
    @RequiresPermissions("productManage:processInspectionReport:add")
    String add() {
        return "factory/productManage/processInspectionReport/add";
    }

    @GetMapping("/selectProcess")
    @RequiresPermissions("productManage:processInspectionReport:add")
    String selectProcess() {
        return "factory/productManage/processInspectionReport/selectProcess";
    }

    @GetMapping("/selectProduct")
    @RequiresPermissions("productManage:processInspectionReport:add")
    String selectProduct() {
        return "factory/productManage/processInspectionReport/selectProduct";
    }

    @GetMapping("/addItem")
    @RequiresPermissions("productManage:processInspectionReport:add")
    String addDetail() {
        return "factory/productManage/processInspectionReport/addItem";
    }

    @GetMapping("/edit/{processInspectionReportId}")
    @RequiresPermissions("productManage:processInspectionReport:edit")
    String edit(@PathVariable("processInspectionReportId") String processInspectionReportId, Model model) {
        ProcessInspectionReportDO processInspectionReport = processInspectionReportService.get(processInspectionReportId);
        model.addAttribute("processInspectionReport", processInspectionReport);
        return "factory/productManage/processInspectionReport/edit";
    }

    @GetMapping("/editItem/{processInspectionDetailId}")
    @RequiresPermissions("productManage:processInspectionReport:edit")
    String editDetail(@PathVariable("processInspectionDetailId") String processInspectionDetailId, Model model) {
        ProcessInspectionDetailDO processInspectionDetailDO = processInspectionDetailService.getOne(processInspectionDetailId);
        model.addAttribute("processInspectionDetail", processInspectionDetailDO);
        return "factory/productManage/processInspectionReport/editItem";
    }

    @GetMapping("/detail/{processInspectionReportId}")
    @RequiresPermissions("productManage:processInspectionReport:detail")
    String detail(@PathVariable("processInspectionReportId") String processInspectionReportId, Model model) {
        ProcessInspectionReportDO processInspectionReport = processInspectionReportService.get(processInspectionReportId);
        model.addAttribute("processInspectionReport", processInspectionReport);
        return "factory/productManage/processInspectionReport/detail";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("productManage:processInspectionReport:add")
    public R save(ProcessInspectionReportDO processInspectionReport, HttpServletRequest request) {
        Integer userId = ShiroUtils.getUserId().intValue();
        processInspectionReport.setCreateUserId(userId);
        processInspectionReport.setCreateTime(new Date());
        String[] processIds = request.getParameterValues("processId");
        String[] processNames = request.getParameterValues("processName");
        String[] componentIds = request.getParameterValues("componentId");
        String[] componentNames = request.getParameterValues("componentName");
        String[] formats = request.getParameterValues("format");
        String[] units = request.getParameterValues("unit");
        String[] qualifiedNums = request.getParameterValues("qualifiedNum");
        String[] reworkNums = request.getParameterValues("reworkNum");
        String[] scrapNums = request.getParameterValues("scrapNum");
        List<ProcessInspectionDetailDO> processInspectionDetailDOList = new LinkedList<>();
        for (int i = 0; i < processIds.length; i++) {
            ProcessInspectionDetailDO processInspectionDetailDO = new ProcessInspectionDetailDO();
            processInspectionDetailDO.setProcessId(processIds[i]);
            processInspectionDetailDO.setProcessName(processNames[i]);
            processInspectionDetailDO.setComponentId(componentIds[i]);
            processInspectionDetailDO.setComponentName(componentNames[i]);
            processInspectionDetailDO.setFormat(formats[i]);
            processInspectionDetailDO.setUnit(units[i]);
            processInspectionDetailDO.setQualifiedNum(Integer.valueOf(qualifiedNums[i]));
            processInspectionDetailDO.setReworkNum(Integer.valueOf(reworkNums[i]));
            processInspectionDetailDO.setScrapNum(Integer.valueOf(scrapNums[i]));
            processInspectionDetailDOList.add(processInspectionDetailDO);
        }
        processInspectionReport.setProcessInspectionDetailDOList(processInspectionDetailDOList);
        if (processInspectionReportService.save(processInspectionReport) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/saveItem")
    @RequiresPermissions("productManage:processInspectionReport:add")
    public R saveItem(ProcessInspectionDetailDO processInspectionDetailDO) {
        if (processInspectionDetailService.save(processInspectionDetailDO) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("productManage:processInspectionReport:edit")
    public R update(ProcessInspectionReportDO processInspectionReport) {
        if (processInspectionReportService.update(processInspectionReport) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/updateItem")
    @RequiresPermissions("productManage:processInspectionReport:edit")
    public R updateItem(ProcessInspectionDetailDO processInspectionDetailDO) {
        if (processInspectionDetailService.update(processInspectionDetailDO) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("productManage:processInspectionReport:remove")
    public R remove(String processInspectionReportId) {
        if (processInspectionReportService.remove(processInspectionReportId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/removeItem")
    @ResponseBody
    @RequiresPermissions("productManage:processInspectionReport:remove")
    public R removeItem(String processInspectionDetailId) {
        if (processInspectionDetailService.remove(processInspectionDetailId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("productManage:processInspectionReport:batchRemove")
    public R remove(@RequestParam("ids[]") String[] processInspectionReportIds) {
        processInspectionReportService.batchRemove(processInspectionReportIds);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemoveItems")
    @ResponseBody
    @RequiresPermissions("productManage:processInspectionReport:batchRemove")
    public R batchRemoveDetail(@RequestParam("ids[]") String[] processInspectionDetailIds) {
        processInspectionDetailService.batchRemove(processInspectionDetailIds);
        return R.ok();
    }

}
