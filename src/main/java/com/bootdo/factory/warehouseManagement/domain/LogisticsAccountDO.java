package com.bootdo.factory.warehouseManagement.domain;

import com.bootdo.common.annotation.Log;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Casper
 * @email 1992lcg@163.com
 * @date 2020-03-17 15:33:13
 */
public class LogisticsAccountDO implements Serializable {
    private static final long serialVersionUID = 1L;
    //物流台账ID
    private String logisticsId;
    //关联表单编号
    @Log("关联表单编号")
    private String formId;
    //关联表单类型
    @Log("关联表单类型")
    private String formType;
    //发货单位
    @Log("发货单位")
    private String forwardingUnit;
    //物流台账编号
    @Log("物流台账编号")
    private String logisticsNumber;
    //物流公司
    @Log("物流公司")
    private String logisticsCompany;
    //收货单位
    @Log("收货单位")
    private String receiveUnit;
    //总数量
    @Log("总数量")
    private String totalQuantity;
    //总重量
    @Log("总重量")
    private String totalWeight;
    //总金额
    @Log("总金额")
    private String totalMoney;
    //司机名称
    @Log("司机名称")
    private String driverName;
    //联系电话
    @Log("联系电话")
    private String phoneNumber;
    //车牌号
    @Log("车牌号")
    private String carNumber;
    //驾照
    @Log("驾照")
    private String driverLicense;
    //行驶证
    @Log("行驶证")
    private String driverPermit;
    //重量单位
    @Log("重量单位")
    private String weightUnit;
    //重量
    @Log("重量")
    private String weight;
    //金额
    @Log("金额")
    private String money;
    //备注
    @Log("备注")
    private String remarks;
    //附件
    private String files;
    //删除标记；默认值0；0：未删除；1：已删除
    private Boolean isDeleted;
    //创建者
    private String createUserId;
    //创建时间
    private Date createTime;

    public void setLogisticsId(String logisticsId) {
        this.logisticsId = logisticsId;
    }

    public String getLogisticsId() {
        return logisticsId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public String getFormType() {
        return formType;
    }

    public void setForwardingUnit(String forwardingUnit) {
        this.forwardingUnit = forwardingUnit;
    }

    public String getForwardingUnit() {
        return forwardingUnit;
    }

    public void setLogisticsNumber(String logisticsNumber) {
        this.logisticsNumber = logisticsNumber;
    }

    public String getLogisticsNumber() {
        return logisticsNumber;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setReceiveUnit(String receiveUnit) {
        this.receiveUnit = receiveUnit;
    }

    public String getReceiveUnit() {
        return receiveUnit;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverPermit(String driverPermit) {
        this.driverPermit = driverPermit;
    }

    public String getDriverPermit() {
        return driverPermit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeight() {
        return weight;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getMoney() {
        return money;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public String getFiles() {
        return files;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }
}
