package com.bootdo.factory.personManage.domain;

import com.bootdo.common.annotation.Log;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;



/**
 * 
 * 
 * @author zrj
 * @email 1992lcg@163.com
 * @date 2020-03-13 17:22:04
 */
public class WorkLogDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//日志ID
	private String workLogId;
	//用户ID
	private String userId;
	//部门ID
	private String deptId;
	//工时类型
    @Log("工时类型")
	private String workTimeType;
	//时长
    @Log("时长")
	private String hourPeriod;
	//日报日期
    @Log("日报日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date logDate;
	//日报(工作)内容
    @Log("日报(工作）内容")
	private String logContent;
	//删除标记； 0表示未删除； 1已删除
	private Boolean isDeleted;
	//创建者
	private String createUserId;
	//创建日期
	private Timestamp createDate;
	private String timeTypeName;
	private String hourName;

	public String getTimeTypeName() {
		return timeTypeName;
	}

	public void setTimeTypeName(String timeTypeName) {
		this.timeTypeName = timeTypeName;
	}
	public String getHourName() {
		return hourName;
	}

	public void setHourName(String hourName) {
		this.hourName = hourName;
	}
	/**
	 * 设置：日志ID
	 */
	public void setWorkLogId(String workLogId) {
		this.workLogId = workLogId;
	}
	/**
	 * 获取：日志ID
	 */
	public String getWorkLogId() {
		return workLogId;
	}
	/**
	 * 设置：用户ID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户ID
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：部门ID
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * 获取：部门ID
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * 设置：工时类型
	 */
	public void setWorkTimeType(String workTimeType) {
		this.workTimeType = workTimeType;
	}
	/**
	 * 获取：工时类型
	 */
	public String getWorkTimeType() {
		return workTimeType;
	}
	/**
	 * 设置：时长
	 */
	public void setHourPeriod(String hourPeriod) {
		this.hourPeriod = hourPeriod;
	}
	/**
	 * 获取：时长
	 */
	public String getHourPeriod() {
		return hourPeriod;
	}
	/**
	 * 设置：日报日期
	 */
	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}
	/**
	 * 获取：日报日期
	 */
	public Date getLogDate() {
		return logDate;
	}
	/**
	 * 设置：日报(工作)内容
	 */
	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}
	/**
	 * 获取：日报(工作)内容
	 */
	public String getLogContent() {
		return logContent;
	}
	/**
	 * 设置：删除标记； 0表示未删除； 1已删除
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	/**
	 * 获取：删除标记； 0表示未删除； 1已删除
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
	 * 获取：
	 */
	public Timestamp getCreateDate() {
		return createDate;
	}

	/**
	 * 设置：
	 */
	public void setCreateDate(long createDate) {
		this.createDate = new Timestamp(createDate);
	}
}
