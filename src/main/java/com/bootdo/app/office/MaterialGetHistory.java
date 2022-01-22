package com.bootdo.app.office;

import com.bootdo.common.utils.R;
import com.bootdo.factory.productManage.domain.ProductPlanDO;
import com.bootdo.factory.warehouseManagement.dao.GoodsDetailDao;
import com.bootdo.factory.warehouseManagement.dao.OutboundOrderDao;
import com.bootdo.factory.warehouseManagement.domain.GoodsDetailDO;
import com.bootdo.factory.warehouseManagement.domain.OutboundOrderDO;
import com.bootdo.factory.warehouseManagement.service.OutboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.HashPrintServiceAttributeSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MaterialGetHistory {
    @Autowired
    AppMaterialGet appMaterialGet;
    @Autowired
    OutboundOrderDao outboundOrderDao;
    @Autowired
    GoodsDetailDao goodsDetailDao;

    @GetMapping("app/materialHistory/getPlanList")
    List getPlanList(){
        List<ProductPlanDO> planList=appMaterialGet.getProductPlanList(new HashMap<>());
        List<Map<String,Object>> list=new ArrayList<>();
        for(ProductPlanDO plan:planList){
            Map<String,Object> map=new HashMap<>();
            Map<String,Object> queryMap=new HashMap<>();
            queryMap.put("associated_table_id",plan.getPlanId());
            queryMap.put("sort","create_time");
            queryMap.put("order","asc");
            queryMap.put("offset",0);
            queryMap.put("limit",1);
            List<OutboundOrderDO> outboundOrderS=outboundOrderDao.list(queryMap);
            if(!outboundOrderS.isEmpty()){
                map.put("planId",plan.getPlanId());
                map.put("planCode",plan.getPlanCode());
                map.put("firstGetTime",outboundOrderS.get(0).getCreateTime());
                map.put("outboundId",outboundOrderS.get(0).getOutboundId());
                list.add(map);
            }
        }
        return list;
    }
    @GetMapping("app/materialHistory/getDetail")
    List getDetail(String planId){
        List<Map<String,Object>> detailList=new ArrayList<>();
        Map<String,Object> orderQuery=new HashMap();
        orderQuery.put("associated_table_id",planId);
        List<OutboundOrderDO> outboundOrderS=outboundOrderDao.list(orderQuery);
        for(OutboundOrderDO outbound:outboundOrderS){
            Map<String,Object> goodQuery=new HashMap();
            goodQuery.put("orderId",outbound.getOutboundId());
            List<GoodsDetailDO> goodsDetails=goodsDetailDao.list(goodQuery);
            Map<String,Object> map=new HashMap<>();
            map.put("getTime",outbound.getCreateTime());
            map.put("goods",goodsDetails);
            detailList.add(map);
        }
        return detailList;
    }
}
