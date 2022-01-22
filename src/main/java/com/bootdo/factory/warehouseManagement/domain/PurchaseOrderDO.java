package com.bootdo.factory.warehouseManagement.domain;

import com.bootdo.activiti.domain.TaskDO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-03-18 18:00:09
 */
public class PurchaseOrderDO extends TaskDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//采购订单ID
	private String purchaseId;
	//订单编号
	private String orderCode;
	//申请采购部门ID
	private String applyDeptId;
	//采购负责人
	private String dutyPersonId;
	//申请日期
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date applyDate;
	//请求到货日期
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date arrivalDate;
	//业务类型
	private String businessType;
	//采购单位
	private String supplier;
	//收货单位
	private String demander;
	//总数量
	private Double totalNumber;
	//总重量
	private Double totalWeight;
	//总金额
	private Double totalMoney;
	//审核状态
	private String auditState;
	//采购状态
	private String purchaseState;
	//附件名
	private String fileName;
	//物资用途
	private	String purpose;
	//删除标记
	private Boolean isDeleted;
	//创建用户ID
	private String createUserId;
	//创建时间
	private Date createTime;

	public String getPurpose() {
		return this.purpose;
	}

	public void setPurpose(final String purpose) {
		this.purpose = purpose;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 设置：采购订单ID
	 */
	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}
	/**
	 * 获取：采购订单ID
	 */
	public String getPurchaseId() {
		return purchaseId;
	}
	/**
	 * 设置：订单编号
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	/**
	 * 获取：订单编号
	 */
	public String getOrderCode() {
		return orderCode;
	}
	/**
	 * 设置：申请采购部门ID
	 */
	public void setApplyDeptId(String applyDeptId) {
		this.applyDeptId = applyDeptId;
	}
	/**
	 * 获取：申请采购部门ID
	 */
	public String getApplyDeptId() {
		return applyDeptId;
	}
	/**
	 * 设置：采购负责人
	 */
	public void setDutyPersonId(String dutyPersonId) {
		this.dutyPersonId = dutyPersonId;
	}
	/**
	 * 获取：采购负责人
	 */
	public String getDutyPersonId() {
		return dutyPersonId;
	}
	/**
	 * 设置：申请日期
	 */
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	/**
	 * 获取：申请日期
	 */
	public Date getApplyDate() {
		return applyDate;
	}
	/**
	 * 设置：请求到货日期
	 */
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	/**
	 * 获取：请求到货日期
	 */
	public Date getArrivalDate() {
		return arrivalDate;
	}
	/**
	 * 设置：业务类型
	 */
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	/**
	 * 获取：业务类型
	 */
	public String getBusinessType() {
		return businessType;
	}
	/**
	 * 设置：采购单位
	 */
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	/**
	 * 获取：采购单位
	 */
	public String getSupplier() {
		return supplier;
	}
	/**
	 * 设置：收货单位
	 */
	public void setDemander(String demander) {
		this.demander = demander;
	}
	/**
	 * 获取：收货单位
	 */
	public String getDemander() {
		return demander;
	}
	/**
	 * 设置：总数量
	 */
	public void setTotalNumber(Double totalNumber) {
		this.totalNumber = totalNumber;
	}
	/**
	 * 获取：总数量
	 */
	public Double getTotalNumber() {
		return totalNumber;
	}
	/**
	 * 设置：总重量
	 */
	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}
	/**
	 * 获取：总重量
	 */
	public Double getTotalWeight() {
		return totalWeight;
	}
	/**
	 * 设置：总金额
	 */
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	/**
	 * 获取：总金额
	 */
	public Double getTotalMoney() {
		return totalMoney;
	}
	/**
	 * 设置：审核状态
	 */
	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}
	/**
	 * 获取：审核状态
	 */
	public String getAuditState() {
		return auditState;
	}
	/**
	 * 设置：采购状态
	 */
	public void setPurchaseState(String purchaseState) {
		this.purchaseState = purchaseState;
	}
	/**
	 * 获取：采购状态
	 */
	public String getPurchaseState() {
		return purchaseState;
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
