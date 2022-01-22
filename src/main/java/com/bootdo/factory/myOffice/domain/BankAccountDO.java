package com.bootdo.factory.myOffice.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author Casper
 * @email 1992lcg@163.com
 * @date 2020-05-25 15:36:18
 */
public class BankAccountDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//银行账户ID
	private String bankAccountId;
	//收款账户
	private String collectionAccount;
	//银行账户
	private String bankAccount;
	//开户行
	private String bankName;
	//删除标记
	private Boolean isDeleted;
	//创建者
	private String createUserId;
	//创建时间
	private Date createTime;

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
}
