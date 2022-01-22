package com.bootdo.app.office;

import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.factory.reimburse.domain.PaymentRequestDO;
import com.bootdo.factory.reimburse.service.PaymentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author willice
 * @email willicen@qq.com
 * @date 2020年6月6日 23点26分
 */

@RestController
@RequestMapping("/app/paymentRequest")
public class PaymentRequest {
    @Autowired
    PaymentRequestService paymentRequestService;

    @GetMapping("/list")
    List<PaymentRequestDO> list(){
        Map<String,Object> map=new HashMap<>();
        map.put("userId", ShiroUtils.getUserId());
        return paymentRequestService.list(map);
    }
    @PostMapping("/add")
    R add(@RequestBody PaymentRequestDO paymentRequest){
        Long userId = ShiroUtils.getUserId();
        paymentRequest.setFormNumber(UUID.randomUUID().toString());
        paymentRequest.setUserId(userId.toString());
        paymentRequest.setCreateUserId(userId.toString());
        paymentRequest.setCreateTime(new Date());
        paymentRequest.setIsDeleted(false);
        paymentRequestService.save(paymentRequest);
        return R.ok();
    }
}
