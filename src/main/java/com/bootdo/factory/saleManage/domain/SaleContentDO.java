package com.bootdo.factory.saleManage.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xgq
 * @email 1992lcg@163.com
 * @date 2020-03-29 23:21:55
 */
public class SaleContentDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String saleContentId;
	//
	private String saleId;
	//
	private String productId;
	//
	private Integer count;
	//
	private String productName;
	//
	private String productType;
	//
	private String productCode;
	//
	private String quantityUnit;
	private String quantityUnitName;
	private String deadline;
	private String storehouse;

	public String getQuantityUnitName() {
		return quantityUnitName;
	}

	public void setQuantityUnitName(String quantityUnitName) {
		this.quantityUnitName = quantityUnitName;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getStorehouse() {
		return storehouse;
	}

	public void setStorehouse(String storehouse) {
		this.storehouse = storehouse;
	}

	/**
	 * 获取：
	 */
	public String getSaleContentId() {
		return saleContentId;
	}

	/**
	 * 设置：
	 */
	public void setSaleContentId(String saleContentId) {
		this.saleContentId = saleContentId;
	}

	/**
	 * 获取：
	 */
	public String getSaleId() {
		return saleId;
	}

	/**
	 * 设置：
	 */
	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	/**
	 * 获取：
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * 设置：
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * 获取：
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * 设置：
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * 获取：
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * 设置：
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * 获取：
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * 设置：
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * 获取：
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * 设置：
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * 获取：
	 */
	public String getQuantityUnit() {
		return quantityUnit;
	}

	/**
	 * 设置：
	 */
	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}
}
