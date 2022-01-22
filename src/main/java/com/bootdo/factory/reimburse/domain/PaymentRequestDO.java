package com.bootdo.factory.reimburse.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * @author Casper
 * @email 1992lcg@163.com
 * @date 2020-06-02 09:26:01
 */
public class PaymentRequestDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //请款申请ID
    private String paymentRequestId;
    //表单号
    private String formNumber;
    //请款人
    private String userId;
    //业务事由
    private String businessReasons;
    //备注
    private String remarks;
    //请款金额（元）
    private String claimAmount;
    //收款账户
    private String collectionAccount;
    //开户银行
    private String bankName;
    //银行账户
    private String bankAccount;
    //汇总
    private String summary;
    //单据状态
    private String paymentState;
    //附件
    private String files;
    //是否为草稿
    private Boolean isDraft;
    //删除标记
    private Boolean isDeleted;
    //创建者
    private String createUserId;
    //创建时间
    private Date createTime;
    //员工名称
    private String userName;

    /**
     * 获取：请款申请ID
     */
    public String getPaymentRequestId() {
        return paymentRequestId;
    }

    /**
     * 设置：请款申请ID
     */
    public void setPaymentRequestId(String paymentRequestId) {
        this.paymentRequestId = paymentRequestId;
    }

    /**
     * 获取：表单号
     */
    public String getFormNumber() {
        return formNumber;
    }

    /**
     * 设置：表单号
     */
    public void setFormNumber(String formNumber) {
        this.formNumber = formNumber;
    }

    /**
     * 获取：请款人
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置：请款人
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取：业务事由
     */
    public String getBusinessReasons() {
        return businessReasons;
    }

    /**
     * 设置：业务事由
     */
    public void setBusinessReasons(String businessReasons) {
        this.businessReasons = businessReasons;
    }

    /**
     * 获取：备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置：备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 获取：请款金额（元）
     */
    public String getClaimAmount() {
        return claimAmount;
    }

    /**
     * 设置：请款金额（元）
     */
    public void setClaimAmount(String claimAmount) {
        this.claimAmount = claimAmount;
    }

    /**
     * 获取：收款账户
     */
    public String getCollectionAccount() {
        return collectionAccount;
    }

    /**
     * 设置：收款账户
     */
    public void setCollectionAccount(String collectionAccount) {
        this.collectionAccount = collectionAccount;
    }

    /**
     * 获取：开户银行
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * 设置：开户银行
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * 获取：银行账户
     */
    public String getBankAccount() {
        return bankAccount;
    }

    /**
     * 设置：银行账户
     */
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    /**
     * 获取：汇总
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置：汇总
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * 获取：单据状态
     */
    public String getPaymentState() {
        return paymentState;
    }

    /**
     * 设置：单据状态
     */
    public void setPaymentState(String paymentState) {
        this.paymentState = paymentState;
    }

    /**
     * 获取：附件
     */
    public String getFiles() {
        return files;
    }

    /**
     * 设置：附件
     */
    public void setFiles(String files) {
        this.files = files;
    }

    /**
     * 获取：是否为草稿
     */
    public Boolean getIsDraft() {
        return isDraft;
    }

    /**
     * 设置：是否为草稿
     */
    public void setIsDraft(Boolean isDraft) {
        this.isDraft = isDraft;
    }

    /**
     * 获取：删除标记
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置：删除标记
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 获取：创建者
     */
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置：创建者
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置：创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }
}
