package com.bootdo.factory.reimburse.service.impl;

import com.bootdo.common.service.DictService;
import com.bootdo.factory.reimburse.dao.ConsumeDao;
import com.bootdo.factory.reimburse.dao.LoanDao;
import com.bootdo.factory.reimburse.dao.ReimbursementDao;
import com.bootdo.factory.reimburse.domain.ConsumeDO;
import com.bootdo.factory.reimburse.domain.LoanDO;
import com.bootdo.factory.reimburse.domain.ReimbursementDO;
import com.bootdo.factory.reimburse.service.ReimbursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class ReimbursementServiceImpl implements ReimbursementService {
    @Autowired
    private ReimbursementDao reimbursementDao;
    @Autowired
    private DictService dictService;
    @Autowired
    private LoanDao loanDao;
    @Autowired
    private ConsumeDao consumeDao;

    @Override
    public ReimbursementDO get(String reimburseId) {
        Map<String, Object> map = new HashMap<>();
        map.put("reimburseId", reimburseId);
        ReimbursementDO reimbursementDO = reimbursementDao.get(reimburseId);
        reimbursementDO.setInvoiceTypeName(dictService.getName("reimburse_type", reimbursementDO.getInvoiceType()));
        reimbursementDO.setLoan(loanDao.list(map));
        reimbursementDO.setConsume(consumeDao.list(map));
//        reimbursementDO.setStateName(dictService.getName("reimburse_state", String.valueOf(reimbursementDO.getState())));
        return reimbursementDO;
    }

    @Override
    public List<ReimbursementDO> list(Map<String, Object> map) {
        List<ReimbursementDO> reimbursementDOS = reimbursementDao.list(map);
        for (ReimbursementDO reimbursementDO : reimbursementDOS) {
            Map<String, Object> mapp = new HashMap<>();
            mapp.put("reimburseId", reimbursementDO.getReimburseId());
            reimbursementDO.setLoan(loanDao.list(mapp));
            reimbursementDO.setConsume(consumeDao.list(mapp));
            reimbursementDO.setInvoiceTypeName(dictService.getName("reimburse_type", reimbursementDO.getInvoiceType()));
//            reimbursementDO.setStateName(dictService.getName("reimburse_state", String.valueOf(reimbursementDO.getState())));
        }
        return reimbursementDOS;
    }

	@Override
	public int count(Map<String, Object> map) {
		return reimbursementDao.count(map);
	}

	@Override
	public int save(ReimbursementDO reimbursement) {
        reimbursement.setReimburseId(UUID.randomUUID().toString());
        for (ConsumeDO c : reimbursement.getConsume()) {
            c.setConsumeId(UUID.randomUUID().toString());
            c.setReimburseId(reimbursement.getReimburseId());
            consumeDao.save(c);
        }
        for (LoanDO l : reimbursement.getLoan()) {
            l.setLoanId(UUID.randomUUID().toString());
            l.setReimburseId(reimbursement.getReimburseId());
            loanDao.save(l);
        }
        return reimbursementDao.save(reimbursement);
    }

	@Override
	public int update(ReimbursementDO reimbursement) {
        Map<String, Object> map = new HashMap<>();
        map.put("reimburseId", reimbursement.getReimburseId());
        List<ConsumeDO> consumeDOS = consumeDao.list(map);
        for (ConsumeDO c : consumeDOS) {
            consumeDao.remove(c.getConsumeId());
        }
        List<LoanDO> loanDOS = loanDao.list(map);
        for (LoanDO l : loanDOS) {
            loanDao.remove(l.getLoanId());
        }
        for (ConsumeDO c : reimbursement.getConsume()) {
            c.setConsumeId(UUID.randomUUID().toString());
            c.setReimburseId(reimbursement.getReimburseId());
            consumeDao.save(c);
        }
        for (LoanDO l : reimbursement.getLoan()) {
            l.setLoanId(UUID.randomUUID().toString());
            l.setReimburseId(reimbursement.getReimburseId());
            loanDao.save(l);
        }
        return reimbursementDao.update(reimbursement);
    }

	@Override
	public int remove(String reimburseId) {
        Map<String, Object> map = new HashMap<>();
        map.put("reimburseId", reimburseId);
        List<ConsumeDO> consumeDOS = consumeDao.list(map);
        for (ConsumeDO c : consumeDOS) {
            consumeDao.remove(c.getConsumeId());
        }
        List<LoanDO> loanDOS = loanDao.list(map);
        for (LoanDO l : loanDOS) {
            loanDao.remove(l.getLoanId());
        }
        return reimbursementDao.remove(reimburseId);
    }

	@Override
	public int batchRemove(String[] reimburseIds) {
        int i = 0;
        for (String r : reimburseIds) {
            i = remove(r);
            if (i == 0) {
                return i;
            }
        }
        return i;
    }

}
