package com.bootdo.factory.reimburse.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;



/**
 * 
 * 
 * @author zrj
 * @email 1992lcg@163.com
 * @date 2020-05-27 08:45:58
 */
public class LoanApplicationDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//借款申请ID
	private String loanApplicationId;
	//银行账户ID
	private String bankAccountId;
	//借款人ID
	private String userId;
	//单据状态
	private String loanState;
	//单据编号
	private String loanCode;
	//申请事由
    private String loanPurpose;
    //申请时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date loanDate;
    //还款日期
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date repaymentDate;
    //借款金额
    private Double loanAccount;
	//备注
	private String note;
	//附件
	private String fileName;
	//删除标记
	private Boolean isDeleted;
	//创建者
	private String createUserId;
	//创建日期
	private Date createTime;
    //员工名称
	private String userName;
    //收款账户
    private String collectionAccount;
    //银行账户
    private String bankAccount;
    //开户行
    private String bankName;
	//汇总
	private Double summary;

    /**
     * 设置：收款账户
     */
    public void setCollectionAccount(String collectionAccount) {
        this.collectionAccount = collectionAccount;
    }
    /**
     * 获取：收款账户
     */
    public String getCollectionAccount() {
        return collectionAccount;
    }
    /**
     * 设置：银行账户
     */
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
    /**
     * 获取：银行账户
     */
    public String getBankAccount() {
        return bankAccount;
    }
    /**
     * 设置：开户行
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    /**
     * 获取：开户行
     */
    public String getBankName() {
        return bankName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }
	/**
	 * 设置：借款申请ID
	 */
	public void setLoanApplicationId(String loanApplicationId) {
		this.loanApplicationId = loanApplicationId;
	}
	/**
	 * 获取：借款申请ID
	 */
	public String getLoanApplicationId() {
		return loanApplicationId;
	}
	/**
	 * 设置：银行账户ID
	 */
	public void setBankAccountId(String bankAccountId) {
		this.bankAccountId = bankAccountId;
	}
	/**
	 * 获取：银行账户ID
	 */
	public String getBankAccountId() {
		return bankAccountId;
	}
	/**
	 * 设置：借款人ID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：借款人ID
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：单据状态
	 */
	public void setLoanState(String loanState) {
		this.loanState = loanState;
	}
	/**
	 * 获取：单据状态
	 */
	public String getLoanState() {
		return loanState;
	}
	/**
	 * 设置：单据编号
	 */
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	/**
	 * 获取：单据编号
	 */
	public String getLoanCode() {
		return loanCode;
	}
	/**
	 * 设置：申请事由
	 */
	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}
	/**
	 * 获取：申请事由
	 */
	public String getLoanPurpose() {
		return loanPurpose;
	}
	/**
	 * 设置：申请时间
	 */
	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}
	/**
	 * 获取：申请时间
	 */
	public Date getLoanDate() {
		return loanDate;
	}
	/**
	 * 设置：还款日期
	 */
	public void setRepaymentDate(Date repaymentDate) {
		this.repaymentDate = repaymentDate;
	}
	/**
	 * 获取：还款日期
	 */
	public Date getRepaymentDate() {
		return repaymentDate;
	}
	/**
	 * 设置：借款金额
	 */
	public void setLoanAccount(Double loanAccount) {
		this.loanAccount = loanAccount;
	}
	/**
	 * 获取：借款金额
	 */
	public Double getLoanAccount() {
		return loanAccount;
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
    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }
	/**
	 * 设置：删除标记
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	/**
	 * 获取：删除标记
	 */
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	/**
	 * 设置：创建者
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：创建者
	 */
	public String getCreateUserId() {
		return createUserId;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
    /**
     * 获取：汇总
     */
    public Double getSummary() {
        return summary;
    }

    /**
     * 设置：汇总
     */
    public void setSummary(Double summary) {
        this.summary = summary;
    }
}
