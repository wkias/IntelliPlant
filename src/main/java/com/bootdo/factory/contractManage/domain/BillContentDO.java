package com.bootdo.factory.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author Casper
 * @email 1992lcg@163.com
 * @date 2020-04-18 11:08:28
 */
public class BillContentDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//关系表主键ID
	private String id;
	//开票ID
	private String invoiceId;
	//产品名称
	private String productName;
	//规格型号
	private String specification;
	//单位
	private String unit;
	//数量
	private String quantity;
	//单价
	private String unitPrice;
	//金额
	private String amount;
	//税率
	private String taxRate;
	//税额
	private String taxAmount;
	//删除标记；默认值0；0：未删除；1：已删除
	private Boolean isDeleted;
	//创建者
	private String createUserId;
	//创建时间
	private Date createTime;

	private String billContent;

	public void setBillContent(String billContent) {
		this.billContent = billContent;
	}
	public String getBillContent() {
		return billContent;
	}

	/**
	 * 设置：关系表主键ID
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：关系表主键ID
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：开票ID
	 */
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	/**
	 * 获取：开票ID
	 */
	public String getInvoiceId() {
		return invoiceId;
	}
	/**
	 * 设置：产品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * 获取：产品名称
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * 设置：规格型号
	 */
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	/**
	 * 获取：规格型号
	 */
	public String getSpecification() {
		return specification;
	}
	/**
	 * 设置：单位
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * 获取：单位
	 */
	public String getUnit() {
		return unit;
	}
	/**
	 * 设置：数量
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	/**
	 * 获取：数量
	 */
	public String getQuantity() {
		return quantity;
	}
	/**
	 * 设置：单价
	 */
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	/**
	 * 获取：单价
	 */
	public String getUnitPrice() {
		return unitPrice;
	}
	/**
	 * 设置：金额
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * 获取：金额
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * 设置：税率
	 */
	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}
	/**
	 * 获取：税率
	 */
	public String getTaxRate() {
		return taxRate;
	}
	/**
	 * 设置：税额
	 */
	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}
	/**
	 * 获取：税额
	 */
	public String getTaxAmount() {
		return taxAmount;
	}
	/**
	 * 设置：删除标记；默认值0；0：未删除；1：已删除
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	/**
	 * 获取：删除标记；默认值0；0：未删除；1：已删除
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
