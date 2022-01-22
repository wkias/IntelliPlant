package com.bootdo.factory.reimburse.service.impl;

import com.bootdo.factory.reimburse.dao.PaymentRequestDao;
import com.bootdo.factory.reimburse.domain.PaymentRequestDO;
import com.bootdo.factory.reimburse.service.PaymentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class PaymentRequestServiceImpl implements PaymentRequestService {
    @Autowired
    private PaymentRequestDao paymentRequestDao;

    @Override
    public PaymentRequestDO get(String paymentRequestId) {
        return paymentRequestDao.get(paymentRequestId);
    }

    @Override
    public List<PaymentRequestDO> list(Map<String, Object> map) {
        return paymentRequestDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return paymentRequestDao.count(map);
    }

    @Override
    public int save(PaymentRequestDO paymentRequest) {
        paymentRequest.setPaymentRequestId(UUID.randomUUID().toString());
        return paymentRequestDao.save(paymentRequest);
    }

    @Override
    public int update(PaymentRequestDO paymentRequest) {
        return paymentRequestDao.update(paymentRequest);
    }

    @Override
    public int remove(String paymentRequestId) {
        return paymentRequestDao.remove(paymentRequestId);
    }

    @Override
    public int batchRemove(String[] paymentRequestIds) {
        return paymentRequestDao.batchRemove(paymentRequestIds);
    }

}
