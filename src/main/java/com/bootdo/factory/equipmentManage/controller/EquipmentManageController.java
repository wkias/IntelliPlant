package com.bootdo.factory.equipmentManage.controller;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.*;
import com.bootdo.factory.equipmentManage.domain.EquipmentManageDO;
import com.bootdo.factory.equipmentManage.service.EquipmentManageService;
import com.bootdo.system.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-02-18 08:59:15
 */

@Controller
@RequestMapping("/factory/equipmentManage")
public class EquipmentManageController {
    private static String fileRootPath;
    @Autowired
    private EquipmentManageService equipmentManageService;
    @Autowired
    private UserService userService;
    @Autowired
    private BootdoConfig bootdoConfig;

    @PostConstruct
    private void init() {        //初始化文件根路径
        fileRootPath = bootdoConfig.getUploadPath();
    }

    @GetMapping()
    @RequiresPermissions("factory:equipmentManage:equipmentManage")
    String EquipmentManage() {
        return "factory/equipmentManage/equipmentManage";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("factory:equipmentManage:queryAll")
    public PageUtils list(@RequestParam Map<String, Object> params, HttpSession session) {
        //查询列表数据
        Query query = new Query(params);
        List<EquipmentManageDO> equipmentManageList = equipmentManageService.list(query);
        int total = equipmentManageService.count(query);
        PageUtils pageUtils = new PageUtils(equipmentManageList, total);
        return pageUtils;
    }
    @ResponseBody
    @GetMapping("/equipmentChart")
    @RequiresPermissions("factory:equipmentManage:queryAll")
    public Map equipmentChart(@RequestParam Map<String, Object> params, HttpSession session) {
        //查询列表数据
        Map<String,Integer> chartMap=new HashMap<>();
        List<EquipmentManageDO> equipmentManageList = equipmentManageService.list(new HashMap<>());
        for(EquipmentManageDO equipmentManage:equipmentManageList){
            String equipmentType=equipmentManage.getEquipmentType();
            if(!chartMap.containsKey(equipmentType)){
                chartMap.put(equipmentType,1);
            }else{
                int count=chartMap.get(equipmentType);
                chartMap.put(equipmentType,count+1);
            }
        }
        return chartMap;
    }
    /**
     * 详情
     */
    @GetMapping("/details/{equipmentId}")
    @RequiresPermissions("factory:equipmentManage:equipmentManage")
    public String details(@PathVariable String equipmentId, Model model) {
        //查询列表数据
        EquipmentManageDO equipmentManage = equipmentManageService.getWithNameType(equipmentId);
        model.addAttribute("equipmentManage", equipmentManage);
        model.addAttribute("photoURL", "/factory/equipmentManage/photo/" + equipmentManage.getPhoto());
        return "factory/equipmentManage/details";
    }

    @GetMapping("/add")
    @RequiresPermissions("factory:equipmentManage:add")
    String add() {
        return "factory/equipmentManage/add";
    }

    @GetMapping("/edit/{equipmentId}")
    @RequiresPermissions("factory:equipmentManage:edit")
    String edit(@PathVariable("equipmentId") String equipmentId, Model model) throws Exception {
        EquipmentManageDO equipmentManage = equipmentManageService.get(equipmentId);

//		Date date=equipmentManage.getProductionDate();
//		SimpleDateFormat sdf=new SimpleDateFormat("EEE yyyy-MM-dd");
//		date=sdf.parse(date.toString());
//		equipmentManage.setProductionDate(date);

        model.addAttribute("equipmentManage", equipmentManage);
        model.addAttribute("photoURL", "/factory/equipmentManage/photo/" + equipmentManage.getPhoto());
        System.out.println("equipmentMange:" + equipmentManage);
        return "factory/equipmentManage/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("factory:equipmentManage:add")
    public R save(EquipmentManageDO equipmentManage, HttpSession session, MultipartFile photoFile, MultipartFile filesFile) throws Exception {
        Long userId = ShiroUtils.getUserId();
        equipmentManage.setCreateUserId(userId + "");
        equipmentManage.setCreateTime(new Date().getTime());
        //存储photo文件
        if (photoFile != null && !photoFile.isEmpty()) {
            String photoFileName = savePhoto(photoFile);
            equipmentManage.setPhoto(photoFileName);
        }
        //存储files文件
        if (filesFile != null && !filesFile.isEmpty()) {
            String filesFileName = saveFiles(filesFile);
            equipmentManage.setFiles(filesFileName);
        }
        //
        if (equipmentManageService.save(equipmentManage) > 0) {
            return R.ok();
        }
        return R.error();
    }


    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("factory:equipmentManage:edit")
    public R update(EquipmentManageDO equipmentManage, MultipartFile photoFile, MultipartFile filesFile) throws Exception {
        EquipmentManageDO oldEquipment = equipmentManageService.get(equipmentManage.getEquipmentId());
        String oldPhotoPath = fileRootPath + "photos/" + oldEquipment.getPhoto();
        String oldFilePath = fileRootPath + "files/" + oldEquipment.getFiles();
        //存储photo文件
        if (photoFile != null && !photoFile.isEmpty()) {
            String photoFileName = savePhoto(photoFile);
            equipmentManage.setPhoto(photoFileName);
            FileUtil.deleteFile(oldPhotoPath);//删除被替换的图片
        } else {
            System.out.println("no photo,pass");
        }
        //存储files文件
        if (filesFile != null && !filesFile.isEmpty()) {
            String filesFileName = saveFiles(filesFile);
            equipmentManage.setFiles(filesFileName);
            FileUtil.deleteFile(oldFilePath);//删除被替换的文件
        } else {
            System.out.println("no file,pass");
        }
        //
        equipmentManageService.update(equipmentManage);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("factory:equipmentManage:remove")
    public R remove(String equipmentId) {
        EquipmentManageDO oldEquipment = equipmentManageService.get(equipmentId);
        String oldPhotoPath = fileRootPath + "photos/" + oldEquipment.getPhoto();
        String oldFilePath = fileRootPath + "files/" + oldEquipment.getFiles();
        if (equipmentManageService.remove(equipmentId) > 0) {
            FileUtil.deleteFile(oldPhotoPath);//删除图片
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
    @RequiresPermissions("factory:equipmentManage:batchRemove")
    public R remove(@RequestParam("ids[]") String[] equipmentIds) {
        for (String equipmentId : equipmentIds) {
            EquipmentManageDO oldEquipment = equipmentManageService.get(equipmentId);
            String oldPhotoPath = fileRootPath + "photos/" + oldEquipment.getPhoto();
            String oldFilePath = fileRootPath + "files/" + oldEquipment.getFiles();
            FileUtil.deleteFile(oldPhotoPath);//删除图片
            FileUtil.deleteFile(oldFilePath);//删除文件
        }

        equipmentManageService.batchRemove(equipmentIds);
        return R.ok();
    }

    /**
     * 获取图片
     */
    @GetMapping("/photo/{photoName}")
    @RequiresPermissions("factory:equipmentManage:equipmentManage")
    public void getPhoto(@PathVariable String photoName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (photoName == null) {
            return;
        }
        String photoPath = fileRootPath + "photos/";
        String photoURL = photoPath + photoName;
        File file = new File(photoURL);
        if (!file.exists()) {
            return;
        }
        BufferedImage photo = ImageIO.read(file);
        response.setContentType("image/*");
//		response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        ServletOutputStream sos = response.getOutputStream();
        if (photoName.split("\\.").length <= 1) {
            return;
        }
        ImageIO.write(photo, photoName.split("\\.")[1], sos);
        //request.getRequestDispatcher("file:///"+photoURL).forward(request,response);
        sos.close();
    }

    public String savePhoto(MultipartFile photoFile) throws Exception {
        //存储photo文件
        String photoFileName = photoFile.getOriginalFilename();
        photoFileName = FileUtil.renameToUUID(photoFileName);
        String photoFilePath = fileRootPath + "photos/";
        FileUtil.uploadFile(photoFile.getBytes(), photoFilePath, photoFileName);
        //String photoFileURL=photoFilePath+photoFileName;

        return photoFileName;
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
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + filesName);
            response.getOutputStream().write(getFilesBytes(filesName));
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出为excel
     */
    @GetMapping(value = "/exportFile")
    public void Export(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ExportUtils<EquipmentManageDO> exportUtils = new ExportUtils<>();
//        Map<String,Object> query=new HashMap<>();
//        query.put("code",request.getParameter("code"));
//        query.put("name",request.getParameter("name"));
        List<EquipmentManageDO> equipmentManageList = equipmentManageService.list(params);
        System.out.println("params:");
        for (Map.Entry<String, Object> param : params.entrySet()) {
            System.out.println(param.getKey() + "-------" + param.getValue());
        }
        for (EquipmentManageDO equipment : equipmentManageList) {
            System.out.println("equipment:" + equipment);
        }
        exportUtils.exportFile(response, request, "bootdo导出文档", equipmentManageList);
    }
}
