package com.bootdo.factory.contractManage.service.impl;

import com.bootdo.factory.contractManage.vo.ContractTraderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bootdo.factory.contractManage.dao.ContractTraderDao;
import com.bootdo.factory.contractManage.domain.ContractTraderDO;
import com.bootdo.factory.contractManage.service.ContractTraderService;



@Service
public class ContractTraderServiceImpl implements ContractTraderService {
	@Autowired
	private ContractTraderDao contractTraderDao;
	
	@Override
	public ContractTraderDO get(String id){
		return contractTraderDao.get(id);
	}
	
	@Override
	public List<ContractTraderVO> list(Map<String, Object> map){
		return contractTraderDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return contractTraderDao.count(map);
	}
	
	@Override
	public int save(ContractTraderDO contractTrader){
		contractTrader.setId(UUID.randomUUID().toString());
		return contractTraderDao.save(contractTrader);
	}
	
	@Override
	public int update(ContractTraderDO contractTrader){
		return contractTraderDao.update(contractTrader);
	}
	
	@Override
	public int remove(String id){
		return contractTraderDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return contractTraderDao.batchRemove(ids);
	}
	
}
