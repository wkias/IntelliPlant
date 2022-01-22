package com.bootdo.factory.warehouseManagement.controller;

import com.bootdo.common.utils.*;
import com.bootdo.factory.productManage.vo.ProductDefinitionVO;
import com.bootdo.factory.warehouseManagement.domain.GoodsDetailDO;
import com.bootdo.factory.warehouseManagement.domain.OutboundOrderDO;
import com.bootdo.factory.warehouseManagement.service.OutboundOrderService;
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
 * @date 2020-03-19 11:13:59
 */

@Controller
@RequestMapping("/factory/outboundOrder")
public class OutboundOrderController {
    @Autowired
    private OutboundOrderService outboundOrderService;

    @GetMapping()
    @RequiresPermissions("factory:outboundOrder:outboundOrder")
    String OutboundOrder() {
        return "factory/outboundOrder/outboundOrder";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("factory:godownEntry:godownEntry")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<OutboundOrderDO> outboundOrderDOList = outboundOrderService.list(query);
        int total = outboundOrderService.count(query);
        return new PageUtils(outboundOrderDOList, total);
    }

    @ResponseBody
    @GetMapping("/listProduct/{purchaseOrderId}")
    @RequiresPermissions("factory:godownEntry:godownEntry")
    public PageUtils listProduct(@PathVariable String purchaseOrderId) {
        Map<String, Object> map = new HashMap<>();
        map.put("saleId", purchaseOrderId);
        List<ProductDefinitionVO> productDefinitionVOList = outboundOrderService.listSaleDetails(map);
        int total = productDefinitionVOList.size();
        return new PageUtils(productDefinitionVOList, total);
    }

    @GetMapping("/add")
    @RequiresPermissions("factory:outboundOrder:add")
    String add(Model model) {
        model.addAttribute("uuid", UUID.randomUUID().toString());
        return "factory/outboundOrder/add";
    }

    @GetMapping("/selectSaleManage")
    @RequiresPermissions("factory:outboundOrder:add")
    String saleManage() {
        return "factory/outboundOrder/selectSaleManage";
    }

    @GetMapping("/selectProduct")
    @RequiresPermissions("factory:outboundOrder:add")
    String selectProduct() {
        return "factory/outboundOrder/selectProduct";
    }

    @GetMapping("/selectOthers")
    @RequiresPermissions("factory:godownEntry:add")
    String selectOthers() {
        return "factory/godownEntry/selectOthers";
    }

    @GetMapping("/edit/{outboundId}")
    @RequiresPermissions("factory:outboundOrder:edit")
    String edit(@PathVariable("outboundId") String outboundId, Model model) {
        OutboundOrderDO outboundOrder = outboundOrderService.get(outboundId);
        model.addAttribute("outboundOrder", outboundOrder);
        return "factory/outboundOrder/edit";
    }

    @GetMapping("/detail/{outboundId}")
    @RequiresPermissions("factory:outboundOrder:detail")
    public String details(@PathVariable String outboundId, Model model) {
        OutboundOrderDO outboundOrderDO = outboundOrderService.get(outboundId);
        model.addAttribute("outboundOrder", outboundOrderDO);
        return "factory/outboundOrder/detail";
    }

    @GetMapping("/account/{id}")
    @RequiresPermissions("factory:outboundOrder:account")
    String account(@PathVariable("id") String id, Model model) {
        model.addAttribute("id", id);
        return "factory/outboundOrder/account";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("factory:outboundOrder:add")
    public R save(OutboundOrderDO outboundOrderDO, MultipartFile file, HttpServletRequest request) throws Exception {
        outboundOrderDO.setCreateUserId(ShiroUtils.getUserId());
        outboundOrderDO.setCreateTime(new Timestamp(new Date().getTime()));
        outboundOrderDO.setIsDeleted(false);
        if (file != null && !file.isEmpty()) {
            String fileName = FileUtil.saveFiles(file);
            outboundOrderDO.setFile(fileName);
        }
        String[] productIds = request.getParameterValues("productId");
        String[] productName = request.getParameterValues("productName");
        String[] quantityUnitName = request.getParameterValues("quantityUnitName");
        String[] weightUnitName = request.getParameterValues("weightUnitName");
        String[] quantity = request.getParameterValues("_quantity");
        String[] totalWeight = request.getParameterValues("totalWeight");
        String[] amount = request.getParameterValues("_amount");
        List<GoodsDetailDO> goodsDetailDOList = new LinkedList<>();
        if(productIds!=null){
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
            outboundOrderDO.setGoodsDetail(goodsDetailDOList);
        }

        if (outboundOrderService.save(outboundOrderDO) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("factory:outboundOrder:edit")
    public R update(OutboundOrderDO outboundOrderDO, MultipartFile file, HttpServletRequest request) throws Exception {
        if (file != null && !file.isEmpty()) {
            String fileName = FileUtil.saveFiles(file);
            outboundOrderDO.setFile(fileName);
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
        outboundOrderDO.setGoodsDetail(goodsDetailDOList);
        if (outboundOrderService.update(outboundOrderDO) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("factory:outboundOrder:remove")
    public R remove(String outboundId) {
        if (outboundOrderService.remove(outboundId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("factory:outboundOrder:batchRemove")
    public R remove(@RequestParam("ids[]") String[] outboundIds) {
        outboundOrderService.batchRemove(outboundIds);
        return R.ok();
    }

    /**
     * 导出
     */
    @GetMapping("/export")
    @RequiresPermissions("factory:outboundOrder:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ExportUtils<OutboundOrderDO> exportUtils = new ExportUtils<>();
        List<OutboundOrderDO> outboundOrderDOList = outboundOrderService.list(params);
        exportUtils.exportFile(response, request, "出库管理", outboundOrderDOList);
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
