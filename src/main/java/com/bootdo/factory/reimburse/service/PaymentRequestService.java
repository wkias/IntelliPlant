package com.bootdo.factory.reimburse.service;

import com.bootdo.factory.reimburse.domain.PaymentRequestDO;

import java.util.List;
import java.util.Map;

/**
 * @author Casper
 * @email 1992lcg@163.com
 * @date 2020-06-02 09:26:01
 */
public interface PaymentRequestService {

    PaymentRequestDO get(String paymentRequestId);

    List<PaymentRequestDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(PaymentRequestDO paymentRequest);

    int update(PaymentRequestDO paymentRequest);

    int remove(String paymentRequestId);

    int batchRemove(String[] paymentRequestIds);
}
