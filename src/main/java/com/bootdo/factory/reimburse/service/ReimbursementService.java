package com.bootdo.factory.reimburse.service;

import com.bootdo.factory.reimburse.domain.ReimbursementDO;

import java.util.List;
import java.util.Map;

/**
 * @author willice
 * @email willicen@qq.com
 * @date 2020-05-25 22:39:14
 */
public interface ReimbursementService {

    ReimbursementDO get(String reimburseId);

    List<ReimbursementDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(ReimbursementDO reimbursement);

    int update(ReimbursementDO reimbursement);

    int remove(String reimburseId);

    int batchRemove(String[] reimburseIds);
}
