package com.bootdo.factory.warehouseManagement.domain;

import com.bootdo.factory.productManage.domain.ProductDetailDO;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-03-18 21:40:50
 */
public class PurchaseDetailDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//关系表主键ID
	private String id;
	//采购订单ID
	private String orderId;
	//物品明细ID
	private String productId;
	//物品明显
	private ProductDetailDO productDetail;
	//数量
	private Double number;
	//金额
	private Double totalMoney;
	//删除标记
	private Boolean isDeleted;
	//创建用户ID
	private String createUserId;
	//创建时间
	private Date createTime;

	public ProductDetailDO getProductDetail() {
		return this.productDetail;
	}

	public void setProductDetail(final ProductDetailDO productDetail) {
		this.productDetail = productDetail;
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
	 * 设置：采购订单ID
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：采购订单ID
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * 设置：物品明细ID
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * 获取：物品明细ID
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * 设置：数量
	 */
	public void setNumber(Double number) {
		this.number = number;
	}
	/**
	 * 获取：数量
	 */
	public Double getNumber() {
		return number;
	}
	/**
	 * 设置：金额
	 */
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	/**
	 * 获取：金额
	 */
	public Double getTotalMoney() {
		return totalMoney;
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
	 * 设置：创建用户ID
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：创建用户ID
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
