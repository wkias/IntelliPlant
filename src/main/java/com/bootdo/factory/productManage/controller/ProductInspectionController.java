package com.bootdo.factory.productManage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.bootdo.factory.productManage.dao.InspectionItemsDetailDao;
import com.bootdo.factory.productManage.domain.*;
import com.bootdo.system.domain.UserDO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.factory.productManage.service.ProductInspectionService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Casper
 * @email 1992lcg@163.com
 * @date 2020-03-26 11:13:40
 */

@Controller
@RequestMapping("/factory/productInspection")
public class ProductInspectionController {
    @Autowired
    private ProductInspectionService productInspectionService;
    @Autowired
    private InspectionItemsDetailDao inspectionItemsDetailDao;
    @Autowired
    private com.bootdo.factory.productManage.service.InspectionItemsService inspectionItemsService;

    @GetMapping()
    @RequiresPermissions("factory:productInspection:productInspection")
    String ProductInspection() {
        return "factory/productInspection/productInspection";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("factory:productInspection:productInspection")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<ProductInspectionDO> productInspectionList = productInspectionService.list(query);
        int total = productInspectionService.count(query);
        PageUtils pageUtils = new PageUtils(productInspectionList, total);
        for (ProductInspectionDO productInspection : productInspectionList) {
            Map<String, Object> promap = new HashMap<>();
            promap.put("productInspectionId", productInspection.getProductInspectionId());
            List<InspectionItemsDetailDO> inspectionItemsSelectDOS = inspectionItemsDetailDao.list(promap);
            for (InspectionItemsDetailDO inspectionItemsDetail : inspectionItemsSelectDOS) {
                InspectionItemsDO inspectionItems = inspectionItemsService.get(inspectionItemsDetail.getInspectionItemsId());
                productInspection.setInspectionItems(inspectionItems);
            }
        }
        return pageUtils;
    }

    @ResponseBody
    @GetMapping("/inspectionItemsList/{productInspectionId}")
    @RequiresPermissions("factory:productInspection:productInspection")
    public List<InspectionItemsDetailDO> list(@PathVariable("productInspectionId") String productInspectionId) {
        Map<String, Object> map = new HashMap<>();
        map.put("productInspectionId", productInspectionId);
        List<InspectionItemsDetailDO> inspectionItemsSelectDOS = inspectionItemsDetailDao.list(map);
        for (InspectionItemsDetailDO inspectionItemsDetail : inspectionItemsSelectDOS) {
            InspectionItemsDO inspectionItems = inspectionItemsService.get(inspectionItemsDetail.getInspectionItemsId());
            inspectionItemsDetail.setInspectionItems(inspectionItems);
        }
        return inspectionItemsSelectDOS;
    }

    @GetMapping("/add")
    @RequiresPermissions("factory:productInspection:add")
    String add() {
        return "factory/productInspection/add";
    }

    @GetMapping("/edit/{productInspectionId}")
    @RequiresPermissions("factory:productInspection:edit")
    String edit(@PathVariable("productInspectionId") String productInspectionId, Model model) {
        ProductInspectionDO productInspection = productInspectionService.get(productInspectionId);
        model.addAttribute("productInspection", productInspection);
        Map<String, Object> map = new HashMap<>();
        map.put("productInspectionId", productInspectionId);
        List<InspectionItemsDetailDO> inspectionItemsSelectDOS = inspectionItemsDetailDao.list(map);
        for (InspectionItemsDetailDO inspectionItemsDetail : inspectionItemsSelectDOS) {
            InspectionItemsDO inspectionItems = inspectionItemsService.get(inspectionItemsDetail.getInspectionItemsId());
            inspectionItemsDetail.setInspectionItems(inspectionItems);
        }
        model.addAttribute("inspectionItemsSelectDOS", inspectionItemsSelectDOS);
        return "factory/productInspection/edit";
    }

    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("factory:productInspection:add")
    public R save(ProductInspectionDO productInspection, HttpServletRequest request) {
        if (productInspectionService.save(productInspection, request) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("factory:productInspection:edit")
    public R update(ProductInspectionDO productInspection, HttpServletRequest request) {
        productInspectionService.update(productInspection, request);
        return R.ok();
    }

    @GetMapping("/details/{productInspectionId}")
    @RequiresPermissions("factory:productInspection:details")
    String details(@PathVariable("productInspectionId") String productInspectionId, Model model) {
        ProductInspectionDO productInspection = productInspectionService.get(productInspectionId);
        model.addAttribute("productInspection", productInspection);
        Map<String, Object> map = new HashMap<>();
        map.put("productInspectionId", productInspectionId);
        List<InspectionItemsDetailDO> inspectionItemsSelectDOS = inspectionItemsDetailDao.list(map);
        for (InspectionItemsDetailDO inspectionItemsDetail : inspectionItemsSelectDOS) {
            com.bootdo.factory.productManage.domain.InspectionItemsDO inspectionItems = inspectionItemsService.get(inspectionItemsDetail.getInspectionItemsId());
            inspectionItemsDetail.setInspectionItems(inspectionItems);
        }
        model.addAttribute("inspectionItemsSelectDOS", inspectionItemsSelectDOS);
        return "factory/productInspection/details";
    }

    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("factory:productInspection:remove")
    public R remove(String productInspectionId) {
        if (productInspectionService.remove(productInspectionId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("factory:productInspection:batchRemove")
    public R remove(@RequestParam("ids[]") String[] productInspectionIds) {
        productInspectionService.batchRemove(productInspectionIds);
        return R.ok();
    }

    @GetMapping("/inspectionItemsSelect")
    @RequiresPermissions("factory:productInspection:add")
    public String inspectionItemsSelect() {
        return "factory/productInspection/inspectionItemsSelect";
    }

    @GetMapping("productDefinition")
    @RequiresPermissions("factory:productInspection:add")
    public String productDefinition() {
        return "factory/productInspection/productDefinition";
    }
}
