package com.bootdo.factory.contractManage.controller;

import com.bootdo.common.utils.*;
import com.bootdo.factory.contractManage.domain.CustermerContactPersonDO;
import com.bootdo.factory.contractManage.domain.CustermerInformationDO;
import com.bootdo.factory.contractManage.service.CustermerContactPersonService;
import com.bootdo.factory.contractManage.service.CustermerInformationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 此Controller于Custermer业务逻辑相同，调用的Service相同，但SevletMapping不同，Shiro权限不同
 * */
@Controller
@RequestMapping("/factory/supplierInformation")
public class SupplierInformationController {
    @Autowired
    private CustermerInformationService custermerInformationService;
    @Autowired
    private CustermerContactPersonService custermerContactPersonService;

    @GetMapping()
    @RequiresPermissions("factory:supplierInformation:supplierInformation")
    String CustermerInformation(){
        return "factory/supplierInformation/supplierInformation";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("factory:supplierInformation:supplierInformation")
    public PageUtils list(@RequestParam Map<String, Object> params){
        //查询列表数据
        params.put("isSupplier",true);//获取供应商
        Query query = new Query(params);
        List<CustermerInformationDO> custermerInformationList = custermerInformationService.list(query);
        int total = custermerInformationService.count(query);
        PageUtils pageUtils = new PageUtils(custermerInformationList, total);
        return pageUtils;
    }


    @GetMapping("/add")
    @RequiresPermissions("factory:supplierInformation:add")
    String add(Model model){
        model.addAttribute("isSupplier",true);
        return "factory/supplierInformation/add";
    }

    @GetMapping("/edit/{custermerId}")
    @RequiresPermissions("factory:supplierInformation:edit")
    String edit(@PathVariable("custermerId") String custermerId,Model model){
        CustermerInformationDO custermerInformation = custermerInformationService.get(custermerId);

        model.addAttribute("supplierInformation", custermerInformation);
        System.out.println("custermerInformation:" + custermerInformation);
        return "factory/supplierInformation/edit";
    }

    /**
     * 详情
     */
    @GetMapping("/details/{custermerId}")
    @RequiresPermissions("factory:supplierInformation:details")
    public String details(@PathVariable String custermerId, Model model) {
        //查询列表数据

        CustermerInformationDO custermerInformation = custermerInformationService.getWithNameType(custermerId);
        model.addAttribute("supplierInformation", custermerInformation);

        return "factory/supplierInformation/details";
    }




    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("factory:supplierInformation:add")
    public R save( CustermerInformationDO custermerInformation){
        Long userId = ShiroUtils.getUserId();
        custermerInformation.setCreateUserId(userId + "");
        custermerInformation.setCreateTime(new Date().getTime());
        custermerInformation.setIsSupplier(true);//设置为供应商
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
    @RequiresPermissions("factory:supplierInformation:edit")
    public R update( CustermerInformationDO custermerInformation){
        custermerInformationService.update(custermerInformation);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping( "/remove")
    @ResponseBody
    @RequiresPermissions("factory:supplierInformation:remove")
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
    @RequiresPermissions("factory:supplierInformation:batchRemove")
    public R remove(@RequestParam("ids[]") String[] custermerIds){
        custermerInformationService.batchRemove(custermerIds);
        return R.ok();
    }

    /**
     * 导出
     */
    @RequestMapping(value = "/exportFile")
    @RequiresPermissions("factory:supplierInformation:exportFile")
    public void Export(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ExportUtils<CustermerInformationDO> exportUtils = new ExportUtils<>();
//        Map<String,Object> query=new HashMap<>();
//        query.put("code",request.getParameter("code"));
//        query.put("name",request.getParameter("name"));
        params.put("isSupplier",true);//获取供应商
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
    @RequiresPermissions("factory:supplierInformation:person")
    String person (@PathVariable("custermerId") String custermerId,Model model) {
        CustermerInformationDO custermerInformation = custermerInformationService.get(custermerId);
        model.addAttribute("supplierInformation", custermerInformation);
        return  "factory/supplierInformation/person";
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
    @RequiresPermissions("factory:supplierInformation:padd")
    String add(@PathVariable("custermerId") String custermerId,Model model){
        CustermerInformationDO custermerInformation = custermerInformationService.get(custermerId);
        model.addAttribute("supplierInformation", custermerInformation);

        return "factory/supplierInformation/padd";
    }
    @ResponseBody
    @PostMapping("/psave")
    @RequiresPermissions("factory:supplierInformation:padd")
    public R save( CustermerContactPersonDO custermerContactPerson){
        Long userId = ShiroUtils.getUserId();
        custermerContactPerson.setCreateUserId(userId + "");
        custermerContactPerson.setCreateTime(new Date().getTime());
        if(custermerContactPersonService.save(custermerContactPerson)>0){
            return R.ok();
        }
        return R.error("已经有主联系人了！");
    }

    @GetMapping("/pedit/{contactPersonId}")
    @RequiresPermissions("factory:supplierInformation:pedit")
    String pedit(/*@PathVariable("custermerId") String custermerId,*/@PathVariable("contactPersonId") String contactPersonId,Model model){
        /* model.addAttribute("custermerId", custermerId);*/
        CustermerContactPersonDO custermerContactPerson = custermerContactPersonService.get(contactPersonId);
        model.addAttribute("custermerContactPerson", custermerContactPerson);
        System.out.println("custermerContactPerson77777777777777"+custermerContactPerson);
        return "factory/supplierInformation/pedit";
    }
    @ResponseBody
    @RequestMapping("/pupdate")
    @RequiresPermissions("factory:supplierInformation:pedit")
    public R update( CustermerContactPersonDO custermerContactPerson){
        custermerContactPersonService.update(custermerContactPerson);
        return R.ok();
    }
    @PostMapping( "/pbatchRemove")
    @ResponseBody
    @RequiresPermissions("factory:supplierInformation:pbatchRemove")
    public R premove(@RequestParam("ids[]") String[] contactPersonIds){
        custermerContactPersonService.batchRemove(contactPersonIds);
        return R.ok();
    }
    /**
     * 导出
     */
    @RequestMapping(value = "/pexportFile")
    @RequiresPermissions("factory:supplierInformation:pexportFile")
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
