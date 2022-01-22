package com.bootdo.app.office;

import com.bootdo.factory.contractManage.domain.CustermerInformationDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Suppliers {
    @Autowired
    com.bootdo.factory.contractManage.service.CustermerInformationService customerService;

    @GetMapping("app/getSuppliers")
    List<CustermerInformationDO> getSuppliers(){
        Map<String,Object> map=new HashMap<>();
        map.put("isSupplier",true);//获取供应商
        return customerService.list(map);
    }
}
