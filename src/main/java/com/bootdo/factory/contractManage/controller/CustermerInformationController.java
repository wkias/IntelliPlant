package com.bootdo.factory.contractManage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.*;
import com.bootdo.factory.contractManage.domain.CustermerContactPersonDO;
import com.bootdo.factory.contractManage.service.CustermerContactPersonService;
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

import com.bootdo.factory.contractManage.domain.CustermerInformationDO;
import com.bootdo.factory.contractManage.service.CustermerInformationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 
 * @author zrj
 * @email 1992lcg@163.com
 * @date 2020-03-06 10:20:56
 */
 
@Controller
@RequestMapping("/factory/custermerInformation")
public class CustermerInformationController {
	@Autowired
	private CustermerInformationService custermerInformationService;
    @Autowired
    private CustermerContactPersonService custermerContactPersonService;
	
	@GetMapping()
	@RequiresPermissions("factory:custermerInformation:custermerInformation")
	String CustermerInformation(){
	    return "factory/custermerInformation/custermerInformation";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("factory:custermerInformation:custermerInformation")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CustermerInformationDO> custermerInformationList = custermerInformationService.list(query);
		int total = custermerInformationService.count(query);
		PageUtils pageUtils = new PageUtils(custermerInformationList, total);
		return pageUtils;
	}
    @ResponseBody
    @GetMapping("/getDo/{id}")
    @RequiresPermissions("factory:custermerInformation:custermerInformation")
    public CustermerInformationDO getDo(@PathVariable String id){
        CustermerInformationDO custermerInformationDO = custermerInformationService.get(id);
        return custermerInformationDO;
    }

	
	@GetMapping("/add")
	@RequiresPermissions("factory:custermerInformation:add")
	String add(){
	    return "factory/custermerInformation/add";
	}

	@GetMapping("/edit/{custermerId}")
	@RequiresPermissions("factory:custermerInformation:edit")
	String edit(@PathVariable("custermerId") String custermerId,Model model){
		CustermerInformationDO custermerInformation = custermerInformationService.get(custermerId);

		model.addAttribute("custermerInformation", custermerInformation);
        System.out.println("custermerInformation:" + custermerInformation);
	    return "factory/custermerInformation/edit";
	}

