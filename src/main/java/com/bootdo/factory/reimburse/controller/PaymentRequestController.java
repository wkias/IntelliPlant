package com.bootdo.factory.reimburse.controller;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.*;
import com.bootdo.factory.reimburse.domain.PaymentRequestDO;
import com.bootdo.factory.reimburse.service.PaymentRequestService;
import com.bootdo.system.domain.UserDO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Casper
 * @email 1992lcg@163.com
 * @date 2020-06-02 09:26:01
 */

@Controller
@RequestMapping("/factory/paymentRequest")
public class PaymentRequestController {
    private static String fileRootPath;


    @Autowired
    private PaymentRequestService paymentRequestService;
    @Autowired
    private BootdoConfig bootdoConfig;

    @PostConstruct
    private void init() {
        fileRootPath = bootdoConfig.getUploadPath();
    }

    @GetMapping()
    @RequiresPermissions("factory:paymentRequest:paymentRequest")
    String PaymentRequest() {
        return "factory/paymentRequest/paymentRequest";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("factory:paymentRequest:paymentRequest")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        if (params.get("queryAll").toString().equals("0")) {
            UserDO user = (UserDO) SecurityUtils.getSubject().getPrincipal();
            String userId = user.getUserId().toString();
            params.put("userId", userId);
        }
        //查询列表数据
        Query query = new Query(params);
        List<PaymentRequestDO> paymentRequestList = paymentRequestService.list(query);
        int total = paymentRequestService.count(query);
        PageUtils pageUtils = new PageUtils(paymentRequestList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("factory:paymentRequest:add")
    String add(Model model) {
        model.addAttribute("uuid", UUID.randomUUID().toString());
        return "factory/paymentRequest/add";
    }

    @GetMapping("/edit/{paymentRequestId}")
    @RequiresPermissions("factory:paymentRequest:edit")
    String edit(@PathVariable("paymentRequestId") String paymentRequestId, Model model) {
        PaymentRequestDO paymentRequest = paymentRequestService.get(paymentRequestId);
        model.addAttribute("paymentRequest", paymentRequest);
        return "factory/paymentRequest/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("factory:paymentRequest:add")
    public R save(PaymentRequestDO paymentRequest, HttpSession session, MultipartFile filesFile) throws Exception {
        if (filesFile != null && !filesFile.isEmpty()) {
            String filesFileName = saveFiles(filesFile);
            paymentRequest.setFiles(filesFileName);
        }
        Long userId = ShiroUtils.getUserId();
        paymentRequest.setUserId(userId + "");
        paymentRequest.setCreateUserId(userId + "");
        paymentRequest.setCreateTime(new Date());
        paymentRequest.setIsDeleted(false);
        if (paymentRequestService.save(paymentRequest) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("factory:paymentRequest:edit")
    public R update(PaymentRequestDO paymentRequest) {
        paymentRequestService.update(paymentRequest);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("factory:paymentRequest:remove")
    public R remove(String paymentRequestId) {
        if (paymentRequestService.remove(paymentRequestId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("factory:paymentRequest:batchRemove")
    public R remove(@RequestParam("ids[]") String[] paymentRequestIds) {
        for (String paymentRequestId : paymentRequestIds) {
            PaymentRequestDO oldEquipment = paymentRequestService.get(paymentRequestId);
            String oldFilePath = fileRootPath + "files/" + oldEquipment.getFiles();
            FileUtil.deleteFile(oldFilePath);//删除文件
        }
        paymentRequestService.batchRemove(paymentRequestIds);
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

    @GetMapping("/supplierInformation")
    @RequiresPermissions("factory:paymentRequest:add")
    public String supplierInformation() {
        return "factory/paymentRequest/supplierInformation";
    }

    @GetMapping("/details/{paymentRequestId}")
    @RequiresPermissions("factory:paymentRequest:details")
    String details(@PathVariable("paymentRequestId") String paymentRequestId, Model model) {
        PaymentRequestDO paymentRequest = paymentRequestService.get(paymentRequestId);
        model.addAttribute("paymentRequest", paymentRequest);
        return "factory/paymentRequest/details";
    }
}
