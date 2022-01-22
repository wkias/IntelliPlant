package com.bootdo.factory.reimburse.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-06-01 19:16:47
 */
public class ConsumeDO implements Serializable {
	private static final long serialVersionUID = 1L;

    //消费明细ID
    private String consumeId;
    //报销ID
    private String reimburseId;
    //消费类型
    private String consumeType;
    //金额
    private BigDecimal amount;
    //付款人
    private String payer;
    //发生时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date time;
    //发票数量
    private Integer invoiceNum;
    //备注
    private String note;
    //附件
    private String file;

    /**
     * 设置：消费明细ID
     */
    public void setConsumeId(String consumeId) {
        this.consumeId = consumeId;
    }

    /**
     * 获取：消费明细ID
     */
    public String getConsumeId() {
        return consumeId;
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
     * 设置：消费类型
     */
    public void setConsumeType(String consumeType) {
        this.consumeType = consumeType;
    }

    /**
     * 获取：消费类型
     */
    public String getConsumeType() {
        return consumeType;
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

    /**
     * 获取：付款人
     */
    public String getPayer() {
        return payer;
    }

    /**
     * 设置：付款人
     */
    public void setPayer(String payer) {
        this.payer = payer;
    }

    /**
     * 获取：发生时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置：发生时间
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * 设置：发票数量
     */
    public void setInvoiceNum(Integer invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    /**
     * 获取：发票数量
     */
    public Integer getInvoiceNum() {
        return invoiceNum;
    }

    /**
     * 设置：备注
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * 获取：备注
     */
    public String getNote() {
        return note;
    }

    /**
     * 设置：附件
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * 获取：附件
     */
    public String getFile() {
        return file;
    }
}
