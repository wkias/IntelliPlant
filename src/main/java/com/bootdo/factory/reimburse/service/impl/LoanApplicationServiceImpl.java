package com.bootdo.factory.reimburse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bootdo.factory.reimburse.dao.LoanApplicationDao;
import com.bootdo.factory.reimburse.domain.LoanApplicationDO;
import com.bootdo.factory.reimburse.service.LoanApplicationService;



@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {
	@Autowired
	private LoanApplicationDao loanApplicationDao;
	
	@Override
	public LoanApplicationDO get(String loanApplicationId){
		return loanApplicationDao.get(loanApplicationId);
	}
	
	@Override
	public List<LoanApplicationDO> list(Map<String, Object> map){
		return loanApplicationDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return loanApplicationDao.count(map);
	}
	
	@Override
	public int save(LoanApplicationDO loanApplication){
		loanApplication.setLoanApplicationId(UUID.randomUUID().toString());
		return loanApplicationDao.save(loanApplication);
	}
	
	@Override
	public int update(LoanApplicationDO loanApplication){
		return loanApplicationDao.update(loanApplication);
	}
	
	@Override
	public int remove(String loanApplicationId){
		return loanApplicationDao.remove(loanApplicationId);
	}
	
	@Override
	public int batchRemove(String[] loanApplicationIds){
		return loanApplicationDao.batchRemove(loanApplicationIds);
	}
	
}
