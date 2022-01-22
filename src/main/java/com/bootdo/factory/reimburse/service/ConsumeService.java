package com.bootdo.factory.reimburse.service;

import com.bootdo.factory.reimburse.domain.ConsumeDO;

import java.util.List;
import java.util.Map;

/**
 * @author willice
 * @email willicen@qq.com
 * @date 2020-05-25 22:39:15
 */
public interface ConsumeService {

    ConsumeDO get(String consumeId);

    List<ConsumeDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(ConsumeDO consume);

    int update(ConsumeDO consume);

    int remove(String consumeId);

    int batchRemove(String[] consumeIds);
}
