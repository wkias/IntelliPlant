package com.bootdo.factory.reimburse.dao;

import com.bootdo.factory.reimburse.domain.PaymentRequestDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author Casper
 * @email 1992lcg@163.com
 * @date 2020-06-02 09:26:01
 */
@Mapper
public interface PaymentRequestDao {

	PaymentRequestDO get(String paymentRequestId);
	
	List<PaymentRequestDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(PaymentRequestDO paymentRequest);
	
	int update(PaymentRequestDO paymentRequest);
	
	int remove(String payment_request_id);
	
	int batchRemove(String[] paymentRequestIds);
}
