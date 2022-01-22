package com.bootdo.factory.reimburse.domain;

import java.io.Serializable;
import java.math.BigDecimal;



/**
 *
 *
 * @author willice
 * @email willicen@qq.com
 * @date 2020-05-29 14:28:56
 */
public class LoanDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //借款ID
    private String loanId;
    //报销ID
    private String reimburseId;
    //表单号
    private String loanCode;
    //状态
    private Integer state;
    //日期
    private String date;
    //事由
    private String reason;
    //金额
    private BigDecimal amount;

    /**
     * 设置：借款ID
     */
    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }
    /**
     * 获取：借款ID
     */
    public String getLoanId() {
        return loanId;
    }
    /**
     * 设置：报销ID
     */
    public void setReimburseId(String reimburseId) {
        this.reimburseId = reimburseId;
    }
    /**
     * 获取：报销ID
     */
    public String getReimburseId() {
        return reimburseId;
    }
    /**
     * 设置：表单号
     */
    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode;
    }
    /**
     * 获取：表单号
     */
    public String getLoanCode() {
        return loanCode;
    }
    /**
     * 设置：状态
     */
    public void setState(Integer state) {
        this.state = state;
    }
    /**
     * 获取：状态
     */
    public Integer getState() {
        return state;
    }

    /**
     * 获取：日期
     */
    public String getDate() {
        return date;
    }

    /**
     * 设置：日期
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 设置：事由
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * 获取：事由
     */
    public String getReason() {
        return reason;
    }
    /**
     * 设置：金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    /**
     * 获取：金额
     */
    public BigDecimal getAmount() {
        return amount;
    }
}
