package com.bootdo.factory.warehouseManagement.dao;

import com.bootdo.factory.warehouseManagement.domain.OutboundOrderDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author willice
 * @email 2782546116@qq.com
 * @date 2020-03-19 11:13:59
 */
@Mapper
public interface OutboundOrderDao {

    OutboundOrderDO get(String outboundId);

    List<OutboundOrderDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(OutboundOrderDO outboundOrder);

    int update(OutboundOrderDO outboundOrder);

    int remove(String outbound_id);

    int batchRemove(String[] outboundIds);
}
