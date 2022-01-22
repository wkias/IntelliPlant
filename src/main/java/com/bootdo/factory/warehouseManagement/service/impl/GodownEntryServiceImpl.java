package com.bootdo.factory.warehouseManagement.service.impl;

import com.bootdo.common.service.DictService;
import com.bootdo.factory.productManage.dao.ProductDetailDao;
import com.bootdo.factory.productManage.domain.ProductDetailDO;
import com.bootdo.factory.warehouseManagement.dao.GodownEntryDao;
import com.bootdo.factory.warehouseManagement.dao.GoodsDetailDao;
import com.bootdo.factory.warehouseManagement.dao.PurchaseDetailDao;
import com.bootdo.factory.warehouseManagement.domain.GodownEntryDO;
import com.bootdo.factory.warehouseManagement.domain.GoodsDetailDO;
import com.bootdo.factory.warehouseManagement.domain.PurchaseDetailDO;
import com.bootdo.factory.warehouseManagement.domain.StockCheckDO;
import com.bootdo.factory.warehouseManagement.service.GodownEntryService;
import com.bootdo.factory.warehouseManagement.service.StockCheckService;
import com.bootdo.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class GodownEntryServiceImpl implements GodownEntryService {
    @Autowired
    private GodownEntryDao godownEntryDao;
    @Autowired
    private PurchaseDetailDao purchaseDetailDao;
    @Autowired
    private ProductDetailDao productDetailDao;
    @Autowired
    private GoodsDetailDao goodsDetailDao;
    @Autowired
    private DictService dictService;
    @Autowired
    private UserService userService;
    @Autowired
    private StockCheckService stockCheckService;

    @Override
    public GodownEntryDO get(String godownEntryId) {
        GodownEntryDO godownEntryDO = godownEntryDao.get(godownEntryId);
        fillGodownEntryDO(godownEntryDO);
        return godownEntryDO;
    }

    @Override
    public List<GodownEntryDO> list(Map<String, Object> map) {
        List<GodownEntryDO> godownEntryDOList = godownEntryDao.list(map);
        for (GodownEntryDO godownEntryDO : godownEntryDOList) {
            fillGodownEntryDO(godownEntryDO);
        }
        return godownEntryDOList;
    }

    private void fillGodownEntryDO(GodownEntryDO godownEntryDO) {
        String str;
        str = godownEntryDO.getAssociatedTableType();
        str = dictService.getName("in_associated_table_type", str);
        godownEntryDO.setAssociatedTableTypeName(str);

        try {
            str = godownEntryDO.getBusinessType();
            str = dictService.getName("purchase_business_type", str);
            godownEntryDO.setBusinessTypeName(str);
        } catch (Exception e) {
            godownEntryDO.setBusinessTypeName("其他入库");
        }

        str = godownEntryDO.getGodownEntryState();
        str = dictService.getName("godown_entry_state", str);
        godownEntryDO.setGodownEntryStateName(str);

        str = godownEntryDO.getRepository();
        str = dictService.getName("repository", str);
        godownEntryDO.setRepositoryName(str);

        str = userService.get(godownEntryDO.getManager()).getName();
        godownEntryDO.setManagerName(str);

        str = userService.get(godownEntryDO.getCreateUserId()).getName();
        godownEntryDO.setCreateUserName(str);

        Map<String, Object> mapp = new HashMap<>();
        mapp.put("orderId", godownEntryDO.getGodownEntryId());
        godownEntryDO.setGoodsDetail(goodsDetailDao.list(mapp));
    }

    @Override
    public List<ProductDetailDO> listPurchaseDetails(Map<String, Object> map) {
        List<PurchaseDetailDO> purchaseDetailDOList = purchaseDetailDao.list(map);
        List<ProductDetailDO> productDetailDOList = new LinkedList<>();
        for (PurchaseDetailDO purchaseDetailDO : purchaseDetailDOList) {
            ProductDetailDO productDetailDO = productDetailDao.get(purchaseDetailDO.getProductId());
            productDetailDO.setIsDeleted(purchaseDetailDO.getNumber().intValue());
            productDetailDO.setProductTypeName(dictService.getName("product_type", productDetailDO.getProductType()));
            productDetailDO.setQuantityUnitName(dictService.getName("quantity_unit", productDetailDO.getQuantityUnit()));
            productDetailDO.setWeightUnitName(dictService.getName("weight_unit", productDetailDO.getWeightUnit()));
            productDetailDOList.add(productDetailDO);
        }
        return productDetailDOList;
    }

    @Override
    public int count(Map<String, Object> map) {
        return godownEntryDao.count(map);
    }

    @Override
    public int save(GodownEntryDO godownEntry) {
        String orderId = UUID.randomUUID().toString();
        godownEntry.setGodownEntryId(orderId);
        for (GoodsDetailDO goodsDetailDO : godownEntry.getGoodsDetail()) {
            goodsDetailDO.setOrderId(orderId);
            goodsDetailDO.setGoodsId(UUID.randomUUID().toString());
            goodsDetailDO.setIsProduct(0);
            goodsDetailDao.save(goodsDetailDO);
            stockCheckService.save(buildStock(goodsDetailDO));
        }
        return godownEntryDao.save(godownEntry);
    }

    @Override
    public int update(GodownEntryDO godownEntry) {
        for (GoodsDetailDO goodsDetailDO : get(godownEntry.getGodownEntryId()).getGoodsDetail()) {
            stockCheckService.removeByProduct(buildStock(goodsDetailDO));
        }
        goodsDetailDao.removeByOrder(godownEntry.getGodownEntryId());
        for (GoodsDetailDO goodsDetailDO : godownEntry.getGoodsDetail()) {
            stockCheckService.save(buildStock(goodsDetailDO));
            goodsDetailDao.save(goodsDetailDO);
        }
        return godownEntryDao.update(godownEntry);
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
    public int remove(String godownEntryId) {
        for (GoodsDetailDO goodsDetailDO : get(godownEntryId).getGoodsDetail()) {
            stockCheckService.removeByProduct(buildStock(goodsDetailDO));
        }
        goodsDetailDao.removeByOrder(godownEntryId);
        return godownEntryDao.remove(godownEntryId);
    }

    @Override
    public int batchRemove(String[] godownEntryIds) {
        for (String id : godownEntryIds) {
            for (GoodsDetailDO goodsDetailDO : get(id).getGoodsDetail()) {
                stockCheckService.removeByProduct(buildStock(goodsDetailDO));
            }
            goodsDetailDao.removeByOrder(id);
        }
        return godownEntryDao.batchRemove(godownEntryIds);
    }

}
