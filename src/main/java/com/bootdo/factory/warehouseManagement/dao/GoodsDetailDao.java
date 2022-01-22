package com.bootdo.factory.warehouseManagement.dao;

import com.bootdo.factory.warehouseManagement.domain.GoodsDetailDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author willice
 * @email 2782546116@qq.com
 * @date 2020-04-16 16:17:26
 */
@Mapper
public interface GoodsDetailDao {

    GoodsDetailDO get(String goodsId);

    List<GoodsDetailDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(GoodsDetailDO goodsDetail);

    int update(GoodsDetailDO goodsDetail);

    int removeByOrder(String orderId);

    int remove(String goodsId);

    int batchRemove(String[] goodsIds);
}
