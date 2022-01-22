package com.bootdo.factory.dao;

import com.bootdo.factory.myOffice.domain.BankAccountDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author Casper
 * @email 1992lcg@163.com
 * @date 2020-05-25 15:36:18
 */
@Mapper
public interface BankAccountDao {

	BankAccountDO get(String bankAccountId);
	
	List<BankAccountDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(BankAccountDO bankAccount);
	
	int update(BankAccountDO bankAccount);
	
	int remove(String bank_account_id);
	
	int batchRemove(String[] bankAccountIds);
}
