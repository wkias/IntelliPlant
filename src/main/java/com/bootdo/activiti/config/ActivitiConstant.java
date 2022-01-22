package com.bootdo.activiti.config;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class ActivitiConstant {
    public static final String[] ACTIVITI_SALARY = new String[]{"salary", "salary"};
    public static final String[] ACTIVITI_LEAVE = new String[]{"leave_test", "leave_test"};
    public static final Map<String,String> FLOWTYPE_MAPPER = new HashMap<>();
    static {
        FLOWTYPE_MAPPER.put("QJ_FLOW","/act/leaveBill/form");
        FLOWTYPE_MAPPER.put("CG_FLOW","/warehouseManagement/purchaseOrder/form");
    }
}
