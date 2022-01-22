package com.bootdo.factory.reimburse.dao;

import com.bootdo.factory.reimburse.domain.ConsumeDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author willice
 * @email willicen@qq.com
 * @date 2020-05-25 22:39:15
 */
@Mapper
public interface ConsumeDao {

	ConsumeDO get(String consumeId);

	List<ConsumeDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(ConsumeDO consume);

	int update(ConsumeDO consume);

	int remove(String consume_id);

	int batchRemove(String[] consumeIds);
}
