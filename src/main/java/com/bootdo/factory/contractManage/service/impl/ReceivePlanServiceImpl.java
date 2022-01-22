package com.bootdo.factory.contractManage.service.impl;

import com.bootdo.common.service.DictService;
import com.bootdo.factory.contractManage.dao.ReceivePlanDao;
import com.bootdo.factory.contractManage.domain.ReceivePlanDO;
import com.bootdo.factory.contractManage.service.ContractManageService;
import com.bootdo.factory.contractManage.service.ReceivePlanService;
import com.bootdo.factory.contractManage.vo.ContractManageVO;
import com.bootdo.factory.contractManage.vo.ContractTraderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;


@Service
public class ReceivePlanServiceImpl implements ReceivePlanService {
    @Autowired
    private ReceivePlanDao receivePlanDao;
    @Autowired
    private DictService dictService;
    @Autowired
    private ContractManageService contractManageService;

    @Override
    public ReceivePlanDO get(String receiveId) {
        ReceivePlanDO receivePlanDO = receivePlanDao.get(receiveId);
        try {
            String stateString = dictService.getName("receive_state", receivePlanDO.getState());
            receivePlanDO.setStateString(stateString);
            String receiveBatchName = dictService.getName("receive_batch", receivePlanDO.getReceiveBatch());
            receivePlanDO.setReceiveBatchName(receiveBatchName);
            receivePlanDO.setContractAmount(BigDecimal.valueOf(contractManageService.get(receivePlanDO.getContractId()).getContractAmount()));
            if (receivePlanDO.getState().equals("0") && new Date().after(receivePlanDO.getPlannedDate())) {
                long duration = (new Date().getTime() - receivePlanDO.getPlannedDate().getTime()) / (1000 * 60 * 60 * 24);
                receivePlanDO.setDaysOverdue(duration);
            }
            receivePlanDO.setUnreceivedAmount(receivePlanDO.getPlannedAmount().subtract(receivePlanDO.getReceivedAmount()));
            if (receivePlanDO.getUnreceivedAmount().compareTo(BigDecimal.valueOf(0)) < 0) {
                receivePlanDO.setUnreceivedAmount(BigDecimal.valueOf(0));
            }
        } catch (Exception ignored) {
        }
        return receivePlanDO;
    }

    @Override
    public List<ReceivePlanDO> list(Map<String, Object> map) {
        List<ReceivePlanDO> receivePlanDOList = receivePlanDao.list(map);
        for (ReceivePlanDO receivePlanDO : receivePlanDOList) {
            try {
                String stateString = dictService.getName("receive_state", receivePlanDO.getState());
                receivePlanDO.setStateString(stateString);
                String receiveBatchName = dictService.getName("receive_batch", receivePlanDO.getReceiveBatch());
                receivePlanDO.setReceiveBatchName(receiveBatchName);
                ContractManageVO contractManageVO = contractManageService.getVO(receivePlanDO.getContractId());
                receivePlanDO.setContractAmount(BigDecimal.valueOf(contractManageVO.getContractAmount()));
                StringBuilder str = new StringBuilder();
                if (contractManageVO.getContractType().equals("sale_contract")) {
                    for (ContractTraderVO informationDO : contractManageVO.getDemanders()) {
                        str.append(informationDO.getTrader().getCustermerName());
                        str.append(";");
                    }
                } else {
                    for (ContractTraderVO informationDO : contractManageVO.getSuppliers()) {
                        str.append(informationDO.getTrader().getCustermerName());
                        str.append(";");
                    }
                }
                receivePlanDO.setCustomer(str.toString());
                if (receivePlanDO.getState().equals("0") && new Date().after(receivePlanDO.getPlannedDate())) {
                    long duration = (new Date().getTime() - receivePlanDO.getPlannedDate().getTime()) / (1000 * 60 * 60 * 24);
                    receivePlanDO.setDaysOverdue(duration);
                }
                receivePlanDO.setUnreceivedAmount(receivePlanDO.getPlannedAmount().subtract(receivePlanDO.getReceivedAmount()));
                if (receivePlanDO.getUnreceivedAmount().compareTo(BigDecimal.valueOf(0)) < 0) {
                    receivePlanDO.setUnreceivedAmount(BigDecimal.valueOf(0));
                }
            } catch (Exception ignored) {
            }
        }
        return receivePlanDOList;
    }

    @Override
    public int count(Map<String, Object> map) {
        return receivePlanDao.count(map);
    }

    @Override
    public int save(ReceivePlanDO receivePlan) {
        receivePlan.setReceiveId(UUID.randomUUID().toString());
        try {
            HashMap<String, Object> map = new LinkedHashMap<>();
            map.put("contractId", receivePlan.getContractId());
            map.put("sort", "receive_batch");
            map.put("order", "desc");
            int receiveBatch = Integer.parseInt(list(map).get(0).getReceiveBatch()) + 1;
            receivePlan.setReceiveBatch(Integer.toString(receiveBatch));
        } catch (Exception e) {
            receivePlan.setReceiveBatch("1");
        }
        receivePlan.setReceivedAmount(new BigDecimal(0));
        return receivePlanDao.save(receivePlan);
    }

    @Override
    public int update(ReceivePlanDO receivePlan) {
        return receivePlanDao.update(receivePlan);
    }

    @Override
    public int remove(String receiveId) {
        return receivePlanDao.remove(receiveId);
    }

    @Override
    public int batchRemove(String[] receiveIds) {
        return receivePlanDao.batchRemove(receiveIds);
    }

}
