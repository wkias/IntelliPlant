package com.bootdo.factory.warehouseManagement.service;

import com.bootdo.factory.productManage.vo.ProductDefinitionVO;
import com.bootdo.factory.warehouseManagement.domain.OutboundOrderDO;

import java.util.List;
import java.util.Map;

/**
 * @author willice
 * @email 2782546116@qq.com
 * @date 2020-03-19 11:13:59
 */
public interface OutboundOrderService {

    OutboundOrderDO get(String outboundId);

    List<OutboundOrderDO> list(Map<String, Object> map);

    List<ProductDefinitionVO> listSaleDetails(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(OutboundOrderDO outboundOrder);

    int update(OutboundOrderDO outboundOrder);

    int remove(String outboundId);

    int batchRemove(String[] outboundIds);
}
