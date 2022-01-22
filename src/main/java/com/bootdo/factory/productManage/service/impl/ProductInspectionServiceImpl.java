package com.bootdo.factory.productManage.service.impl;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.factory.productManage.dao.InspectionItemsDetailDao;
import com.bootdo.factory.productManage.domain.InspectionItemsDetailDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.bootdo.factory.productManage.dao.ProductInspectionDao;
import com.bootdo.factory.productManage.domain.ProductInspectionDO;
import com.bootdo.factory.productManage.service.ProductInspectionService;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
public class ProductInspectionServiceImpl implements ProductInspectionService {
    @Autowired
    private ProductInspectionDao productInspectionDao;
    @Autowired
    private InspectionItemsDetailDao inspectionItemsDetailDao;

    @Override
    public ProductInspectionDO get(String productInspectionId) {
        return productInspectionDao.get(productInspectionId);
    }

    @Override
    public List<ProductInspectionDO> list(Map<String, Object> map) {
        return productInspectionDao.list(map);
    }

    @Override
    public ProductInspectionDO getWithNameType(String productInspectionId) {
        ProductInspectionDO equipment = productInspectionDao.get(productInspectionId);
        return equipment;
    }

    @Override
    public int count(Map<String, Object> map) {
        return productInspectionDao.count(map);
    }

    @Override
    @Transactional
    public int save(ProductInspectionDO productInspection, HttpServletRequest request) {
        productInspection.setProductInspectionId(UUID.randomUUID().toString());
        productInspection.setIsDeleted(false);
        productInspection.setCreateUserId(ShiroUtils.getUserId() + "");
        productInspection.setCreateTime(new Date());
        String[] inspectionItemsIds = request.getParameterValues("inspectionItemsId");
        String[] projectNames = request.getParameterValues("projectName");
        String[] valueTypes = request.getParameterValues("valueType");
        String[] units = request.getParameterValues("unit");
        String[] rangeThresholds = request.getParameterValues("rangeThreshold");
        String[] descriptions = request.getParameterValues("description");
        if (productInspectionDao.save(productInspection) > 0) {
            for (int i = 0; i < inspectionItemsIds.length; i++) {
                InspectionItemsDetailDO inspectionItemsDetail = new InspectionItemsDetailDO();
                inspectionItemsDetail.setId(UUID.randomUUID().toString());
                inspectionItemsDetail.setProductInspectionId(productInspection.getProductInspectionId());
                inspectionItemsDetail.setInspectionItemsId(inspectionItemsIds[i]);
                inspectionItemsDetail.setProjectName(projectNames[i]);
                inspectionItemsDetail.setValueType(valueTypes[i]);
                inspectionItemsDetail.setUnit(units[i]);
                inspectionItemsDetail.setRangeThreshold(rangeThresholds[i]);
                inspectionItemsDetail.setDescription(descriptions[i]);
                inspectionItemsDetail.setIsDeleted(false);
                inspectionItemsDetail.setCreateTime(new Date());
                inspectionItemsDetail.setCreateUserId(ShiroUtils.getUserId() + "");
                inspectionItemsDetailDao.save(inspectionItemsDetail);
            }
        }
        return 1;
    }

    @Override
    @Transactional
    public int update(ProductInspectionDO productInspection, HttpServletRequest request) {
        String[] inspectionItemsIds = request.getParameterValues("inspectionItemsId");
        String[] projectNames = request.getParameterValues("projectName");
        String[] valueTypes = request.getParameterValues("valueType");
        String[] units = request.getParameterValues("unit");
        String[] rangeThresholds = request.getParameterValues("rangeThreshold");
        String[] descriptions = request.getParameterValues("description");
        if (productInspectionDao.update(productInspection) > 0 && inspectionItemsIds != null) {
            inspectionItemsDetailDao.removeByProductInspectionId(productInspection.getProductInspectionId());
            for (int i = 0; i < inspectionItemsIds.length; i++) {
                InspectionItemsDetailDO inspectionItemsDetail = new InspectionItemsDetailDO();
                inspectionItemsDetail.setId(UUID.randomUUID().toString());
                inspectionItemsDetail.setProductInspectionId(productInspection.getProductInspectionId());
                inspectionItemsDetail.setInspectionItemsId(inspectionItemsIds[i]);
                inspectionItemsDetail.setProjectName(projectNames[i]);
                inspectionItemsDetail.setValueType(valueTypes[i]);
                inspectionItemsDetail.setUnit(units[i]);
                inspectionItemsDetail.setRangeThreshold(rangeThresholds[i]);
                inspectionItemsDetail.setDescription(descriptions[i]);
                inspectionItemsDetail.setIsDeleted(false);
                inspectionItemsDetail.setCreateTime(new Date());
                inspectionItemsDetail.setCreateUserId(ShiroUtils.getUserId() + "");
                inspectionItemsDetailDao.save(inspectionItemsDetail);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int remove(String productInspectionId) {
        return productInspectionDao.remove(productInspectionId);
    }

    @Override
    public int batchRemove(String[] productInspectionIds) {
        return productInspectionDao.batchRemove(productInspectionIds);
    }

}
