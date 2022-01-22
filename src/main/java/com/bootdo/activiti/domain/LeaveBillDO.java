package com.bootdo.activiti.domain;

import com.bootdo.common.annotation.Log;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xh
 * @email 1992lcg@163.com
 * @date 2020-03-02 09:38:31
 */
public class LeaveBillDO extends TaskDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//请假单ID
	private String billId;
	//流程实例ID
	private String procInsId;
	//变动用户ID
	private String userId;
	//归属部门ID
	private String officeId;
	//请假类型
	@Log("请假类型")
	private String leaveType;
	//请假天数
	@Log("请假天数")
	private Double leaveDays;
	//开始时间
	@Log("开始时间")
	private Date startDate;
	//结束时间
	@Log("结束时间")
	private Date endDate;
	//请假原因
	@Log("请假原因")
	private String content;
	//审批状态：0审批中 1已通过 2已拒绝
	private Integer state;
	//创建人
	private String createBy;
	//创建时间
	private Date createDate;
	//更新人
	private String updateBy;
	//更新时间
	private Date updateDate;
	//删除标记：0未删除 1已删除
	private Boolean isDeleted;

	/**
	 * 设置：请假单ID
	 */
	public void setBillId(String billId) {
		this.billId = billId;
	}
	/**
	 * 获取：请假单ID
	 */
	public String getBillId() {
		return billId;
	}
	/**
	 * 设置：流程实例ID
	 */
	public void setProcInsId(String procInsId) {
		this.procInsId = procInsId;
	}
	/**
	 * 获取：流程实例ID
	 */
	public String getProcInsId() {
		return procInsId;
	}
	/**
	 * 设置：变动用户ID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：变动用户ID
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：归属部门ID
	 */
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	/**
	 * 获取：归属部门ID
	 */
	public String getOfficeId() {
		return officeId;
	}
	/**
	 * 设置：请假类型
	 */
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	/**
	 * 获取：请假类型
	 */
	public String getLeaveType() {
		return leaveType;
	}
	/**
	 * 设置：请假天数
	 */
	public void setLeaveDays(Double leaveDays) {
		this.leaveDays = leaveDays;
	}
	/**
	 * 获取：请假天数
	 */
	public Double getLeaveDays() {
		return leaveDays;
	}
	/**
	 * 设置：开始时间
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * 获取：开始时间
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * 设置：
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * 获取：
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * 设置：请假原因
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：请假原因
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：审批状态：0审批中 1已通过 2已拒绝
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 获取：审批状态：0审批中 1已通过 2已拒绝
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：创建人
	 */
	public String getCreateBy() {
		return createBy;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：更新人
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：更新人
	 */
	public String getUpdateBy() {
		return updateBy;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * 设置：删除标记：0未删除 1已删除
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	/**
	 * 获取：删除标记：0未删除 1已删除
	 */
	public Boolean getIsDeleted() {
		return isDeleted;
	}
}
