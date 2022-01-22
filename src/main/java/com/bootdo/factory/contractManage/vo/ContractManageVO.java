package com.bootdo.factory.contractManage.vo;

import com.bootdo.common.annotation.Log;
import com.bootdo.factory.contractManage.domain.ContractManageDO;
import com.bootdo.factory.contractManage.domain.CustermerInformationDO;

import java.util.List;
import java.util.Map;

public class ContractManageVO extends ContractManageDO {
    @Log("合同类型")
    private String typeName;
    @Log("合同状态")
    private String stateName;
    @Log("合同负责人")
    private String dutyUserName;
    @Log("承办部门")
    private String dutyDeptName;
    //供应商列表
    private List<ContractTraderVO> suppliers;
    //需求商列表
    private List<ContractTraderVO> demanders;

    public List<ContractTraderVO> getSuppliers() {
        return this.suppliers;
    }

    public void setSuppliers(final List<ContractTraderVO> suppliers) {
        this.suppliers = suppliers;
    }

    public List<ContractTraderVO> getDemanders() {
        return this.demanders;
    }

    public void setDemanders(final List<ContractTraderVO> demanders) {
        this.demanders = demanders;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(final String typeName) {
        this.typeName = typeName;
    }

    public String getStateName() {
        return this.stateName;
    }

    public void setStateName(final String stateName) {
        this.stateName = stateName;
    }

    public String getDutyUserName() {
        return this.dutyUserName;
    }

    public void setDutyUserName(final String dutyUserName) {
        this.dutyUserName = dutyUserName;
    }

    public String getDutyDeptName() {
        return this.dutyDeptName;
    }

    public void setDutyDeptName(final String dutyDeptName) {
        this.dutyDeptName = dutyDeptName;
    }

}
