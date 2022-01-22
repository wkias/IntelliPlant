package com.bootdo.factory.equipmentManage.controller;

import java.awt.image.BufferedImage;
import java.io.File;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.config.Constant;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.*;
import com.bootdo.factory.equipmentManage.domain.EquipmentManageDO;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.factory.equipmentManage.domain.CheckContentDO;
import com.bootdo.factory.equipmentManage.service.CheckContentService;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * 
 * @author xgq
 * @email 1992lcg@163.com
 * @date 2020-02-25 14:32:31
 */
 
@Controller
@RequestMapping("/factory/checkContent")
public class CheckContentController extends BaseController {
	private static String fileRootPath;
	@Autowired
	private CheckContentService checkContentService;
	@Autowired
	private DictService dictService;
	@Autowired
	private UserService userService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@PostConstruct
	private void init() {        //初始化文件根路径
		fileRootPath = bootdoConfig.getUploadPath();
	}

	@GetMapping()
	@RequiresPermissions("factory:checkContent:checkContent")
	String CheckContent(){
	    return "factory/checkContent/checkContent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("factory:checkContent:checkContent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CheckContentDO> checkContentList = checkContentService.list(query);
		if(checkContentList.size()!=0){
		for (CheckContentDO check: checkContentList) {
			String user = userService.get(Long.parseLong(check.getCreatUserId())).getName();
			check.setCreatUserId(user);
			String type = dictService.get(Long.parseLong(check.getEquipmentType())).getName();
			check.setEquipmentType(type);
		}
		}
		int total = checkContentService.count(query);
		PageUtils pageUtils = new PageUtils(checkContentList, total);
		return pageUtils;
	}
	@GetMapping("/tree")
	@ResponseBody
	public Tree<DictDO> tree() {
		Tree<DictDO> tree = new Tree<DictDO>();
		tree = checkContentService.getTree();
		return tree;
	}
	@GetMapping("/add")
	@RequiresPermissions("factory:checkContent:add")
	String add(){
	    return "factory/checkContent/add";
	}

	@GetMapping("/edit/{checkId}")
	@RequiresPermissions("factory:checkContent:edit")
	String edit(@PathVariable("checkId") int checkId,Model model){
		CheckContentDO checkContent = checkContentService.get(checkId);
		model.addAttribute("checkContent", checkContent);
		System.out.println(checkContent.getPhoto()+"photo更新");
		model.addAttribute("photoURL", "/factory/checkContent/photo/" + checkContent.getPhoto());

		return "factory/checkContent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("factory:checkContent:add")
	public R save(CheckContentDO checkContentDO, HttpSession session, MultipartFile photoFile, MultipartFile filesFile)throws Exception{
//		Long userId = ShiroUtils.getUserId();
//		checkContentDO.setCreateUserId(userId + "");
//		checkContentDO.setCreateTime(new Date().getTime());
		checkContentDO.setId(UUID.randomUUID().toString());
		System.out.println(getUser().getUserId()+"用户姓名");
		checkContentDO.setCreatUserId(getUserId().toString());
		Date date = new Date();
		checkContentDO.setCreatDate(new java.sql.Date(date.getTime()));
		//存储photo文件
		if (photoFile != null && !photoFile.isEmpty()) {
			String photoFileName = savePhoto(photoFile);
			checkContentDO.setPhoto(photoFileName);
		}
		if (checkContentService.save(checkContentDO) > 0) {
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("factory:checkContent:edit")
	public R update( CheckContentDO checkContent, MultipartFile photoFile)throws Exception{
		CheckContentDO oldEquipment = checkContentService.get(checkContent.getCheckId());
		String oldPhotoPath = fileRootPath + "photos/" + oldEquipment.getPhoto();

		//存储photo文件
		if (photoFile != null && !photoFile.isEmpty()) {
			String photoFileName = savePhoto(photoFile);
			checkContent.setPhoto(photoFileName);
			FileUtil.deleteFile(oldPhotoPath);//删除被替换的图片
		} else {
			System.out.println("no photo,pass");
		}
		//
		checkContentService.update(checkContent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("factory:checkContent:remove")
	public R remove( int checkId){
		if(checkContentService.remove(checkId)>0){
		return R.ok();
		}
		return R.error();
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
		System.out.println(photoName+"photoname");
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
		System.out.println("shangchuanwancheng");
		return photoFileName;
	}
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("factory:checkContent:batchRemove")
	public R remove(@RequestParam("ids[]") int[] checkIds){
		checkContentService.batchRemove(checkIds);
		return R.ok();
	}
	
}
