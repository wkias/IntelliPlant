package com.bootdo.factory.service.impl;

import com.bootdo.common.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bootdo.factory.dao.BankAccountDao;
import com.bootdo.factory.myOffice.domain.BankAccountDO;
import com.bootdo.factory.service.BankAccountService;



@Service
public class BankAccountServiceImpl implements BankAccountService {
	@Autowired
	private BankAccountDao bankAccountDao;
	
	@Override
	public BankAccountDO get(String bankAccountId){
		return bankAccountDao.get(bankAccountId);
	}
	
	@Override
	public List<BankAccountDO> list(Map<String, Object> map){
		return bankAccountDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return bankAccountDao.count(map);
	}
	
	@Override
	public int save(BankAccountDO bankAccount){
		bankAccount.setBankAccountId(UUID.randomUUID().toString());
		return bankAccountDao.save(bankAccount);
	}
	
	@Override
	public int update(BankAccountDO bankAccount){
		return bankAccountDao.update(bankAccount);
	}
	
	@Override
	public int remove(String bankAccountId){
		return bankAccountDao.remove(bankAccountId);
	}
	
	@Override
	public int batchRemove(String[] bankAccountIds){
		return bankAccountDao.batchRemove(bankAccountIds);
	}
	
}
