package com.bootdo.factory.reimburse.service.impl;

import com.bootdo.factory.reimburse.dao.LoanDao;
import com.bootdo.factory.reimburse.domain.LoanDO;
import com.bootdo.factory.reimburse.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class LoanServiceImpl implements LoanService {
	@Autowired
	private LoanDao loanDao;

	@Override
	public LoanDO get(String loanId) {
		return loanDao.get(loanId);
	}

	@Override
	public List<LoanDO> list(Map<String, Object> map) {
		return loanDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return loanDao.count(map);
	}

	@Override
	public int save(LoanDO loan) {
		return loanDao.save(loan);
	}

	@Override
	public int update(LoanDO loan) {
		return loanDao.update(loan);
	}

	@Override
	public int remove(String loanId) {
		return loanDao.remove(loanId);
	}

	@Override
	public int batchRemove(String[] loanIds) {
		return loanDao.batchRemove(loanIds);
	}

}
