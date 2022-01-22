package com.bootdo.factory.warehouseManagement.service.impl;

import com.bootdo.common.service.DictService;
import com.bootdo.factory.productManage.service.ProductDefinitionService;
import com.bootdo.factory.productManage.vo.ProductDefinitionVO;
import com.bootdo.factory.saleManage.dao.SaleContentDao;
import com.bootdo.factory.saleManage.domain.SaleContentDO;
import com.bootdo.factory.warehouseManagement.dao.GoodsDetailDao;
import com.bootdo.factory.warehouseManagement.dao.OutboundOrderDao;
import com.bootdo.factory.warehouseManagement.domain.GoodsDetailDO;
import com.bootdo.factory.warehouseManagement.domain.OutboundOrderDO;
import com.bootdo.factory.warehouseManagement.domain.StockCheckDO;
import com.bootdo.factory.warehouseManagement.service.OutboundOrderService;
import com.bootdo.factory.warehouseManagement.service.StockCheckService;
import com.bootdo.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class OutboundOrderServiceImpl implements OutboundOrderService {
    @Autowired
    private OutboundOrderDao outboundOrderDao;
    @Autowired
    private SaleContentDao saleContentDao;
    @Autowired
    private ProductDefinitionService productDefinitionService;
    @Autowired
    private GoodsDetailDao goodsDetailDao;
    @Autowired
    private DictService dictService;
    @Autowired
    private UserService userService;
    @Autowired
    private StockCheckService stockCheckService;

    @Override
    public OutboundOrderDO get(String outboundId) {
        OutboundOrderDO outboundOrderDO = outboundOrderDao.get(outboundId);
        fillDO(outboundOrderDO);
        outboundOrderDO.setCreateUserName(userService.get(outboundOrderDO.getCreateUserId()).getName());
        Map<String, Object> mapp = new HashMap<>();
        mapp.put("orderId", outboundOrderDO.getOutboundId());
        outboundOrderDO.setGoodsDetail(goodsDetailDao.list(mapp));
        return outboundOrderDO;
    }

    @Override
    public List<OutboundOrderDO> list(Map<String, Object> map) {
        List<OutboundOrderDO> outboundOrderDOList = outboundOrderDao.list(map);
        for (OutboundOrderDO outboundOrderDO : outboundOrderDOList) {
            try {
                fillDO(outboundOrderDO);
                Map<String, Object> mapp = new HashMap<>();
                mapp.put("OrderId", outboundOrderDO.getOutboundId());
                outboundOrderDO.setGoodsDetail(goodsDetailDao.list(mapp));
            } catch (NullPointerException ignored) {
            }
        }
        return outboundOrderDOList;
    }

    private void fillDO(OutboundOrderDO outboundOrderDO) {
        String str;
        str = outboundOrderDO.getAssociatedTableType();
        str = dictService.getName("out_associated_table_type", str);
        outboundOrderDO.setAssociatedTableTypeName(str);
        try {
            str = outboundOrderDO.getBusinessType();
            str = dictService.getName("sale_business_type", str);
            outboundOrderDO.setBusinessTypeName(str);
        } catch (Exception e) {
            outboundOrderDO.setBusinessTypeName("其他出库");
        }
        str = outboundOrderDO.getOutboundState();
        str = dictService.getName("outbound_state", str);
        outboundOrderDO.setOutboundStateName(str);
        str = outboundOrderDO.getRepository();
        str = dictService.getName("repository", str);
        outboundOrderDO.setRepositoryName(str);
        str = userService.get(outboundOrderDO.getManager()).getName();
        outboundOrderDO.setManagerName(str);
    }

    @Override
    public List<ProductDefinitionVO> listSaleDetails(Map<String, Object> map) {
        List<SaleContentDO> saleContentDOList = saleContentDao.list(map);
        List<ProductDefinitionVO> productDefinitionVOList = new LinkedList<>();
        for (SaleContentDO saleContentDO : saleContentDOList) {
            try {
                ProductDefinitionVO productDefinitionVO = productDefinitionService.getVO(saleContentDO.getProductId());
                HashMap<String, Object> mapp = new HashMap<String, Object>();
                mapp.put("productId", saleContentDO.getProductId());
                mapp.put("isProduct", 1);
                List<GoodsDetailDO> goodsDetailDOList = goodsDetailDao.list(mapp);
                if (goodsDetailDOList != null) {
                    double count = 0;
                    for (GoodsDetailDO goodsDetailDO : goodsDetailDOList) {
                        count += goodsDetailDO.getQuantity();
                    }
                    productDefinitionVO.setCount(saleContentDO.getCount() - (int) count);
                    if (productDefinitionVO.getCount() <= 0) {
                        continue;
                    }
                } else {
                    productDefinitionVO.setCount(saleContentDO.getCount());
                }
                productDefinitionVO.setProductTypeName(dictService.getName("product_type", productDefinitionVO.getProductType()));
                productDefinitionVO.setQuantityUnitName(dictService.getName("quantity_unit", productDefinitionVO.getQuantityUnit()));
                productDefinitionVO.setWeightUnitName(dictService.getName("weight_unit", productDefinitionVO.getWeightUnit()));
                productDefinitionVOList.add(productDefinitionVO);
            } catch (NullPointerException ignored) {
            }
        }
        return productDefinitionVOList;
    }

    @Override
    public int count(Map<String, Object> map) {
        return outboundOrderDao.count(map);
    }

    @Override
    public int save(OutboundOrderDO outboundOrder) {
        String orderId = UUID.randomUUID().toString();
        outboundOrder.setOutboundId(orderId);
        for (GoodsDetailDO goodsDetailDO : outboundOrder.getGoodsDetail()) {
            goodsDetailDO.setOrderId(orderId);
            goodsDetailDO.setGoodsId(UUID.randomUUID().toString());
            goodsDetailDO.setIsProduct(1);
            goodsDetailDao.save(goodsDetailDO);
            stockCheckService.removeByProduct(buildStock(goodsDetailDO));
        }
        return outboundOrderDao.save(outboundOrder);
    }

    private StockCheckDO buildStock(GoodsDetailDO goodsDetailDO) {
        StockCheckDO stockCheckDO = new StockCheckDO();
        stockCheckDO.setProductId(goodsDetailDO.getProductId());
        stockCheckDO.setProductName(goodsDetailDO.getProductName());
        stockCheckDO.setProductTypeName(goodsDetailDO.getProductTypeName());
        stockCheckDO.setQuantity(goodsDetailDO.getQuantity());
        stockCheckDO.setQuantityUnitName(goodsDetailDO.getQuantityUnitName());
        stockCheckDO.setWeight(goodsDetailDO.getTotalWeight());
        stockCheckDO.setWeightUnitName(goodsDetailDO.getWeightUnitName());
        return stockCheckDO;
    }

    @Override
    public int update(OutboundOrderDO outboundOrder) {
        for (GoodsDetailDO goodsDetailDO : get(outboundOrder.getOutboundId()).getGoodsDetail()) {
            stockCheckService.save(buildStock(goodsDetailDO));
        }
        goodsDetailDao.removeByOrder(outboundOrder.getOutboundId());
        for (GoodsDetailDO goodsDetailDO : outboundOrder.getGoodsDetail()) {
            stockCheckService.removeByProduct(buildStock(goodsDetailDO));
            goodsDetailDao.save(goodsDetailDO);
        }
        return outboundOrderDao.update(outboundOrder);
    }

    @Override
    public int remove(String outboundId) {
        for (GoodsDetailDO goodsDetailDO : get(outboundId).getGoodsDetail()) {
            stockCheckService.save(buildStock(goodsDetailDO));
        }
        goodsDetailDao.removeByOrder(outboundId);
        return outboundOrderDao.remove(outboundId);
    }

    @Override
    public int batchRemove(String[] outboundIds) {
        for (String id : outboundIds) {
            for (GoodsDetailDO goodsDetailDO : get(id).getGoodsDetail()) {
                stockCheckService.save(buildStock(goodsDetailDO));
            }
            goodsDetailDao.removeByOrder(id);
        }
        return outboundOrderDao.batchRemove(outboundIds);
    }

}
