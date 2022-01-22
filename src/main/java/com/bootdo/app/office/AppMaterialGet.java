package com.bootdo.app.office;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.factory.productManage.domain.MaterialGetDO;
import com.bootdo.factory.productManage.domain.ProductDetailDO;
import com.bootdo.factory.productManage.domain.ProductPlanDO;
import com.bootdo.factory.productManage.service.MaterialGetService;
import com.bootdo.factory.productManage.service.ProductDetailService;
import com.bootdo.factory.productManage.service.ProductPlanService;
import com.bootdo.factory.warehouseManagement.controller.OutboundOrderController;
import com.bootdo.factory.warehouseManagement.domain.GoodsDetailDO;
import com.bootdo.factory.warehouseManagement.domain.OutboundOrderDO;
import net.sf.ehcache.search.aggregator.Count;
import org.apache.batik.ext.awt.image.renderable.AbstractColorInterpolationRable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Action;
import java.io.IOException;
import java.rmi.MarshalledObject;
import java.util.*;

@RestController
public class AppMaterialGet {
    @Autowired
    private ProductPlanService productPlanService;
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private DictService dictService;
    @Autowired
    private MaterialGetService materialGetService;
    @Autowired
    private OutboundOrderController outboundOrderController;

    @GetMapping("app/productPlanList")
    List<ProductPlanDO> getProductPlanList(@RequestParam Map<String, Object> params){
        List<ProductPlanDO> productPlanDOS= productPlanService.list(params);
        Iterator<ProductPlanDO> iterator=productPlanDOS.iterator();
        while(iterator.hasNext()){
            ProductPlanDO productPlanDO=iterator.next();
            if (!"已开始".equals(productPlanDO.getPlanState())
                    &&!"已超期".equals(productPlanDO.getPlanState())) {
                iterator.remove();
            }
        }
        return productPlanDOS;
    }
    @GetMapping("app/productDetailList")
    List<ProductDetailDO> getProductDetails(@RequestParam Map<String, Object> params){
        List<ProductDetailDO> productDetailDOS = productDetailService.list(params);
        if(productDetailDOS.size()!=0){
            for (ProductDetailDO product:productDetailDOS) {
                product.setProductTypeName(dictService.getName("product_type",product.getProductType()));
                product.setWeightUnitName(dictService.getName("weight_unit",product.getWeightUnit()));
                product.setQuantityUnitName(dictService.getName("quantity_unit",product.getQuantityUnit()));
            }
        }
        return productDetailDOS;
    }
    //物料领取（出库）
    @PostMapping("app/materialGet")
    void materialGet(@RequestBody Map<String,Object> map,
                             HttpServletRequest request) {
        System.out.println(map);
        Map<String, Object> materQuery = new HashMap<>();
        Map planMap = (Map) map.get("plan");
        materQuery.put("planId", planMap.get("planId"));
        List<MaterialGetDO> materialGetDOS = materialGetService.list(new HashMap<>());
        Map<String, Object> materMap = new HashMap<>();
        for (MaterialGetDO materialGet : materialGetDOS) {
            materMap.put(materialGet.getMateralId(), materialGet.getCount());

        }
        List<Map> productList = (List) map.get("products");
        List<GoodsDetailDO> goods = new ArrayList<>();
        String outBoundId = UUID.randomUUID().toString();
        for (Map product : productList) {
            String productId = (String) product.get("productId");
            if (materMap.containsKey(productId)) {
                for (MaterialGetDO materialGet : materialGetDOS) {
                    if (materialGet.getMateralId().equals(productId)) {
                        materialGet.setCount(materialGet.getCount() + Integer.parseInt(product.get("count").toString()));
                        materialGetService.update(materialGet);
                    }
                }
            } else {
                MaterialGetDO materialGet = new MaterialGetDO();
                materialGet.setId(UUID.randomUUID().toString());
                materialGet.setPlanId((String) planMap.get("planId"));
                materialGet.setMateralId(productId);
                materialGet.setCount(Integer.parseInt(product.get("count").toString()));
                materialGet.setIsDeleted(false);
                materialGet.setCreateUserId(ShiroUtils.getUserId() + "");
                materialGet.setCreateTime(new Date());
                materialGetService.save(materialGet);
            }
            //出库明细
            GoodsDetailDO good = new GoodsDetailDO();
            good.setOrderId(outBoundId);
            good.setGoodsId(UUID.randomUUID().toString());
            good.setIsProduct(0);
            good.setProductId(productId);
            good.setProductName((String) product.get("productName"));
            good.setQuantity(Integer.parseInt((String) product.get("count")));
            good.setQuantityUnitName((String) product.get("quantityUnitName"));
            good.setWeightUnitName((String) product.get("weightUnitName"));
            good.setProductTypeName((String) product.get("productTypeName"));
            try{
                double weight=Double.parseDouble((String) product.get("weight"));
                good.setTotalWeight(weight*Integer.parseInt((String) product.get("count")));
            }catch (Exception e){
                good.setTotalWeight(0d);
            }

            goods.add(good);
        }
        //出库
        try {
            OutboundOrderDO outboundOrder = new OutboundOrderDO();
            outboundOrder.setAssociatedTableId(planMap.get("planId").toString());
            outboundOrder.setAssociatedTableCode(planMap.get("planCode").toString());
            outboundOrder.setAssociatedTableType("2");
            outboundOrder.setBusinessType("material_get");
            outboundOrder.setManager(ShiroUtils.getUserId());
            outboundOrder.setIsDeleted(false);
            outboundOrder.setOutboundId(outBoundId);
            outboundOrder.setCreateTime(new Date());
            outboundOrder.setCreateUserId(ShiroUtils.getUserId());
            outboundOrder.setOutboundCode(UUID.randomUUID().toString());
            outboundOrder.setOutboundDate(new Date());
            outboundOrder.setGoodsDetail(goods);
            outboundOrderController.save(outboundOrder, null, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 已领取的物料列表
     */
    @GetMapping("app/materialGetList")
    List<MaterialGetDO> materialList(String planId){
        Map<String,Object> map=new HashMap<>();
        map.put("planId",planId);
        List<MaterialGetDO> materialGetDOS=materialGetService.list(map);
        return materialGetDOS;
    }
}
