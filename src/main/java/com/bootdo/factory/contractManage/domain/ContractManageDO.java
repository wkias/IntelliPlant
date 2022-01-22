package com.bootdo.factory.contractManage.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.bootdo.common.annotation.Log;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-03-09 15:22:58
 */
public class ContractManageDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//合同ID
	private String contractId;
	//合同编号
	@Log("合同编号")
	private String contractCode;
	//合同名称
	@Log("合同名称")
	private String contractName;
	//合同类型
	private String contractType;
	//负责人ID
	private String dutyUserId;
	//负责部门ID
	private String dutyDeptId;
	//签订日期
	@Log("签订日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date signDate;
	//开始日期
	@Log("开始日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	//截止日期
	@Log("截止日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	//签订地点
	@Log("签订地点")
	private String signPlace;
	//总金额
	@Log("总金额")
	private Double contractAmount;
	//合同内容
	@Log("合同内容")
	private String contractContent;
	//备注
	@Log("备注")
	private String remarks;
	//附件名
	private String fileName;
	//合同状态
	private String state;
	//删除标记
	private Boolean isDeleted;
	//创建者
	private String createUserId;
	//创建时间
	@Log("创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(final String remarks) {
		this.remarks = remarks;
	}

	public String getContractContent() {
		return this.contractContent;
	}

	public void setContractContent(final String contractContent) {
		this.contractContent = contractContent;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 设置：合同ID
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * 获取：合同ID
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * 设置：合同编号
	 */
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	/**
	 * 获取：合同编号
	 */
	public String getContractCode() {
		return contractCode;
	}
	/**
	 * 设置：合同名称
	 */
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	/**
	 * 获取：合同名称
	 */
	public String getContractName() {
		return contractName;
	}
	/**
	 * 设置：合同类型
	 */
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	/**
	 * 获取：合同类型
	 */
	public String getContractType() {
		return contractType;
	}
	/**
	 * 设置：负责人ID
	 */
	public void setDutyUserId(String dutyUserId) {
		this.dutyUserId = dutyUserId;
	}
	/**
	 * 获取：负责人ID
	 */
	public String getDutyUserId() {
		return dutyUserId;
	}
	/**
	 * 设置：负责部门ID
	 */
	public void setDutyDeptId(String dutyDeptId) {
		this.dutyDeptId = dutyDeptId;
	}
	/**
	 * 获取：负责部门ID
	 */
	public String getDutyDeptId() {
		return dutyDeptId;
	}
	/**
	 * 设置：签订日期
	 */
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}
	/**
	 * 获取：签订日期
	 */
	public Date getSignDate() {
		return signDate;
	}
	/**
	 * 设置：开始日期
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * 获取：开始日期
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * 设置：截止日期
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * 获取：截止日期
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * 设置：签订地点
	 */
	public void setSignPlace(String signPlace) {
		this.signPlace = signPlace;
	}
	/**
	 * 获取：签订地点
	 */
	public String getSignPlace() {
		return signPlace;
	}
	/**
	 * 设置：总金额
	 */
	public void setContractAmount(Double contractAmount) {
		this.contractAmount = contractAmount;
	}
	/**
	 * 获取：总金额
	 */
	public Double getContractAmount() {
		return contractAmount;
	}
	/**
	 * 设置：合同状态 0未开始，1进行中，2已终止，3已结束
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：合同状态 0未开始，1进行中，2已终止，3已结束
	 */
	public String getState() {
		return state;
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
