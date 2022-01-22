package com.bootdo.factory.warehouseManagement.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.factory.warehouseManagement.domain.LogisticsAccountDO;
import com.bootdo.factory.warehouseManagement.service.LogisticsAccountService;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Casper
 * @email 1992lcg@163.com
 * @date 2020-03-17 15:48:07
 */

@Controller
@RequestMapping("/factory/logisticsAccount")
public class LogisticsAccountController {
    private static String fileRootPath;
    @Autowired
    private LogisticsAccountService logisticsAccountService;
    @Autowired
    private BootdoConfig bootdoConfig;

    @PostConstruct
    private void init() {
        fileRootPath = bootdoConfig.getUploadPath();
    }

    @GetMapping()
    @RequiresPermissions("factory:logisticsAccount:logisticsAccount")
    String LogisticsAccount() {
        return "factory/logisticsAccount/logisticsAccount";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("factory:logisticsAccount:logisticsAccount")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<LogisticsAccountDO> logisticsAccountList = logisticsAccountService.list(query);
        int total = logisticsAccountService.count(query);
        PageUtils pageUtils = new PageUtils(logisticsAccountList, total);
        return pageUtils;
    }


    @GetMapping("/add")
    @RequiresPermissions("factory:logisticsAccount:add")
    String add(Model model) {
        model.addAttribute("uuid", UUID.randomUUID().toString());
        return "factory/logisticsAccount/add";
    }

    @GetMapping("/edit/{logisticsId}")
    @RequiresPermissions("factory:logisticsAccount:edit")
    String edit(@PathVariable("logisticsId") String logisticsId, Model model) {
        LogisticsAccountDO logisticsAccount = logisticsAccountService.get(logisticsId);
        model.addAttribute("logisticsAccount", logisticsAccount);
        return "factory/logisticsAccount/edit";
    }

    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("factory:logisticsAccount:add")
    public R save(LogisticsAccountDO logisticsAccount, HttpSession session, MultipartFile filesFile) throws Exception {
        Long userId = ShiroUtils.getUserId();
        logisticsAccount.setCreateUserId(userId + "");
        logisticsAccount.setCreateTime(new Date());
        logisticsAccount.setIsDeleted(false);
        if (filesFile != null && !filesFile.isEmpty()) {
            String filesFileName = saveFiles(filesFile);
            logisticsAccount.setFiles(filesFileName);
        }
        if (logisticsAccountService.save(logisticsAccount) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("factory:logisticsAccount:edit")
    public R update(LogisticsAccountDO logisticsAccount) {
        logisticsAccountService.update(logisticsAccount);
        return R.ok();
    }

    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("factory:logisticsAccount:remove")
    public R remove(String logisticsId) {
        if (logisticsAccountService.remove(logisticsId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("factory:logisticsAccount:batchRemove")
    public R remove(@RequestParam("ids[]") String[] logisticsIds) {
        for (String logisticsId : logisticsIds) {
            LogisticsAccountDO oldEquipment = logisticsAccountService.get(logisticsId);
            String oldFilePath = fileRootPath + "files/" + oldEquipment.getFiles();
            FileUtil.deleteFile(oldFilePath);//删除文件
        }
        logisticsAccountService.batchRemove(logisticsIds);
        return R.ok();
    }

    @GetMapping("/details/{logisticsId}")
    @RequiresPermissions("factory:logisticsAccount:details")
    public String details(@PathVariable String logisticsId, Model model) {
        //查询列表数据
        LogisticsAccountDO logisticsDetail = logisticsAccountService.getWithNameType(logisticsId);
        model.addAttribute("logisticsAccount", logisticsDetail);
        return "factory/logisticsAccount/details";
    }

    private String saveFiles(MultipartFile files) throws Exception {
        String filesName = files.getOriginalFilename();
        filesName = FileUtil.renameToUUID(filesName);
        String filesPath = fileRootPath + "files/";
        FileUtil.uploadFile(files.getBytes(), filesPath, filesName);
        return filesName;
    }

    private byte[] getFilesBytes(String filesName) throws Exception {
        String filesPath = fileRootPath + "files/" + filesName;
        byte[] filesBytes = Files.readAllBytes(Paths.get(filesPath));
        return filesBytes;
    }

    @GetMapping("/file/{fileName}")
    public void fileDownload(@PathVariable("fileName") String filesName, HttpServletResponse response) throws Exception {
        try {
            if (filesName == null || filesName.equals("")) {
                return;
            }
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + filesName);
            response.getOutputStream().write(getFilesBytes(filesName));
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/exportFile")
    @RequiresPermissions("factory:logisticsAccount:exportFile")
    public void Export(HttpServletResponse response, HttpServletRequest request) throws Exception {
        Map<String, Object> params = new HashMap<>();
        String formId = request.getParameter("Id");
        String formType = request.getParameter("searchType");
        params.put("Id", formId);
        params.put("searchType", formType);
        List<LogisticsAccountDO> logisticsAccountList = logisticsAccountService.list(params);
        ExportUtils<LogisticsAccountDO> exportUtils = new ExportUtils<LogisticsAccountDO>();
        exportUtils.exportFile(response, request, "物流台账", logisticsAccountList);
    }

    @GetMapping("/saleManage")
    @RequiresPermissions("factory:logisticsAccount:add")
    public String saleManage() {
        return "factory/logisticsAccount/saleManage";
    }

    @GetMapping("/purchaseOrder")
    @RequiresPermissions("factory:logisticsAccount:add")
    public String purchaseOrder() {
        return "factory/logisticsAccount/purchaseOrder";
    }
}