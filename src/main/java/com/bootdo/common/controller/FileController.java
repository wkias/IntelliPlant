package com.bootdo.common.controller;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.*;
import com.bootdo.factory.equipmentManage.domain.EquipmentManageDO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件上传
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-19 16:02:20
 */
@Controller
@RequestMapping("/common/sysFile")
public class FileController extends BaseController {

    @Autowired
    private FileService sysFileService;

    @Autowired
    private BootdoConfig bootdoConfig;

    @GetMapping()
    @RequiresPermissions("common:sysFile:sysFile")
    String sysFile(Model model) {
        Map<String, Object> params = new HashMap<>(16);
        return "common/file/file";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("common:sysFile:sysFile")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<FileDO> sysFileList = sysFileService.list(query);
        int total = sysFileService.count(query);
        PageUtils pageUtils = new PageUtils(sysFileList, total);
        return pageUtils;
    }

    @GetMapping("/add")
        // @RequiresPermissions("common:bComments")
    String add() {
        return "common/sysFile/add";
    }

    @GetMapping("/edit")
        // @RequiresPermissions("common:bComments")
    String edit(Long id, Model model) {
        FileDO sysFile = sysFileService.get(id);
        model.addAttribute("sysFile", sysFile);
        return "common/sysFile/edit";
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("common:info")
    public R info(@PathVariable("id") Long id) {
        FileDO sysFile = sysFileService.get(id);
        return R.ok().put("sysFile", sysFile);
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("common:save")
    public R save(FileDO sysFile) {
        if (sysFileService.save(sysFile) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("common:update")
    public R update(@RequestBody FileDO sysFile) {
        sysFileService.update(sysFile);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    // @RequiresPermissions("common:remove")
    public R remove(Long id, HttpServletRequest request) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        String fileName = bootdoConfig.getUploadPath() + sysFileService.get(id).getUrl().replace("/files/", "");
        if (sysFileService.remove(id) > 0) {
            boolean b = FileUtil.deleteFile(fileName);
            if (!b) {
                return R.error("数据库记录删除成功，文件删除失败");
            }
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("common:remove")
    public R remove(@RequestParam("ids[]") Long[] ids) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        sysFileService.batchRemove(ids);
        return R.ok();
    }

    @ResponseBody
    @PostMapping("/upload")
    R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        String fileName = file.getOriginalFilename();
        fileName = FileUtil.renameToUUID(fileName);
        FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
        try {
            FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
        } catch (Exception e) {
            return R.error();
        }

        if (sysFileService.save(sysFile) > 0) {
            return R.ok().put("fileName", sysFile.getUrl());
        }
        return R.error();
    }
    /**
     * （公用）附件上传
     * */
    @ResponseBody
    @RequestMapping("/fileUpload")
    public R fileUpload(MultipartFile file,String fileName){
        if (file != null && !file.isEmpty()) {
            String oldFilePath = FileUtil.fileRootPath + "files/" +fileName;
            String newFileName="";
            try {
                if(StringUtils.isNotBlank(fileName)){
                    FileUtil.deleteFile(oldFilePath);//删除被替换的文件
                }
                newFileName = FileUtil.saveFiles(file);
            }catch (Exception e){
                e.printStackTrace();
                return R.error();
            }
            return R.ok().put("fileName", newFileName);
        } else {
            System.out.println("no file,pass");
            return R.error("没有发现文件");
        }

    }
    /**
     * （公用）附件下载(根据文件名)
     */
    @GetMapping("/files/{filesName}")
    public void filesDownload(@PathVariable("filesName") String filesName, HttpServletResponse response) throws Exception {

        try {
            if (filesName == null || filesName == "") {
                return;
            }
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + filesName);
            response.getOutputStream().write(FileUtil.getFilesBytes(filesName));
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
