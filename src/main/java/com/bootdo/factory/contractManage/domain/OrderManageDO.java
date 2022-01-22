package com.bootdo.factory.contractManage.domain;

import com.bootdo.common.annotation.Log;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Date;



/**
 * 
 * 
 * @author xgq
 * @email 1992lcg@163.com
 * @date 2020-03-11 16:26:25
 */
public class OrderManageDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//订单ID
	private String orderId;
	//订单编号
	@Log(value = "订单编号")
	private String orderCode;
	//订单名称
	@Log(value = "订单名称")
	private String orderName;
	//订单类型
	private String  orderType;
	//订单负责人
	private String  orderManagerId;
	//签订日期
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
	private Date orderSigningDate;
	//开始日期
	@Log(value = "开始日期")
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
	private Date  orderStartDate;
	//截止日期
	@Log(value = "截止日期")
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
	private Date  orderDeadline;
	//订单期次
	@Log(value = "订单期次")
	private String  orderPeriod;
	//发件人
	private String orderSender;
	//订单状态
	@Log(value = "订单状态")
	private String state;
	//发货地
	private String senderPlace;
	//合同编号
	private String contractId;
	//总金额
	private Double totalSum;
	//需求商
	private String demandId;
	private String demandName;

	public String getDemandName() {
		return demandName;
	}

	public void setDemandName(String demandName) {
		this.demandName = demandName;
	}

	public String getDemandId() {
		return demandId;
	}

	public void setDemandId(String demandId) {
		this.demandId = demandId;
	}

	//备注
	private String remark;
	//删除标志
	private Integer isDeleted;
	//附件
	private String files;

	/**
	 * 获取：订单ID
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * 设置：订单ID
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * 获取：订单编号
	 */
	public String getOrderCode() {
		return orderCode;
	}

	/**
	 * 设置：订单编号
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	/**
	 * 获取：订单名称
	 */
	public String getOrderName() {
		return orderName;
	}

	/**
	 * 设置：订单名称
	 */
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	/**
	 * 获取：订单类型
	 */
	public String getOrderType() {
		return  orderType;
	}

	/**
	 * 设置：订单类型
	 */
	public void setOrderType(String  orderType) {
		this. orderType =  orderType;
	}

	/**
	 * 获取：订单负责人
	 */
	public String getOrderManagerId() {
		return  orderManagerId;
	}

	/**
	 * 设置：订单负责人
	 */
	public void setOrderManagerId(String  orderManagerId) {
		this. orderManagerId =  orderManagerId;
	}

	/**
	 * 获取：签订日期
	 */
	public Date getOrderSigningDate() {
		return orderSigningDate;
	}

	/**
	 * 设置：签订日期
	 */
	public void setOrderSigningDate(Date orderSigningDate) {
		this.orderSigningDate = orderSigningDate;
	}

	/**
	 * 获取：开始日期
	 */
	public Date getOrderStartDate() {
		return  orderStartDate;
	}

	/**
	 * 设置：开始日期
	 */
	public void setOrderStartDate(Date  orderStartDate) {
		this. orderStartDate =  orderStartDate;
	}

	/**
	 * 获取：截止日期
	 */
	public Date getOrderDeadline() {
		return  orderDeadline;
	}

	/**
	 * 设置：截止日期
	 */
	public void setOrderDeadline(Date  orderDeadline) {
		this. orderDeadline =  orderDeadline;
	}

	/**
	 * 获取：订单期次
	 */
	public String getOrderPeriod() {
		return  orderPeriod;
	}

	/**
	 * 设置：订单期次
	 */
	public void setOrderPeriod(String  orderPeriod) {
		this. orderPeriod =  orderPeriod;
	}

	/**
	 * 获取：发件人
	 */
	public String getOrderSender() {
		return orderSender;
	}

	/**
	 * 设置：发件人
	 */
	public void setOrderSender(String orderSender) {
		this.orderSender = orderSender;
	}

	/**
	 * 获取：订单状态
	 */
	public String getState() {
		return state;
	}

	/**
	 * 设置：订单状态
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 获取：发货地
	 */
	public String getSenderPlace() {
		return senderPlace;
	}

	/**
	 * 设置：发货地
	 */
	public void setSenderPlace(String senderPlace) {
		this.senderPlace = senderPlace;
	}

	/**
	 * 获取：合同编号
	 */
	public String getContractId() {
		return contractId;
	}

	/**
	 * 设置：合同编号
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	/**
	 * 获取：总金额
	 */
	public Double getTotalSum() {
		return totalSum;
	}

	/**
	 * 设置：总金额
	 */
	public void setTotalSum(Double totalSum) {
		this.totalSum = totalSum;
	}

	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取：删除标志
	 */
	public Integer getIsDeleted() {
		return isDeleted;
	}

	/**
	 * 设置：删除标志
	 */
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
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
}
