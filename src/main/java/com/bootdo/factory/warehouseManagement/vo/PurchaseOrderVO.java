package com.bootdo.factory.warehouseManagement.vo;

import com.bootdo.factory.warehouseManagement.domain.PurchaseDetailDO;
import com.bootdo.factory.warehouseManagement.domain.PurchaseOrderDO;

import java.util.List;

public class PurchaseOrderVO extends PurchaseOrderDO {
    //申请部门名
    private String applyDeptName;
    //负责人名
    private String dutyPersonName;
    //采购状态名
    private String purchaseStateName;
    //业务类型名
    private String businessTypeName;
    //当采购单位为供应商时，此变量存储供应商ID
    private String supplierId;
    //物资明细
    private List<PurchaseDetailDO> details;

    public String getApplyDeptName() {
        return this.applyDeptName;
    }

    public void setApplyDeptName(final String applyDeptName) {
        this.applyDeptName = applyDeptName;
    }


    public String getDutyPersonName() {
        return this.dutyPersonName;
    }

    public void setDutyPersonName(final String dutyPersonName) {
        this.dutyPersonName = dutyPersonName;
    }

    public String getPurchaseStateName() {
        return this.purchaseStateName;
    }

    public void setPurchaseStateName(final String purchaseStateName) {
        this.purchaseStateName = purchaseStateName;
    }

    public String getBusinessTypeName() {
        return this.businessTypeName;
    }

    public void setBusinessTypeName(final String businessTypeName) {
        this.businessTypeName = businessTypeName;
    }

    public String getSupplierId() {
        return this.supplierId;
    }

    public void setSupplierId(final String supplierId) {
        this.supplierId = supplierId;
    }

    public List<PurchaseDetailDO> getDetails() {
        return this.details;
    }

    public void setDetails(final List<PurchaseDetailDO> details) {
        this.details = details;
    }
}
