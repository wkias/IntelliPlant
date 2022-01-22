package com.bootdo.factory.contractManage.domain;

import com.bootdo.factory.productManage.domain.ProductDefinitionDO;
import com.bootdo.factory.productManage.domain.ProductDetailDO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Date;



/**
 * 
 * 
 * @author xgq
 * @email 1992lcg@163.com
 * @date 2020-03-28 16:33:20
 */
public class OrderContentDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//订单内容ID
	private String contentId;
	//产品ID
	private String productId;

	private ProductDefinitionDO productDefinitionDO;

	public ProductDefinitionDO getProductDefinitionDO() {
		return productDefinitionDO;
	}

	public void setProductDefinitionDO(ProductDefinitionDO productDefinitionDO) {
		this.productDefinitionDO = productDefinitionDO;
	}
	//是否分批次  0 不分
	private String isBatched;
	//已发货数
	private Integer complete;

	//数量
	private Integer count;
	//总金额
	private Double totalSum;
	//到货日期
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
	private Date deadline;
	//目的地
	private String destination;
	//仓库
	private String storehouse;
	//订单id
	private String orderId;




	/**
	 * 获取：订单内容ID
	 */
	public String getContentId() {
		return contentId;
	}

	/**
	 * 设置：订单内容ID
	 */
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	/**
	 * 设置：产品ID
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * 获取：产品ID
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * 设置：数量
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * 获取：数量
	 */
	public Integer getCount() {
		return count;
	}
	/**
	 * 设置：总金额
	 */
	public void setTotalSum(Double totalSum) {
		this.totalSum = totalSum;
	}
	/**
	 * 获取：总金额
	 */
	public Double getTotalSum() {
		return totalSum;
	}
	/**
	 * 设置：到货日期
	 */
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	/**
	 * 获取：到货日期
	 */
	public Date getDeadline() {
		return deadline;
	}
	/**
	 * 设置：目的地
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	/**
	 * 获取：目的地
	 */
	public String getDestination() {
		return destination;
	}
	/**
	 * 设置：仓库
	 */
	public void setStorehouse(String storehouse) {
		this.storehouse = storehouse;
	}
	/**
	 * 获取：仓库
	 */
	public String getStorehouse() {
		return storehouse;
	}
	/**
	 * 设置：订单id
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：订单id
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * 获取：是否分批次  0 不分
	 */
	public String getIsBatched() {
		return isBatched;
	}

	/**
	 * 设置：是否分批次  0 不分
	 */
	public void setIsBatched(String isBatched) {
		this.isBatched = isBatched;
	}

	/**
	 * 获取：已发货数
	 */
	public Integer getComplete() {
		return complete;
	}

	/**
	 * 设置：已发货数
	 */
	public void setComplete(Integer complete) {
		this.complete = complete;
	}
}