	/**
	 * 详情
	 */
	@GetMapping("/details/{custermerId}")
	@RequiresPermissions("factory:custermerInformation:details")
	public String details(@PathVariable String custermerId, Model model) {
		//查询列表数据

		CustermerInformationDO custermerInformation = custermerInformationService.getWithNameType(custermerId);
		model.addAttribute("custermerInformation", custermerInformation);

		return "factory/custermerInformation/details";
	}




	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("factory:custermerInformation:add")
	public R save( CustermerInformationDO custermerInformation){
        Long userId = ShiroUtils.getUserId();
        custermerInformation.setCreateUserId(userId + "");
        custermerInformation.setCreateTime(new Date().getTime());
		if(custermerInformationService.save(custermerInformation)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("factory:custermerInformation:edit")
	public R update( CustermerInformationDO custermerInformation){
		custermerInformationService.update(custermerInformation);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("factory:custermerInformation:remove")
	public R remove( String custermerId){
		if(custermerInformationService.remove(custermerId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("factory:custermerInformation:batchRemove")
	public R remove(@RequestParam("ids[]") String[] custermerIds){
		custermerInformationService.batchRemove(custermerIds);
		return R.ok();
	}

    /**
     * 导出
     */
    @RequestMapping(value = "/exportFile")
    @RequiresPermissions("factory:custermerInformation:exportFile")
    public void Export(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ExportUtils<CustermerInformationDO> exportUtils = new ExportUtils<>();
//        Map<String,Object> query=new HashMap<>();
//        query.put("code",request.getParameter("code"));
//        query.put("name",request.getParameter("name"));
        List<CustermerInformationDO> custermerInformationList = custermerInformationService.list(params);
        System.out.println("params:");
        for (Map.Entry<String, Object> param : params.entrySet()) {
            System.out.println(param.getKey() + "-------" + param.getValue());
        }
        for (CustermerInformationDO custermer : custermerInformationList) {
            System.out.println("custermer:" + custermer);
        }
        exportUtils.exportFile(response, request, "bootdo导出文档", custermerInformationList);
    }

    /**
     * 联系人
     */
    @GetMapping("/person/{custermerId}")
    @RequiresPermissions("factory:custermerInformation:person")
    String person (@PathVariable("custermerId") String custermerId,Model model) {
        CustermerInformationDO custermerInformation = custermerInformationService.get(custermerId);
        model.addAttribute("custermerInformation", custermerInformation);
        return  "factory/custermerInformation/person";
    }

    @ResponseBody
    @GetMapping("/plist/{custermerId}")
    PageUtils plist(@PathVariable("custermerId")String custermerId,@RequestParam Map<String, Object> params) {
        params.put("custermerId",custermerId);
        Query query = new Query(params);
        /*query.put("custermerId", getCustermerId());*/
        System.out.println("params11111111111111111111111111111:" + params);
        return custermerInformationService.plist(query);
    }

    @GetMapping("/padd/{custermerId}")
    @RequiresPermissions("factory:custermerInformation:padd")
    String add(@PathVariable("custermerId") String custermerId,Model model){
        CustermerInformationDO custermerInformation = custermerInformationService.get(custermerId);
        model.addAttribute("custermerInformation", custermerInformation);

        return "factory/custermerInformation/padd";
    }
    @ResponseBody
    @PostMapping("/psave")
    @RequiresPermissions("factory:custermerInformation:padd")
    public R save( CustermerContactPersonDO custermerContactPerson){
        Long userId = ShiroUtils.getUserId();
        custermerContactPerson.setCreateUserId(userId + "");
        custermerContactPerson.setCreateTime(new Date().getTime());
        if(custermerContactPersonService.save(custermerContactPerson)>0){
            return R.ok();
        }
        return R.error();
    }

    @GetMapping("/pedit/{contactPersonId}")
    @RequiresPermissions("factory:custermerInformation:pedit")
    String pedit(/*@PathVariable("custermerId") String custermerId,*/@PathVariable("contactPersonId") String contactPersonId,Model model){
       /* model.addAttribute("custermerId", custermerId);*/
        CustermerContactPersonDO custermerContactPerson = custermerContactPersonService.get(contactPersonId);
        model.addAttribute("custermerContactPerson", custermerContactPerson);
        System.out.println("custermerContactPerson77777777777777"+custermerContactPerson);
        return "factory/custermerInformation/pedit";
    }
    @ResponseBody
    @RequestMapping("/pupdate")
    @RequiresPermissions("factory:custermerInformation:pedit")
    public R update( CustermerContactPersonDO custermerContactPerson){
        custermerContactPersonService.update(custermerContactPerson);
        return R.ok();
    }
    @PostMapping( "/pbatchRemove")
    @ResponseBody
    @RequiresPermissions("factory:custermerInformation:pbatchRemove")
    public R premove(@RequestParam("ids[]") String[] contactPersonIds){
        custermerContactPersonService.batchRemove(contactPersonIds);
        return R.ok();
    }
    /**
     * 导出
     */
    @RequestMapping(value = "/pexportFile")
    @RequiresPermissions("factory:custermerInformation:pexportFile")
    public void pexport(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ExportUtils<CustermerContactPersonDO> exportUtils = new ExportUtils<>();
//        Map<String,Object> query=new HashMap<>();
//        query.put("code",request.getParameter("code"));
//        query.put("name",request.getParameter("name"));
        List<CustermerContactPersonDO> custermerContactPersonList = custermerContactPersonService.list(params);
        System.out.println("params:");
        for (Map.Entry<String, Object> param : params.entrySet()) {
            System.out.println(param.getKey() + "-------" + param.getValue());
        }
        for (CustermerContactPersonDO custermerContactPerson : custermerContactPersonList) {
            System.out.println("custermerContactPerson:" + custermerContactPerson);
        }
        exportUtils.exportFile(response, request, "bootdo导出文档", custermerContactPersonList);
    }

}
