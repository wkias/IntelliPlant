package com.bootdo.factory.reimburse.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * @author willice
 * @email willicen@qq.com
 * @date 2020-05-25 22:39:14
 */
public class ReimbursementDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //报销ID
    private String reimburseId;
    //单据类型
    private Integer invoiceType;
    //单据类型
    private String invoiceTypeName;
    //单据号
    private String invoiceId;
    //产生时间开始
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateFrom;
    //产生时间结束
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateTo;
    //报销事由
    private String reason;
    //备注
    private String note;
    //收款账户
    private String payee;
    //开户银行
    private String bank;
    //银行账号
    private String bankAccount;
    //待报销金额
    private BigDecimal amountToBeReimbursed;
    //待还款金额
    private BigDecimal amountToBeRepaid;
    //累计报销金额
    private BigDecimal cumulativeReimbursementAmount;
    //
    private List<ConsumeDO> consume;
    //
    private List<LoanDO> loan;
    //附件
    private String file;
    //是否为草稿
    private int isDraft;
    //状态
    private Integer state;
    private String stateName;

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    //创建时间
    private Date createTime;
    //创建人
    private Integer creatUser;
    //是否已删除
    private Boolean isDeleted;

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public List<ConsumeDO> getConsume() {
        return consume;
    }

    public void setConsume(List<ConsumeDO> consume) {
        this.consume = consume;
    }

    public List<LoanDO> getLoan() {
        return loan;
    }

    public void setLoan(List<LoanDO> loan) {
        this.loan = loan;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    /**
     * 获取：报销ID
     */
    public String getReimburseId() {
        return reimburseId;
    }

    /**
     * 设置：报销ID
     */
    public void setReimburseId(String reimburseId) {
        this.reimburseId = reimburseId;
    }

    /**
     * 获取：单据类型
     *
     * @return
     */
    public String getInvoiceType() {
        return invoiceType.toString();
    }

    /**
     * 设置：单据类型
     */
    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceTypeName() {
        return invoiceTypeName;
    }

    public void setInvoiceTypeName(String invoiceTypeName) {
        this.invoiceTypeName = invoiceTypeName;
    }

    /**
     * 获取：单据号
     */
    public String getInvoiceId() {
        return invoiceId;
    }

    /**
     * 设置：单据号
     */
    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    /**
     * 获取：报销事由
     */
    public String getReason() {
        return reason;
    }

    /**
     * 设置：报销事由
     */
    public void setReason(String reason) {
        this.reason = reason;
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
     * 获取：收款账户
     */
    public String getPayee() {
        return payee;
    }

    /**
     * 设置：收款账户
     */
    public void setPayee(String payee) {
        this.payee = payee;
    }

    /**
     * 获取：开户银行
     */
    public String getBank() {
        return bank;
    }

    /**
     * 设置：开户银行
     */
    public void setBank(String bank) {
        this.bank = bank;
    }

    /**
     * 获取：银行账号
     */
    public String getBankAccount() {
        return bankAccount;
    }

    /**
     * 设置：银行账号
     */
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    /**
     * 获取：待报销金额
     */
    public BigDecimal getAmountToBeReimbursed() {
        return amountToBeReimbursed;
    }

    /**
     * 设置：待报销金额
     */
    public void setAmountToBeReimbursed(BigDecimal amountToBeReimbursed) {
        this.amountToBeReimbursed = amountToBeReimbursed;
    }

    /**
     * 获取：待还款金额
     */
    public BigDecimal getAmountToBeRepaid() {
        return amountToBeRepaid;
    }

    /**
     * 设置：待还款金额
     */
    public void setAmountToBeRepaid(BigDecimal amountToBeRepaid) {
        this.amountToBeRepaid = amountToBeRepaid;
    }

    /**
     * 获取：累计报销金额
     */
    public BigDecimal getCumulativeReimbursementAmount() {
        return cumulativeReimbursementAmount;
    }

    /**
     * 设置：累计报销金额
     */
    public void setCumulativeReimbursementAmount(BigDecimal cumulativeReimbursementAmount) {
        this.cumulativeReimbursementAmount = cumulativeReimbursementAmount;
    }

    /**
     * 获取：附件
     */
    public String getFile() {
        return file;
    }

    /**
     * 设置：附件
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * 获取：是否为草稿
     */
    public int getIsDraft() {
        return isDraft;
    }

    /**
     * 设置：是否为草稿
     */
    public void setIsDraft(int isDraft) {
        this.isDraft = isDraft;
    }

    /**
     * 获取：状态
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置：状态
     */
    public void setState(Integer state) {
        this.state = state;
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

    /**
     * 获取：创建人
     */
    public Integer getCreatUser() {
        return creatUser;
    }

    /**
     * 设置：创建人
     */
    public void setCreatUser(Integer creatUser) {
        this.creatUser = creatUser;
    }

    /**
     * 获取：是否已删除
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置：是否已删除
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
