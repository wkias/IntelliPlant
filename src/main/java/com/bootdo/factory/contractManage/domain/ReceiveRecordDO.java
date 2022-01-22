package com.bootdo.factory.contractManage.domain;

import com.bootdo.common.annotation.Log;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * @author willice
 * @email 2782546116@qq.com
 * @date 2020-03-07 16:38:35
 */
public class ReceiveRecordDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //回款记录ID
    @Log("回款记录ID")
    private String recordId;
    //回款计划ID
    @Log("回款计划ID")
    private String planId;
    //合同ID
    @Log("合同ID")
    private String contractId;
    //合同编号
    @Log("合同编号")
    private String contractCode;
    //合同名称
    @Log("合同名称")
    private String contractName;
    //合同类型
    @Log("合同类型")
    private String contractType;
    //
    private String demander;
    //
    private String demanderName;
    //回款计划期次字典值
    @Log("回款计划期次字典值")
    private String planBatch;
    //回款计划期次
    @Log("回款计划期次")
    private String planBatchName;
    //回款记录期次字典值
    @Log("回款记录期次字典值")
    private String recordBatch;
    @Log("计划回款日期")
    private Date plannedDate;
    //计划回款状态:0:未回款 1:已回款但未完成 2:已回款
    private String state;
    //计划回款状态：字符串类型
    @Log("计划回款状态")
    private String stateString;
    //回款记录期次
    @Log("回款记录期次")
    private String recordBatchName;
    //回款类型字典值
    @Log("回款类型字典值")
    private String receiveType;
    //回款类型
    @Log("回款类型")
    private String receiveTypeName;
    //回款金额
    @Log("回款金额")
    private BigDecimal receiveAmount;
    //回款方式字典值
    @Log("回款方式字典值")
    private String paymentMethod;
    //回款方式
    @Log("回款方式")
    private String paymentMethodName;
    //回款日期
    @Log("回款日期")
    private Date date;
    //收款人 用户编号
    @Log("收款人ID")
    private Long payee;
    //收款人 姓名
    @Log("收款人姓名")
    private String payeeName;
    //文件
    private String file;
    //备注
    @Log("备注")
    private String note;
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

    public Date getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(Date plannedDate) {
        this.plannedDate = plannedDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateString() {
        return stateString;
    }

    public void setStateString(String stateString) {
        this.stateString = stateString;
    }

    /**
     * 获取：回款记录编号
     */
    public String getRecordId() {
        return recordId;
    }

    /**
     * 设置：回款记录编号
     */
    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    /**
     * 获取：回款计划编号
     */
    public String getPlanId() {
        return planId;
    }

    /**
     * 设置：回款计划编号
     */
    public void setPlanId(String planId) {
        this.planId = planId;
    }

    /**
     * 获取：合同编号
     */
    public String getContractId() {
        return contractId;
    }

    /**
     * 设置：合同编号
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
     * 获取：合同类型
     */
    public String getContractType() {
        return contractType;
    }

    /**
     * 设置：合同类型
     */
    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    /**
     * 获取：回款计划期次字典值
     */
    public String getPlanBatch() {
        return planBatch;
    }

    /**
     * 设置：回款计划期次字典值
     */
    public void setPlanBatch(String planBatch) {
        this.planBatch = planBatch;
    }

    /**
     * 获取：回款计划期次
     */
    public String getPlanBatchName() {
        return planBatchName;
    }

    /**
     * 设置：回款计划期次
     */
    public void setPlanBatchName(String planBatchName) {
        this.planBatchName = planBatchName;
    }

    /**
     * 获取：回款记录期次字典值
     */
    public String getRecordBatch() {
        return recordBatch;
    }

    /**
     * 设置：回款记录期次字典值
     */
    public void setRecordBatch(String recordBatch) {
        this.recordBatch = recordBatch;
    }

    /**
     * 获取：回款记录期次
     */
    public String getRecordBatchName() {
        return recordBatchName;
    }

    /**
     * 设置：回款记录期次
     */
    public void setRecordBatchName(String recordBatchName) {
        this.recordBatchName = recordBatchName;
    }

    /**
     * 获取：回款类型
     */
    public String getReceiveType() {
        return receiveType;
    }

    /**
     * 设置：回款类型
     */
    public void setReceiveType(String receiveType) {
        this.receiveType = receiveType;
    }

    /**
     * 获取：回款类型
     */
    public String getReceiveTypeName() {
        return receiveTypeName;
    }

    /**
     * 设置：回款类型
     */
    public void setReceiveTypeName(String receiveTypeName) {
        this.receiveTypeName = receiveTypeName;
    }

    /**
     * 获取：回款金额
     */
    public BigDecimal getReceiveAmount() {
        return receiveAmount;
    }

    /**
     * 设置：回款金额
     */
    public void setReceiveAmount(BigDecimal receiveAmount) {
        this.receiveAmount = receiveAmount;
    }

    /**
     * 获取：付款方式字典值
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * 设置：付款方式字典值
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * 获取：付款方式
     */
    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    /**
     * 设置：付款方式
     */
    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    /**
     * 获取：回款日期
     */
    public Date getDate() {
        return date;
    }

    /**
     * 设置：回款日期
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * 获取：收款人 用户编号
     */
    public Long getPayee() {
        return payee;
    }

    /**
     * 设置：收款人 用户编号
     */
    public void setPayee(Long payee) {
        this.payee = payee;
    }

    /**
     * 获取：收款人 姓名
     */
    public String getPayeeName() {
        return payeeName;
    }

    /**
     * 设置：收款人 姓名
     */
    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
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
}
