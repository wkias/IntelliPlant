package com.bootdo.factory.productManage.dao;

import com.bootdo.factory.productManage.domain.InspectionItemsDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author willice
 * @email 2782546116@qq.com
 * @date 2020-03-26 12:19:21
 */
@Mapper
public interface InspectionItemsDao {

    InspectionItemsDO get(String inspectionItemsId);

    List<InspectionItemsDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(InspectionItemsDO inspectionItems);

    int update(InspectionItemsDO inspectionItems);

    int remove(String inspection_items_id);

    int batchRemove(String[] inspectionItemsIds);

}
