package com.bootdo.factory.reimburse.service;

import com.bootdo.factory.reimburse.domain.LoanApplicationDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author zrj
 * @email 1992lcg@163.com
 * @date 2020-05-27 08:45:58
 */
public interface LoanApplicationService {
	
	LoanApplicationDO get(String loanApplicationId);
	
	List<LoanApplicationDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LoanApplicationDO loanApplication);
	
	int update(LoanApplicationDO loanApplication);
	
	int remove(String loanApplicationId);
	
	int batchRemove(String[] loanApplicationIds);
}
