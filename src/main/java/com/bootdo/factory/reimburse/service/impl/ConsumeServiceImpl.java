package com.bootdo.factory.reimburse.service.impl;

import com.bootdo.factory.reimburse.dao.ConsumeDao;
import com.bootdo.factory.reimburse.domain.ConsumeDO;
import com.bootdo.factory.reimburse.service.ConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ConsumeServiceImpl implements ConsumeService {
	@Autowired
	private ConsumeDao consumeDao;

	@Override
	public ConsumeDO get(String consumeId) {
		return consumeDao.get(consumeId);
	}

	@Override
	public List<ConsumeDO> list(Map<String, Object> map) {
		return consumeDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return consumeDao.count(map);
	}

	@Override
	public int save(ConsumeDO consume) {
		return consumeDao.save(consume);
	}

	@Override
	public int update(ConsumeDO consume) {
		return consumeDao.update(consume);
	}

	@Override
	public int remove(String consumeId) {
		return consumeDao.remove(consumeId);
	}

	@Override
	public int batchRemove(String[] consumeIds) {
		return consumeDao.batchRemove(consumeIds);
	}

}
