package com.bootdo.factory.equipmentManage.domain;

import java.io.Serializable;
import java.sql.Date;



/**
 * 
 * 
 * @author xgq
 * @email 1992lcg@163.com
 * @date 2020-02-27 13:06:37
 */
public class CheckContentDO implements Serializable {
	private static final long serialVersionUID = 1L;



	//uuid
	private String id;
	//点检序号
	private int checkId;
	//设备类型
	private String equipmentType;
	//点检内容
	private String checkContent;
	//设备照片
	private String photo;
	//删除标记：0表示未删除，1表示已删除
	private Integer isDeleted;
	//创建人
	private String creatUserId;
	//创建时间
	private Date creatDate;
	//顺序序号
	private int order;
	//状态
	private String state;

	/**
	 * 获取：点检序号
	 */
	public int getCheckId() {
		return checkId;
	}

	/**
	 * 设置：点检序号
	 */
	public void setCheckId(int checkId) {
		this.checkId = checkId;
	}

	/**
	 * 获取：设备类型
	 */
	public String getEquipmentType() {
		return equipmentType;
	}

	/**
	 * 设置：设备类型
	 */
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	/**
	 * 获取：点检内容
	 */
	public String getCheckContent() {
		return checkContent;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 设置：点检内容
	 */
	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}

	/**
	 * 获取：设备照片
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * 设置：设备照片
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/**
	 * 获取：删除标记：0表示未删除，1表示已删除
	 */
	public Integer getIsDeleted() {
		return isDeleted;
	}

	/**
	 * 设置：删除标记：0表示未删除，1表示已删除
	 */
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * 获取：创建人
	 */
	public String getCreatUserId() {
		return creatUserId;
	}

	/**
	 * 设置：创建人
	 */
	public void setCreatUserId(String creatUserId) {
		this.creatUserId = creatUserId;
	}

	/**
	 * 获取：创建时间
	 */
	public Date getCreatDate() {
		return creatDate;
	}

	/**
	 * 设置：创建时间
	 */
	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
