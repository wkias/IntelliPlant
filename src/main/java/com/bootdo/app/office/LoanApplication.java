package com.bootdo.app.office;

import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.factory.reimburse.domain.LoanApplicationDO;
import com.bootdo.factory.reimburse.service.LoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class LoanApplication {
    @Autowired
    LoanApplicationService loanApplicationService;

    @GetMapping("app/getLoanApplyList")
    List<LoanApplicationDO> getLoanApplyList(){
        Map<String,Object> map=new HashMap<>();
        map.put("userId", ShiroUtils.getUserId());
        return loanApplicationService.list(map);
    }
    @PostMapping("app/loanApply/add")
    R addLoanApply(@RequestBody LoanApplicationDO loanApplication){
        System.out.println(loanApplication);
        loanApplication.setLoanApplicationId(UUID.randomUUID().toString());
        loanApplication.setUserId(ShiroUtils.getUserId()+"");
        loanApplication.setLoanCode(UUID.randomUUID().toString());
        loanApplication.setLoanDate(new Date());
        loanApplication.setIsDeleted(false);
        loanApplication.setCreateUserId(ShiroUtils.getUserId()+"");
        loanApplication.setCreateTime(new Date());
        loanApplicationService.save(loanApplication);
        return R.ok();
    }
}
