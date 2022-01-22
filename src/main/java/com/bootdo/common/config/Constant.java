package com.bootdo.common.config;

public class Constant {
    //演示系统账户
    public static String DEMO_ACCOUNT = "test";
    //自动去除表前缀
    public static String AUTO_REOMVE_PRE = "true";
    //停止计划任务
    public static String STATUS_RUNNING_STOP = "stop";
    //开启计划任务
    public static String STATUS_RUNNING_START = "start";
    //通知公告阅读状态-未读
    public static String OA_NOTIFY_READ_NO = "0";
    //通知公告阅读状态-已读
    public static int OA_NOTIFY_READ_YES = 1;
    //部门根节点id
    public static Long DEPT_ROOT_ID = 0l;
    //缓存方式
    public static String CACHE_TYPE_REDIS = "redis";

    public static String LOG_ERROR = "error";

    //字典值 设备类型
    public static String EQUIPMENT_TYPE = "equipment_type";

    //字典值 开票类型
    public static String INVOICE_TYPE = "invoice_type";

    //字典值 物流公司
    public static String LOGISTICS_COMPANY = "logistics_company";

    //字典值 重量单位
    public static String WEIGHT_UNIT = "weight_unit";

    //字典值 设备维修
    public static String REPAIR_STATE = "equipment_repair_state";

    //字典值 点检周期
    public static String CHECK_CYCLE = "check_cycle";
    //字典值 设备点检
    public static String CHECK_STATE = "equipment_check_state";
    //字典值 流程类型
    public static String FLOW_TYPE = "workflow_type";
    //字典值 合同类型
    public static String CONTRACT_TYPE="contract_type";
    //字典值 合同状态
    public static String CONTRACT_STATE="contract_state";
    //字典值  采购状态
    public static String PURCHASE_STATE="purchase_state";
    //字典值   业务类型——采购
    public static String PURCHASE_BUSINESS_TYPE="purchase_business_type";
    //字典值  	生产计划优先级
    public static String PRODUCT_PLAN_PRIORITY="product_plan_priority";
    //字典值       生产计划状态
    public static String PRODUCT_PLAN_STATE="product_plan_state";
    //状态常量 流程状态
    public static String[] FLOW_STATE= {"审批中","已通过","已拒绝"};
//字典值
    public static String WORK_TIME_TYPE ="work_time_type";
    public static String HOUR_PERIOD ="hour_period";
    public static String UNIT ="unit";
    public static String WEIGHTUNIT ="weightUnit";


}
