package com.bootdo.factory.contractManage.vo;

import com.bootdo.common.annotation.Log;
import com.bootdo.factory.contractManage.domain.OrderContentDO;
import com.bootdo.factory.contractManage.domain.OrderManageDO;

import java.util.List;

public class OrderManageVO extends OrderManageDO {


    //合同编号

    private String contractCode;
    //合同名称
    @Log(value = "合同名称")
    private String contractName;
    @Log(value = "订单负责人")
    private String orderManagerName;

    public String getOrderManagerName() {
        return orderManagerName;
    }

    public void setOrderManagerName(String orderManagerName) {
        this.orderManagerName = orderManagerName;
    }
    //合同类型
    private String contractType;
    //订单内容
    private List<OrderContentDO> batchOrderContentS;

    private List<OrderContentDO> noBatchOrderContentS;

    public List<OrderContentDO> getBatchOrderContentS() {
        return batchOrderContentS;
    }

    public void setBatchOrderContentS(List<OrderContentDO> batchOrderContentS) {
        this.batchOrderContentS = batchOrderContentS;
    }

    public List<OrderContentDO> getNoBatchOrderContentS() {
        return noBatchOrderContentS;
    }

    public void setNoBatchOrderContentS(List<OrderContentDO> noBatchOrderContentS) {
        this.noBatchOrderContentS = noBatchOrderContentS;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

   }
