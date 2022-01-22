package com.bootdo.factory.contractManage.controller;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.*;
import com.bootdo.factory.contractManage.domain.InvoiceRecordDO;
import com.bootdo.factory.contractManage.service.InvoiceRecordService;
import com.bootdo.factory.domain.BillContentDO;
import com.bootdo.factory.productManage.domain.InspectionItemsDO;
import com.bootdo.factory.domain.BillContentDO;
import com.bootdo.factory.productManage.domain.InspectionItemsDetailDO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Casper
 * @email 1992lcg@163.com
 * @date 2020-03-08 16:33:30
 */

@Controller
@RequestMapping("/factory/invoiceRecord")
public class InvoiceRecordController {
    private static String fileRootPath;
    @Autowired
    private InvoiceRecordService invoiceRecordService;
    @Autowired
    private BootdoConfig bootdoConfig;
    @Autowired
    private com.bootdo.factory.dao.BillContentDao billContentDao;

    @PostConstruct
    private void init() {
        fileRootPath = bootdoConfig.getUploadPath();
    }

    @GetMapping()
    @RequiresPermissions("factory:invoiceRecord:invoiceRecord")
    String InvoiceRecord() {
        return "factory/invoiceRecord/invoiceRecord";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("factory:invoiceRecord:invoiceRecord")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<InvoiceRecordDO> invoiceRecordList = invoiceRecordService.list(query);
        int total = invoiceRecordService.count(query);
        PageUtils pageUtils = new PageUtils(invoiceRecordList, total);
        for (InvoiceRecordDO invoiceRecord : invoiceRecordList) {
            Map<String, Object> promap = new HashMap<>();
            promap.put("invoiceId", invoiceRecord.getInvoiceId());
            List<BillContentDO> billContentDOS = billContentDao.list(promap);
            for (BillContentDO billContent : billContentDOS) {
                invoiceRecord.setBillContent(billContent);
        }
        }

        return pageUtils;
    }

    @ResponseBody
    @GetMapping("/billContentList/{invoiceId}")
    @RequiresPermissions("factory:invoiceRecord:invoiceRecord")
    public List<BillContentDO> list(@PathVariable("invoiceId") String invoiceId) {
        Map<String, Object> map = new HashMap<>();
        map.put("invoiceId", invoiceId);
        List<BillContentDO> billContentDOS = billContentDao.list(map);
        return billContentDOS;
    }

    @GetMapping("/add")
    @RequiresPermissions("factory:invoiceRecord:add")
    String add() {
        return "factory/invoiceRecord/add";
    }

    @GetMapping("/edit/{invoiceId}")
    @RequiresPermissions("factory:invoiceRecord:edit")
    String edit(@PathVariable("invoiceId") String invoiceId, Model model) {
        InvoiceRecordDO invoiceRecord = invoiceRecordService.get(invoiceId);
        model.addAttribute("invoiceRecord", invoiceRecord);
        Map<String, Object> map = new HashMap<>();
        map.put("invoiceId", invoiceId);
        List<BillContentDO> billContentDOS = billContentDao.list(map);
        for (BillContentDO billContent : billContentDOS) {
            invoiceRecord.setBillContent(billContent);
        }
        model.addAttribute("billContentDOS", billContentDOS);
        return "factory/invoiceRecord/edit";
    }


    /**
     * 保存
     *
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("factory:invoiceRecord:add")
    public R save(InvoiceRecordDO invoiceRecord, HttpSession session,MultipartFile filesFile,HttpServletRequest request)throws  Exception{
        if (filesFile != null && !filesFile.isEmpty()) {
            String filesFileName = saveFiles(filesFile);
            invoiceRecord.setFiles(filesFileName);
        }
        if (invoiceRecordService.save(invoiceRecord, request) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("factory:invoiceRecord:edit")
    public R update(InvoiceRecordDO invoiceRecord,HttpServletRequest request) {
        invoiceRecordService.update(invoiceRecord, request);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("factory:invoiceRecord:remove")
    public R remove(String invoiceId) {
        if (invoiceRecordService.remove(invoiceId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("factory:invoiceRecord:batchRemove")
    public R remove(@RequestParam("ids[]") String[] invoiceIds) {
        for (String invoiceId : invoiceIds) {
            InvoiceRecordDO oldEquipment = invoiceRecordService.get(invoiceId);
            String oldFilePath = fileRootPath + "files/" + oldEquipment.getFiles();
            FileUtil.deleteFile(oldFilePath);//删除文件
        }
        invoiceRecordService.batchRemove(invoiceIds);
        return R.ok();
    }

    /**
     * 存储文件
     */
    private String saveFiles(MultipartFile files) throws Exception {
        String filesName = files.getOriginalFilename();
        filesName = FileUtil.renameToUUID(filesName);
        String filesPath = fileRootPath + "files/";
        FileUtil.uploadFile(files.getBytes(), filesPath, filesName);
        return filesName;
    }

    /**
     * 获取文件二进制数组
     */
    private byte[] getFilesBytes(String filesName) throws Exception {
        String filesPath = fileRootPath + "files/" + filesName;
        byte[] filesBytes = Files.readAllBytes(Paths.get(filesPath));
        return filesBytes;
    }

    /**
     * 附件下载
     */
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
    @RequiresPermissions("factory:invoiceRecord:exportFile")
    public void Export(HttpServletResponse response, HttpServletRequest request) throws Exception {
        Map<String, Object> params = new HashMap<>();
        String invoiceName = request.getParameter("name");
        String invoiceType = request.getParameter("searchType");
        params.put("name", invoiceName);
        params.put("searchType", invoiceType);
        List<InvoiceRecordDO> invoiceRecordList = invoiceRecordService.list(params);
        ExportUtils<InvoiceRecordDO> exportUtils = new ExportUtils<InvoiceRecordDO>();
        exportUtils.exportFile(response, request, "开票记录", invoiceRecordList);
    }

    @GetMapping("/contractSelect")
    @RequiresPermissions("factory:invoiceRecord:add")
    public String contractSelect() {
        return "factory/invoiceRecord/contractSelect";
    }

    @GetMapping("/custermerInformation")
    @RequiresPermissions("factory:invoiceRecord:add")
    public String custermerInformation(){
        return  "factory/invoiceRecord/custermerInformation";
    }

    @GetMapping("/supplierInformation")
    @RequiresPermissions("factory:invoiceRecord:add")
    public String supplierInformation(){
        return  "factory/invoiceRecord/supplierInformation";
    }

    @GetMapping("/details/{invoiceId}")
    @RequiresPermissions("factory:invoiceRecord:details")
    String details(@PathVariable("invoiceId") String invoiceId, Model model) {
        InvoiceRecordDO invoiceRecord = invoiceRecordService.get(invoiceId);
        model.addAttribute("invoiceRecord", invoiceRecord);
        Map<String, Object> map = new HashMap<>();
        map.put("invoiceId", invoiceId);
        List<BillContentDO> billContentDOS = billContentDao.list(map);
        for (BillContentDO billContent : billContentDOS) {
            invoiceRecord.setBillContent(billContent);
        }
        model.addAttribute("billContentDOS", billContentDOS);
        return "factory/invoiceRecord/details";
    }
}
