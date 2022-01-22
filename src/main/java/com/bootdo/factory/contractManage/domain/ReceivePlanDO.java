package com.bootdo.factory.contractManage.domain;

import com.bootdo.common.annotation.Log;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * @author willice
 * @email 2782546116@qq.com
 * @date 2020-03-06 12:08:15
 */
public class ReceivePlanDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //回款id
    @Log("回款ID")
    private String receiveId;
    //合同id
    @Log("合同ID")
    private String contractId;
    //合同编号
    @Log("合同编号")
    private String contractCode;
    //合同名称
    @Log("合同名称")
    private String contractName;
    //合同总金额
    @Log("合同总金额")
    private BigDecimal contractAmount;
    //
    private String customer;
    //
    private String demander;
    //
    private String demanderName;
    //签约日期
    @Log("签约日期")
    private Date signDate;
    //回款期次的字典值
    @Log("回款批次-字典值")
    private String receiveBatch;
    //回款期次
    @Log("回款期次")
    private String receiveBatchName;
    //计划回款金额
    @Log("计划回款金额")
    private BigDecimal plannedAmount;
    //计划回款日期
    @Log("计划回款日期")
    private Date plannedDate;
    //状态:0:未回款 1:已回款但未完成 2:已回款
    @Log("状态-字典值")
    private String state;
    //状态：字符串类型
    @Log("状态")
    private String stateString;
    //逾期天数
    @Log("逾期天数")
    private Long daysOverdue;
    //已回款金额
    @Log("已回款金额")
    private BigDecimal receivedAmount;
    //未回款金额
    @Log("未回款金额")
    private BigDecimal unreceivedAmount;
    //创建人
    @Log("创建者编号")
    private Long createUserId;
    //创建者
    @Log("创建者")
    private String createUserName;
    //创建时间
    @Log("创建时间")
    private Timestamp createTime;
    //是否删除
    @Log("是否删除")
    private Boolean isDeleted;
    //备注
    @Log("备注")
    private String note;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getDemanderName() {
        return demanderName;
    }

    public void setDemanderName(String demanderName) {
        this.demanderName = demanderName;
    }

    public String getDemander() {
        return this.demander;
    }

    public void setDemander(final String demander) {
        this.demander = demander;
    }

    /**
     * 获取：回款id
     */
    public String getReceiveId() {
        return receiveId;
    }

    /**
     * 设置：回款id
     */
    public void setReceiveId(String receiveId) {
        this.receiveId = receiveId;
    }

    /**
     * 获取：合同id
     */
    public String getContractId() {
        return contractId;
    }

    /**
     * 设置：合同id
     */
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    /**
     * 获取：合同编号
     */
    public String getContractCode() {
        return contractCode;
    }

    /**
     * 设置：合同编号
     */
    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    /**
     * 获取：合同名称
     */
    public String getContractName() {
        return contractName;
    }

    /**
     * 设置：合同名称
     */
    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    /**
     * 获取：合同总金额
     */
    public BigDecimal getContractAmount() {
        return contractAmount;
    }

    /**
     * 设置：合同总金额
     */
    public void setContractAmount(BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
    }

    /**
     * 获取：签约日期
     */
    public Date getSignDate() {
        return signDate;
    }

    /**
     * 设置：签约日期
     */
    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    /**
     * 获取：回款期次的字典值
     */
    public String getReceiveBatch() {
        return receiveBatch;
    }

    /**
     * 设置：回款期次的字典值
     */
    public void setReceiveBatch(String receiveBatch) {
        this.receiveBatch = receiveBatch;
    }

    /**
     * 获取：回款期次
     */
    public String getReceiveBatchName() {
        return receiveBatchName;
    }

    /**
     * 设置：回款期次
     */
    public void setReceiveBatchName(String receiveBatchName) {
        this.receiveBatchName = receiveBatchName;
    }

    /**
     * 获取：计划回款金额
     */
    public BigDecimal getPlannedAmount() {
        return plannedAmount;
    }

    /**
     * 设置：计划回款金额
     */
    public void setPlannedAmount(BigDecimal plannedAmount) {
        this.plannedAmount = plannedAmount;
    }

    /**
     * 获取：计划回款日期
     */
    public Date getPlannedDate() {
        return plannedDate;
    }

    /**
     * 设置：计划回款日期
     */
    public void setPlannedDate(Date plannedDate) {
        this.plannedDate = plannedDate;
    }

    /**
     * 获取：状态:0:未完成 1:异常 2:已完成
     */
    public String getState() {
        return state;
    }

    /**
     * 设置：状态:0:未完成 1:异常 2:已完成
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取：状态：未完成 异常 已完成
     */
    public String getStateString() {
        return stateString;
    }

    /**
     * 设置：状态:未完成 异常 已完成
     */
    public void setStateString(String stateString) {
        this.stateString = stateString;
    }

    /**
     * 获取：逾期天数
     */
    public Long getDaysOverdue() {
        return daysOverdue;
    }

    /**
     * 设置：逾期天数
     */
    public void setDaysOverdue(Long daysOverdue) {
        this.daysOverdue = daysOverdue;
    }

    /**
     * 获取：已回款金额
     */
    public BigDecimal getReceivedAmount() {
        return receivedAmount;
    }

    /**
     * 设置：已回款金额
     */
    public void setReceivedAmount(BigDecimal receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    /**
     * 获取：未回款金额
     */
    public BigDecimal getUnreceivedAmount() {
        return unreceivedAmount;
    }

    /**
     * 设置：未回款金额
     */
    public void setUnreceivedAmount(BigDecimal unreceivedAmount) {
        this.unreceivedAmount = unreceivedAmount;
    }

    /**
     * 获取：创建人
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置：创建人
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取：创建人姓名
     */
    public String getCreateUserName() {
        return createUserName;
    }

    /**
     * 设置：创建人姓名
     */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    /**
     * 获取：创建时间
     */
    public Timestamp getCreateTime() {
        return createTime;
    }

    /**
     * 设置：创建时间
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：是否删除
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置：是否删除
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 获取：备注
     */
    public String getNote() {
        return note;
    }

    /**
     * 设置：备注
     */
    public void setNote(String note) {
        this.note = note;
    }
}
