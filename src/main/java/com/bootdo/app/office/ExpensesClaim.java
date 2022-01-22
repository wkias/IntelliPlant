package com.bootdo.app.office;

import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.factory.reimburse.controller.ReimbursementController;
import com.bootdo.factory.reimburse.domain.ConsumeDO;
import com.bootdo.factory.reimburse.domain.LoanApplicationDO;
import com.bootdo.factory.reimburse.domain.LoanDO;
import com.bootdo.factory.reimburse.domain.ReimbursementDO;
import com.bootdo.factory.reimburse.service.ReimbursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//import sun.misc.UUDecoder;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class ExpensesClaim {
    @Autowired
    private ReimbursementController reimbursementController;
    @Autowired
    private ReimbursementService reimbursementService;

    @GetMapping("app/getReimbursementList")
    List<ReimbursementDO> getReimbursementList(){
        Map<String,Object> map=new HashMap<>();
        map.put("userId", ShiroUtils.getUserId());
        return reimbursementService.list(map);
    }

    @PostMapping("app/expensesClaim/add")
    R addExpensesClaim(@RequestBody ReimbursementDO reimbursement) throws Exception {
        String reimburseId= UUID.randomUUID().toString();
        reimbursement.setReimburseId(reimburseId);
        reimbursement.setInvoiceId(UUID.randomUUID().toString());
        reimbursement.setCreateTime(new Date());
        reimbursement.setCreatUser(Integer.parseInt(ShiroUtils.getUserId()+""));
        reimbursement.setIsDeleted(false);
        reimbursement.setState(0);
        //处理consume
        for(ConsumeDO consume:reimbursement.getConsume()){
            consume.setConsumeId(UUID.randomUUID().toString());
            consume.setReimburseId(reimburseId);
        }
        //处理loan
        for(LoanDO loan:reimbursement.getLoan()){
            loan.setReimburseId(reimburseId);
        }
        reimbursementService.save(reimbursement);
        return R.ok();
    }
}
