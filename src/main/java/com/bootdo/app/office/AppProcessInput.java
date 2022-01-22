package com.bootdo.app.office;

import com.bootdo.common.utils.CodeGenerator;
import com.bootdo.common.utils.R;
import com.bootdo.factory.productManage.controller.CraftDefinitionController;
import com.bootdo.factory.productManage.controller.ProductCraftController;
import com.bootdo.factory.productManage.dao.CraftProcessDao;
import com.bootdo.factory.productManage.domain.*;
import com.bootdo.factory.productManage.service.ProcessInputService;
import com.bootdo.factory.productManage.service.ProductCraftService;
import com.bootdo.factory.productManage.service.ProductPlanService;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;
import org.activiti.engine.impl.bpmn.helper.ScopeUtil;
import org.apache.tomcat.jni.Proc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AppProcessInput {
    @Autowired
    ProductCraftService productCraftService;
    @Autowired
    CraftDefinitionController craftDefinitionController;
    @Autowired
    ProcessInputService processInputService;
    @Autowired
    CodeGenerator codeGenerator;
    @Autowired
    ProductPlanService productPlanService;
    @Autowired
    UserService userService;
    @Autowired
    CraftProcessDao craftProcessDao;

    @GetMapping("app/getProcessList")
    List<CraftProcessDO> getProcessList(@RequestParam Map<String,Object> request){
        Map<String,Object> craftQuery=new HashMap<>();
        craftQuery.put("productId",request.get("productId"));
        List<ProductCraftDO> crafts=productCraftService.list(craftQuery);
        if (crafts.isEmpty()){return null;}
        Map<String,Object> processQuery=new HashMap<>();
        List<CraftProcessDO> process=craftDefinitionController.processList(crafts.get(0).getCraftId());
        return process;
    }

    @PostMapping("app/createComponent")
    R createComponent(@RequestBody List<ProcessInputDO> processInputs){
        String componentCode=codeGenerator.getCodeByType("component");
        for(ProcessInputDO processInput:processInputs){
            processInput.setComponentCode(componentCode);
            processInputService.save(processInput);
        }
        return R.ok("成功生成半成品");
    }

    @GetMapping("app/getComponents")
    List getComponents(String planId,String processId){
        Map<String,Object> map=new HashMap<>();
        map.put("planId",planId);
        map.put("processId",processId);
        map.put("inspecting",'0');
        List<ProcessInputDO> processInputs=processInputService.list(map);
        Map<String,ProcessInputDO> componentMap=new HashMap<>();
        for(ProcessInputDO processInput:processInputs){
            String componentCode=processInput.getComponentCode();
            if(!componentMap.containsKey(componentCode)){
                componentMap.put(componentCode,processInput);
            }
        }
        List<Map<String,Object>> components=new ArrayList<>();
        for(String componentCode:componentMap.keySet()){
            ProcessInputDO processInput=componentMap.get(componentCode);
            Map<String,Object> component=new HashMap<>();
            component.put("id",processInput.getId());
            component.put("componentCode",processInput.getComponentCode());
            Map<String,Object> planQuery=new HashMap<>();
            planQuery.put("planId",planId);
            ProductPlanDO productPlan=productPlanService.list(planQuery).get(0);
            String productName=productPlan.getProductDefinitionDO().getProductName();
            component.put("productName",productName);
            String userId=processInput.getCreateUserId();
            UserDO user=userService.get(Long.parseLong(userId));
            component.put("userName",user.getName());
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            component.put("createTime",sdf.format(processInput.getCreateTime()));
            components.add(component);
        }
        return components;
    }
    /**
     * 部件产出
     * */
    @PostMapping("app/componentOutput")
    R output(String componentCode){
        ProcessInputDO processInput=new ProcessInputDO();
        processInput.setComponentCode(componentCode);
        processInput.setInspecting(true);
        processInputService.update(processInput);
//
//        Map<String,Object> inputQuery=new HashMap<>();
//        inputQuery.put("componentCode",componentCode);
//        ProcessInputDO processInput2=processInputService.list(inputQuery).get(0);
//        Map<String,Object> planQuery=new HashMap<>();
//        planQuery.put("planId",processInput2.getPlanId());
//        ProductPlanDO productPlan=productPlanService.list(planQuery).get(0);
//        String productId=productPlan.getProductDefinitionDO().getProductId();
//        Map<String,Object> proMap=new HashMap<>();
//        proMap.put("productId",productId);
//        List<CraftProcessDO> craftProcessDOS=getProcessList(proMap);
        return R.ok("已产出");
    }
}
