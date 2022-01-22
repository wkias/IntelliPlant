package com.bootdo.factory.contractManage.service.impl;

import com.bootdo.common.service.DictService;
import com.bootdo.factory.contractManage.dao.ReceiveRecordDao;
import com.bootdo.factory.contractManage.domain.ReceivePlanDO;
import com.bootdo.factory.contractManage.domain.ReceiveRecordDO;
import com.bootdo.factory.contractManage.service.ReceivePlanService;
import com.bootdo.factory.contractManage.service.ReceiveRecordService;
import com.bootdo.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ReceiveRecordServiceImpl implements ReceiveRecordService {
    @Autowired
    private ReceiveRecordDao receiveRecordDao;
    @Autowired
    private ReceivePlanService receivePlanService;
    @Autowired
    private UserService userService;
    @Autowired
    private DictService dictService;

    @Override
    public ReceiveRecordDO get(String recordId) {
        ReceiveRecordDO receiveRecordDO = receiveRecordDao.get(recordId);
        String payeeName = userService.get(receiveRecordDO.getPayee()).getName();
        receiveRecordDO.setPayeeName(payeeName);
        String receivePlanBatchName = dictService.getName("receive_batch", receiveRecordDO.getPlanBatch());
        receiveRecordDO.setPlanBatchName(receivePlanBatchName);
        String receiveTypeName = dictService.getName("receive_type", receiveRecordDO.getReceiveType());
        receiveRecordDO.setReceiveTypeName(receiveTypeName);
        String paymentMethodName = dictService.getName("payment_method", receiveRecordDO.getPaymentMethod());
        receiveRecordDO.setPaymentMethodName(paymentMethodName);
        try {
            Map<String, Object> mapp = new HashMap<>();
            mapp.put("contractId", receiveRecordDO.getContractId());
            mapp.put("receiveBatch", receiveRecordDO.getPlanBatch());
            ReceivePlanDO receivePlanDO = receivePlanService.list(mapp).get(0);
            receiveRecordDO.setPlannedDate(receivePlanDO.getPlannedDate());
            receiveRecordDO.setState(receivePlanDO.getState());
            receiveRecordDO.setStateString(receivePlanDO.getStateString());
        } catch (Exception e) {
            receiveRecordDO.setState("2");
            receiveRecordDO.setStateString(dictService.getName("receive_state", receiveRecordDO.getState()));
        }
        return receiveRecordDO;
    }

    @Override
    public List<ReceiveRecordDO> list(Map<String, Object> map) {
        List<ReceiveRecordDO> receiveRecordDOList = receiveRecordDao.list(map);
        for (ReceiveRecordDO receiveRecordDO : receiveRecordDOList) {
            String payeeName = userService.get(receiveRecordDO.getPayee()).getName();
            receiveRecordDO.setPayeeName(payeeName);
            String receivePlanBatchName = dictService.getName("receive_batch", receiveRecordDO.getPlanBatch());
            receiveRecordDO.setPlanBatchName(receivePlanBatchName);
            String receiveTypeName = dictService.getName("receive_type", receiveRecordDO.getReceiveType());
            receiveRecordDO.setReceiveTypeName(receiveTypeName);
            String paymentMethodName = dictService.getName("payment_method", receiveRecordDO.getPaymentMethod());
            receiveRecordDO.setPaymentMethodName(paymentMethodName);
            try {
                Map<String, Object> mapp = new HashMap<>();
                mapp.put("contractId", receiveRecordDO.getContractId());
                mapp.put("receiveBatch", receiveRecordDO.getPlanBatch());
                ReceivePlanDO receivePlanDO = receivePlanService.list(mapp).get(0);
                receiveRecordDO.setPlannedDate(receivePlanDO.getPlannedDate());
                receiveRecordDO.setState(receivePlanDO.getState());
                receiveRecordDO.setStateString(receivePlanDO.getStateString());
            } catch (Exception e) {
                receiveRecordDO.setState("2");
                receiveRecordDO.setStateString(dictService.getName("receive_state", receiveRecordDO.getState()));
            }
        }
        return receiveRecordDOList;
    }

    @Override
    public int count(Map<String, Object> map) {
        return receiveRecordDao.count(map);
    }

    @Override
    public int save(ReceiveRecordDO receiveRecord) {
        receiveRecord.setRecordId(UUID.randomUUID().toString());
        try {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("contractId", receiveRecord.getContractId());
            map.put("receiveBatch", receiveRecord.getPlanBatch());
            map.put("sort", "receive_batch");
            map.put("order", "desc");
            ReceivePlanDO receivePlanDO = receivePlanService.list(map).get(0);
            receivePlanDO.setReceivedAmount(receivePlanDO.getReceivedAmount().add(receiveRecord.getReceiveAmount()));
            if (receivePlanDO.getReceivedAmount().compareTo(receivePlanDO.getPlannedAmount()) < 0) {
                receivePlanDO.setState("0");
            } else {
                receivePlanDO.setState("2");
            }
            receivePlanService.update(receivePlanDO);
        } catch (Exception ignored) {
        }
        return receiveRecordDao.save(receiveRecord);
    }

    @Override
    public int update(ReceiveRecordDO receiveRecord) {
        return receiveRecordDao.update(receiveRecord);
    }

    @Override
    public int remove(String recordId) {
        return receiveRecordDao.remove(recordId);
    }

    @Override
    public int batchRemove(String[] recordIds) {
        return receiveRecordDao.batchRemove(recordIds);
    }

}
