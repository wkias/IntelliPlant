package com.bootdo.app.office;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.factory.myOffice.domain.BankAccountDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BankAccount {
    @Autowired
    com.bootdo.factory.service.BankAccountService bankAccountService;

    @GetMapping("app/getBankAccount")
    List<BankAccountDO> getBankAccount(){
        String userId= ShiroUtils.getUserId()+"";
        Map<String,Object> map=new HashMap<>();
        map.put("createUserId",userId);
        return bankAccountService.list(map);
    }
}
