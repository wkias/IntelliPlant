package com.bootdo.oa.controller;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.config.Constant;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.factory.equipmentManage.domain.EquipmentManageDO;
import com.bootdo.oa.domain.NotifyDO;
import com.bootdo.oa.domain.NotifyRecordDO;
import com.bootdo.oa.service.NotifyRecordService;
import com.bootdo.oa.service.NotifyService;
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
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通知通告
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-05 17:11:16
 */

@Controller
@RequestMapping("/oa/notify")
public class NotifyController extends BaseController {
    private static String fileRootPath;
    @Autowired
    private NotifyService notifyService;
    @Autowired
    private NotifyRecordService notifyRecordService;
    @Autowired
    private DictService dictService;
    @Autowired
    private BootdoConfig bootdoConfig;

    @PostConstruct
    private void init() {        //初始化文件根路径
        fileRootPath = bootdoConfig.getUploadPath();
    }

    @GetMapping()
    @RequiresPermissions("oa:notify:notify")
    String oaNotify() {
        return "oa/notify/notify";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("oa:notify:notify")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<NotifyDO> notifyList = notifyService.list(query);
        int total = notifyService.count(query);
        PageUtils pageUtils = new PageUtils(notifyList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("oa:notify:add")
    String add() {
        return "oa/notify/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("oa:notify:edit")
    String edit(@PathVariable("id") Long id, Model model) {
        NotifyDO notify = notifyService.get(id);
        List<DictDO> dictDOS = dictService.listByType("oa_notify_type");
        String type = notify.getType();
        for (DictDO dictDO:dictDOS){
            if(type.equals(dictDO.getValue())){
                dictDO.setRemarks("checked");
            }
        }
        model.addAttribute("oaNotifyTypes",dictDOS);
        model.addAttribute("notify", notify);
        return "oa/notify/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("oa:notify:add")
    public R save(NotifyDO notify, HttpSession session, MultipartFile filesFile) throws Exception {
        //存储files文件
        notify.setCreateBy(getUserId());
        if (filesFile != null && !filesFile.isEmpty()) {
            String filesFileName = saveFiles(filesFile);
            notify.setFiles(filesFileName);
            System.out.println("filesFileName"+filesFileName);
        }


        if (notifyService.save(notify) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("oa:notify:edit")
    public R update(NotifyDO notify , MultipartFile filesFile) throws Exception {
        NotifyDO oldNotify = notifyService.get(notify.getId());
        String oldFilePath = fileRootPath + "files/" + oldNotify.getFiles();
        //存储files文件
        if (filesFile != null && !filesFile.isEmpty()) {
            String filesFileName = saveFiles(filesFile);
            notify.setFiles(filesFileName);
            FileUtil.deleteFile(oldFilePath);//删除被替换的文件
        } else {
            System.out.println("no file,pass");
        }
        /*if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }*/
        notifyService.update(notify);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("oa:notify:remove")
    public R remove(Long id) {
        NotifyDO oldNotify = notifyService.get(id);
        String oldFilePath = fileRootPath + "files/" + oldNotify.getFiles();
       /* if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }*/

        if (notifyService.remove(id) > 0) {
            FileUtil.deleteFile(oldFilePath);//删除文件
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("oa:notify:batchRemove")
    public R remove(@RequestParam("ids[]") Long[] ids) {
        for (Long id : ids) {
            NotifyDO oldNotify = notifyService.get(id);

            String oldFilePath = fileRootPath + "files/" + oldNotify.getFiles();

            FileUtil.deleteFile(oldFilePath);//删除文件
        }
        /*if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }*/
        notifyService.batchRemove(ids);
        return R.ok();
    }

    @ResponseBody
    @GetMapping("/message")
    PageUtils message() {
        Map<String, Object> params = new HashMap<>(16);
        params.put("offset", 0);
        params.put("limit", 3);
        Query query = new Query(params);
        query.put("userId", getUserId());
        query.put("isRead",Constant.OA_NOTIFY_READ_NO);
        return notifyService.selfList(query);
    }

    @GetMapping("/selfNotify")
    String selfNotify() {
        return "oa/notify/selfNotify";
    }

    @ResponseBody
    @GetMapping("/selfList")
    PageUtils selfList(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        query.put("userId", getUserId());

        return notifyService.selfList(query);
    }

    @GetMapping("/read/{id}")
    @RequiresPermissions("oa:notify:edit")
    String read(@PathVariable("id") Long id, Model model) {
        NotifyDO notify = notifyService.get(id);
        //更改阅读状态
        NotifyRecordDO notifyRecordDO = new NotifyRecordDO();
        notifyRecordDO.setNotifyId(id);
        notifyRecordDO.setUserId(getUserId());
        notifyRecordDO.setReadDate(new Date());
        notifyRecordDO.setIsRead(Constant.OA_NOTIFY_READ_YES);
        notifyRecordService.changeRead(notifyRecordDO);
        model.addAttribute("notify", notify);
        return "oa/notify/read";
    }

    /**
     * 存储文件
     */
    private String saveFiles(MultipartFile filesFile) throws Exception {
        String filesFileName = filesFile.getOriginalFilename();
        filesFileName = FileUtil.renameToUUID(filesFileName);
        String filesFilePath = fileRootPath + "files/";
        FileUtil.uploadFile(filesFile.getBytes(), filesFilePath, filesFileName);
        return filesFileName;
    }
    /**
     * 获取文件二进制数组
     */
    private byte[] getFilesBytes(String filesName) throws Exception {
        String filePath = fileRootPath + "files/" + filesName;
        byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
        return fileBytes;
    }
    /**
     * 附件下载
     */
    @GetMapping("/files/{filesName}")
    public void filesDownload(@PathVariable("filesName") String filesName, HttpServletResponse response) throws Exception {

        try {
            if (filesName == null || filesName == "") {
                return;
            }
            Map<String, Object> map = new HashMap<>();
            map.put("files", filesName);
            NotifyDO notify = notifyService.list(map).get(0);
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + filesName);
            response.getOutputStream().write(getFilesBytes(filesName));
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
