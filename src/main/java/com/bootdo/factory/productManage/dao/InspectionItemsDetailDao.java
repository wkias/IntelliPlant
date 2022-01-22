package com.bootdo.factory.productManage.dao;

import com.bootdo.factory.productManage.domain.InspectionItemsDetailDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author Casper
 * @email 1992lcg@163.com
 * @date 2020-03-28 14:55:08
 */
@Mapper
public interface InspectionItemsDetailDao {

    InspectionItemsDetailDO get(String id);

    List<InspectionItemsDetailDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(InspectionItemsDetailDO inspectionItemsDetail);

    int update(InspectionItemsDetailDO inspectionItemsDetail);

    int removeByProductInspectionId(String ProductInspectionId);

    int remove(String id);

    int batchRemove(String[] ids);
}
