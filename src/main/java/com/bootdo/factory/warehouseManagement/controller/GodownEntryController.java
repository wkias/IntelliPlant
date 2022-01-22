package com.bootdo.factory.warehouseManagement.controller;

import com.bootdo.common.utils.*;
import com.bootdo.factory.productManage.domain.ProductDetailDO;
import com.bootdo.factory.warehouseManagement.domain.GodownEntryDO;
import com.bootdo.factory.warehouseManagement.domain.GoodsDetailDO;
import com.bootdo.factory.warehouseManagement.service.GodownEntryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author willice
 * @email 2782546116@qq.com
 * @date 2020-03-19 11:12:54
 */

@Controller
@RequestMapping("/factory/godownEntry")
public class GodownEntryController {
    @Autowired
    private GodownEntryService godownEntryService;

    @GetMapping()
    @RequiresPermissions("factory:godownEntry:godownEntry")
    String GodownEntry() {
        return "factory/godownEntry/godownEntry";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("factory:godownEntry:godownEntry")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<GodownEntryDO> godownEntryList = godownEntryService.list(query);
        int total = godownEntryService.count(query);
        return new PageUtils(godownEntryList, total);
    }

    @ResponseBody
    @GetMapping("/listProduct/{purchaseOrderId}")
    @RequiresPermissions("factory:godownEntry:godownEntry")
    public PageUtils listProduct(@PathVariable String purchaseOrderId) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", purchaseOrderId);
        List<ProductDetailDO> productDetailDOList = godownEntryService.listPurchaseDetails(map);
        int total = productDetailDOList.size();
        return new PageUtils(productDetailDOList, total);
    }

    @GetMapping("/add")
    @RequiresPermissions("factory:godownEntry:add")
    String add(Model model) {
        model.addAttribute("uuid", UUID.randomUUID().toString());
        return "factory/godownEntry/add";
    }

    @GetMapping("/selectPurchaseOrder")
    @RequiresPermissions("factory:godownEntry:add")
    public String purchaseOrder() {
        return "factory/godownEntry/selectPurchaseOrder";
    }

    @GetMapping("/selectProduct")
    @RequiresPermissions("factory:godownEntry:add")
    String selectProduct() {
        return "factory/godownEntry/selectProduct";
    }

    @GetMapping("/selectOthers")
    @RequiresPermissions("factory:godownEntry:add")
    String selectOthers() {
        return "factory/godownEntry/selectOthers";
    }

    @GetMapping("/edit/{godownEntryId}")
    @RequiresPermissions("factory:godownEntry:edit")
    String edit(@PathVariable("godownEntryId") String godownEntryId, Model model) {
        GodownEntryDO godownEntry = godownEntryService.get(godownEntryId);
        model.addAttribute("godownEntry", godownEntry);
        return "factory/godownEntry/edit";
    }

    @GetMapping("/detail/{godownEntryId}")
    @RequiresPermissions("factory:godownEntry:detail")
    public String details(@PathVariable String godownEntryId, Model model) {
        GodownEntryDO godownEntryDO = godownEntryService.get(godownEntryId);
        model.addAttribute("godownEntry", godownEntryDO);
        return "factory/godownEntry/detail";
    }

    @GetMapping("/account/{id}")
    @RequiresPermissions("factory:godownEntry:account")
    String account(@PathVariable("id") String id, Model model) {
        model.addAttribute("id", id);
        return "factory/godownEntry/account";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("factory:godownEntry:add")
    public R save(GodownEntryDO godownEntry, MultipartFile file, HttpServletRequest request) throws Exception {
        godownEntry.setCreateUserId(ShiroUtils.getUserId());
        godownEntry.setCreateTime(new Timestamp(new Date().getTime()));
        godownEntry.setIsDeleted(false);
        if (file != null && !file.isEmpty()) {
            String fileName = FileUtil.saveFiles(file);
            godownEntry.setFile(fileName);
        }
        String[] productIds = request.getParameterValues("productId");
        String[] productName = request.getParameterValues("productName");
        String[] quantityUnitName = request.getParameterValues("quantityUnitName");
        String[] weightUnitName = request.getParameterValues("weightUnitName");
        String[] quantity = request.getParameterValues("_quantity");
        String[] totalWeight = request.getParameterValues("totalWeight");
        String[] amount = request.getParameterValues("_amount");
        List<GoodsDetailDO> goodsDetailDOList = new LinkedList<>();
        for (int i = 0; i < productIds.length; i++) {
            GoodsDetailDO goodsDetailDO = new GoodsDetailDO();
            goodsDetailDO.setProductId(productIds[i]);
            goodsDetailDO.setProductName(productName[i]);
            goodsDetailDO.setQuantityUnitName(quantityUnitName[i]);
            goodsDetailDO.setWeightUnitName(weightUnitName[i]);
            goodsDetailDO.setQuantity(Integer.parseInt(quantity[i]));
            goodsDetailDO.setTotalWeight(Double.parseDouble(totalWeight[i]));
            goodsDetailDO.setAmount(BigDecimal.valueOf(Double.parseDouble(amount[i])));
            goodsDetailDOList.add(goodsDetailDO);
        }
        godownEntry.setGoodsDetail(goodsDetailDOList);
        if (godownEntryService.save(godownEntry) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("factory:godownEntry:edit")
    public R update(GodownEntryDO godownEntry, MultipartFile file, HttpServletRequest request) throws Exception {
        if (file != null && !file.isEmpty()) {
            String fileName = FileUtil.saveFiles(file);
            godownEntry.setFile(fileName);
        }
        String[] goodsIds = request.getParameterValues("goodsId");
        String[] orderIds = request.getParameterValues("orderId");
        String[] productIds = request.getParameterValues("productId");
        String[] productName = request.getParameterValues("productName");
        String[] quantityUnitName = request.getParameterValues("quantityUnitName");
        String[] weightUnitName = request.getParameterValues("weightUnitName");
        String[] quantity = request.getParameterValues("_quantity");
        String[] totalWeight = request.getParameterValues("totalWeight");
        String[] amount = request.getParameterValues("_amount");
        List<GoodsDetailDO> goodsDetailDOList = new LinkedList<>();
        for (int i = 0; i < productIds.length; i++) {
            GoodsDetailDO goodsDetailDO = new GoodsDetailDO();
            goodsDetailDO.setGoodsId(goodsIds[i]);
            goodsDetailDO.setOrderId(orderIds[i]);
            goodsDetailDO.setProductId(productIds[i]);
            goodsDetailDO.setProductName(productName[i]);
            goodsDetailDO.setQuantityUnitName(quantityUnitName[i]);
            goodsDetailDO.setWeightUnitName(weightUnitName[i]);
            goodsDetailDO.setQuantity(Integer.parseInt(quantity[i]));
            goodsDetailDO.setTotalWeight(Double.parseDouble(totalWeight[i]));
            goodsDetailDO.setAmount(BigDecimal.valueOf(Double.parseDouble(amount[i])));
            goodsDetailDOList.add(goodsDetailDO);
        }
        godownEntry.setGoodsDetail(goodsDetailDOList);
        if (godownEntryService.update(godownEntry) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("factory:godownEntry:remove")
    public R remove(String godownEntryId) {
        if (godownEntryService.remove(godownEntryId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("factory:godownEntry:batchRemove")
    public R remove(@RequestParam("ids[]") String[] godownEntryIds) {
        godownEntryService.batchRemove(godownEntryIds);
        return R.ok();
    }

    /**
     * 导出
     */
    @GetMapping("/export")
    @RequiresPermissions("factory:outboundOrder:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ExportUtils<GodownEntryDO> exportUtils = new ExportUtils<>();
        List<GodownEntryDO> godownEntryDOList = godownEntryService.list(params);
        exportUtils.exportFile(response, request, "入库管理", godownEntryDOList);
    }

    /**
     * 附件下载
     */
    @GetMapping("/file/{fileName}")
    public void fileDownload(@PathVariable("fileName") String fileName, HttpServletResponse response) throws Exception {
        try {
            if (fileName == null || fileName.equals("")) {
                return;
            }
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.getOutputStream().write(FileUtil.getFilesBytes(fileName));
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
