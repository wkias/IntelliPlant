package com.bootdo.factory.equipmentManage.domain;

import com.bootdo.common.annotation.Log;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;



/**
 * 
 * 
 * @author xuhui
 * @email 1992lcg@163.com
 * @date 2020-02-25 13:46:27
 */
public class EquipmentCheckSetDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//点检设置ID
	@Log("点检设置ID")
	private String checkSetId;
	//设备ID
	@Log("设备ID")
	private String equipmentId;
	//点检周期
	@Log("点检周期")
	private Integer checkCycle;
	//单位
	@Log("单位")
	private String unit;
	//截止时间
	@Log("截止时间")
	private Time deadline;
	//点检人
	@Log("点检人")
	private String checkerId;
	//删除标记
//	@Log("删除标记")
	private Boolean isDeleted;
	//创建者
	@Log("创建者")
	private String createUserId;
	//创建时间
	@Log("创建时间")
	private Date createTime;

	/**
	 * 设置：点检设置ID
	 */
	public void setCheckSetId(String checkSetId) {
		this.checkSetId = checkSetId;
	}
	/**
	 * 获取：点检设置ID
	 */
	public String getCheckSetId() {
		return checkSetId;
	}
	/**
	 * 设置：设备ID
	 */
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	/**
	 * 获取：设备ID
	 */
	public String getEquipmentId() {
		return equipmentId;
	}
	/**
	 * 设置：点检周期
	 */
	public void setCheckCycle(Integer checkCycle) {
		this.checkCycle = checkCycle;
	}
	/**
	 * 获取：点检周期
	 */
	public Integer getCheckCycle() {
		return checkCycle;
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
	 * 设置：截止时间
	 */
	public void setDeadline(Time deadline) {
		this.deadline = deadline;
	}
	/**
	 * 获取：截止时间
	 */
	public Time getDeadline() {
		return deadline;
	}

	public String getCheckerId() {
		return checkerId;
	}

	public void setCheckerId(String checkerId) {
		this.checkerId = checkerId;
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
