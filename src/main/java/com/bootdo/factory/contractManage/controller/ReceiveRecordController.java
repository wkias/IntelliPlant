package com.bootdo.factory.contractManage.controller;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.*;
import com.bootdo.factory.contractManage.domain.ReceiveRecordDO;
import com.bootdo.factory.contractManage.service.ReceiveRecordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author willice
 * @email 2782546116@qq.com
 * @date 2020-03-07 16:38:35
 */

@Controller
@RequestMapping("/factory/receiveRecord")
public class ReceiveRecordController {
    private static String fileRootPath;
    @Autowired
    private ReceiveRecordService receiveRecordService;
    @Autowired
    private BootdoConfig bootdoConfig;

    @PostConstruct
    private void init() {
        fileRootPath = bootdoConfig.getUploadPath();
    }

    @GetMapping()
    @RequiresPermissions("factory:receiveRecord:receiveRecord")
    String ReceiveRecord() {
        return "factory/receiveRecord/receiveRecord";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("factory:receiveRecord:receiveRecord")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<ReceiveRecordDO> receiveRecordList = receiveRecordService.list(query);
        int total = receiveRecordService.count(query);
        PageUtils pageUtils = new PageUtils(receiveRecordList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("factory:receiveRecord:add")
    String add() {
        return "factory/receiveRecord/add";
    }

    @GetMapping("/edit/{recordId}")
    @RequiresPermissions("factory:receiveRecord:edit")
    String edit(@PathVariable("recordId") String recordId, Model model) {
        ReceiveRecordDO receiveRecord = receiveRecordService.get(recordId);
        model.addAttribute("receiveRecord", receiveRecord);
        return "factory/receiveRecord/edit";
    }

    @GetMapping("/detail/{recordId}")
    @RequiresPermissions("factory:receiveRecord:receiveRecord")
    String detail(@PathVariable("recordId") String recordId, Model model) {
        ReceiveRecordDO receiveRecord = receiveRecordService.get(recordId);
        model.addAttribute("receiveRecord", receiveRecord);
        return "factory/receiveRecord/detail";
    }

    /**
     * ??????
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("factory:receiveRecord:add")
    public R save(ReceiveRecordDO receiveRecord, MultipartFile file) throws Exception {
        receiveRecord.setCreateUserId(ShiroUtils.getUserId());
        receiveRecord.setCreateTime(new Timestamp(new Date().getTime()));
        receiveRecord.setIsDeleted(false);
        if (file != null && !file.isEmpty()) {
            receiveRecord.setFile(saveFiles(file));
        }
        if (receiveRecordService.save(receiveRecord) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * ??????
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("factory:receiveRecord:edit")
    public R update(ReceiveRecordDO receiveRecord, MultipartFile file) throws Exception {
        if (file != null && !file.isEmpty()) {
            receiveRecord.setFile(saveFiles(file));
        }
        receiveRecordService.update(receiveRecord);
        return R.ok();
    }

    /**
     * ????????????
     */
    private String saveFiles(MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        fileName = FileUtil.renameToUUID(fileName);
        String filesPath = fileRootPath + "files/";
        FileUtil.uploadFile(file.getBytes(), filesPath, fileName);
        return fileName;
    }

    /**
     * ???????????????????????????
     */
    private byte[] getFilesBytes(String filesName) throws Exception {
        String filePath = fileRootPath + "files/" + filesName;
        return Files.readAllBytes(Paths.get(filePath));
    }

    /**
     * ????????????
     */
    @GetMapping("/file/{fileName}")
    public void fileDownload(@PathVariable("fileName") String fileName, HttpServletResponse response) throws Exception {

        try {
            if (fileName == null || fileName.equals("")) {
                return;
            }
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.getOutputStream().write(getFilesBytes(fileName));
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ??????
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("factory:receiveRecord:remove")
    public R remove(String recordId) {
        if (receiveRecordService.remove(recordId) > 0) {
            String filePath = fileRootPath + "files/" + receiveRecordService.get(recordId).getFile();
            FileUtil.deleteFile(filePath);//????????????
            return R.ok();
        }
        return R.error();
    }

    /**
     * ??????
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("factory:receiveRecord:batchRemove")
    public R remove(@RequestParam("ids[]") String[] recordIds) {
        for (String recordId : recordIds) {
            FileUtil.deleteFile(fileRootPath + "files/" + receiveRecordService.get(recordId).getFile());
        }
        receiveRecordService.batchRemove(recordIds);
        return R.ok();
    }

    /**
     * ??????
     */
    @GetMapping("/export")
    @RequiresPermissions("factory:receiveRecord:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ExportUtils<ReceiveRecordDO> exportUtils = new ExportUtils<>();
        List<ReceiveRecordDO> receiveRecordDOList = receiveRecordService.list(params);
        exportUtils.exportFile(response, request, "????????????", receiveRecordDOList);
    }

    @GetMapping("/contractSelect")
    @RequiresPermissions("factory:receiveRecord:add")
    public String contractSelect() {
        return "factory/receiveRecord/contractSelect";
    }

}
