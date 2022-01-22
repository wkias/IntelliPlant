package com.bootdo.factory.reimburse.controller;

import java.io.IOException;
import java.util.*;

import com.bootdo.common.utils.*;
import com.bootdo.oa.domain.NotifyDO;
import com.bootdo.system.domain.UserDO;
import org.apache.shiro.SecurityUtils;
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

import com.bootdo.factory.reimburse.domain.LoanApplicationDO;
import com.bootdo.factory.reimburse.service.LoanApplicationService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 
 * @author zrj
 * @email 1992lcg@163.com
 * @date 2020-05-27 08:45:58
 */
 
@Controller
@RequestMapping("/reimburse/loanApplication")
public class LoanApplicationController {
	@Autowired
	private LoanApplicationService loanApplicationService;

	@GetMapping()
	@RequiresPermissions("reimburse:loanApplication:loanApplication")
	String LoanApplication(){
	    return "factory/reimburse/loanApplication/loanApplication";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("reimburse:loanApplication:loanApplication")
	public PageUtils list(@RequestParam Map<String, Object> params){
        if (params.get("queryAll").toString().equals("0")) {
            UserDO user = (UserDO) SecurityUtils.getSubject().getPrincipal();
            String userId = user.getUserId().toString();
            params.put("userId", userId);
        }


		//查询列表数据
        Query query = new Query(params);
		List<LoanApplicationDO> loanApplicationList = loanApplicationService.list(query);
		int total = loanApplicationService.count(query);
		PageUtils pageUtils = new PageUtils(loanApplicationList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("reimburse:loanApplication:add")
	String add(Model model){
        model.addAttribute("loanCode", UUID.randomUUID().toString());
	    return "factory/reimburse/loanApplication/add";
	}


    @GetMapping("/bankAccountList")
    @RequiresPermissions("reimburse:loanApplication:add")
    String contract(){
        return "factory/reimburse/loanApplication/bankAccountList";
    }



	@GetMapping("/edit/{loanApplicationId}")
	@RequiresPermissions("reimburse:loanApplication:edit")
	String edit(@PathVariable("loanApplicationId") String loanApplicationId,Model model){
		LoanApplicationDO loanApplication = loanApplicationService.get(loanApplicationId);
		model.addAttribute("loanApplication", loanApplication);
	    return "factory/reimburse/loanApplication/edit";
	}

    @GetMapping("/detail/{loanApplicationId}")
    @RequiresPermissions("reimburse:loanApplication:detail")
    String detail(@PathVariable("loanApplicationId") String loanApplicationId,Model model){
        LoanApplicationDO loanApplication = loanApplicationService.get(loanApplicationId);
        model.addAttribute("loanApplication", loanApplication);
        return "factory/reimburse/loanApplication/detail";
    }
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("reimburse:loanApplication:add")
	public R save(LoanApplicationDO loanApplication, MultipartFile filesFile, HttpServletRequest request)throws Exception{
		if (filesFile != null && !filesFile.isEmpty()) {
			String filesFileName = FileUtil.saveFiles(filesFile);
			loanApplication.setFileName(filesFileName);
		}
		Long userId = ShiroUtils.getUserId();
		loanApplication.setUserId(userId + "");
		loanApplication.setCreateUserId(userId + "");
		loanApplication.setCreateTime(new Date());
		loanApplication.setLoanDate(new Date());
		if(loanApplicationService.save(loanApplication)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("reimburse:loanApplication:edit")
	public R update( LoanApplicationDO loanApplication){
		loanApplicationService.update(loanApplication);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("reimburse:loanApplication:remove")
	public R remove( String loanApplicationId){
		if(loanApplicationService.remove(loanApplicationId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("reimburse:loanApplication:batchRemove")
	public R remove(@RequestParam("ids[]") String[] loanApplicationIds){
		loanApplicationService.batchRemove(loanApplicationIds);
		return R.ok();
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
            LoanApplicationDO loanApplication = loanApplicationService.list(map).get(0);
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + filesName);
            response.getOutputStream().write(FileUtil.getFilesBytes(filesName));
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	
}
