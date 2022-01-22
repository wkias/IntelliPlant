package com.bootdo.factory.contractManage.domain;

import com.bootdo.common.annotation.Log;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;



/**
 * 
 * 
 * @author zrj
 * @email 1992lcg@163.com
 * @date 2020-03-10 18:39:57
 */
public class CustermerContactPersonDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//客户ID
	@Log(value = "客户ID")
	private String custermerId;
	//联系人ID
	@Log(value = "联系人ID")
	private String contactPersonId;
	//姓名
	@Log(value = "姓名")
	private String name;
	//性别；1表示男； 2表示女
	@Log(value = "性别")
	private String sex;
	//联系电话
	@Log(value = "联系电话")
	private String contactPersonTel;
	//微信
	@Log(value = "微信")
	private String wechat;
	//QQ
	@Log(value = "QQ")
	private String qq;
	//职位
	@Log(value = "职位")
	private String position;
	//是否主要联系人；0不是；1是；默认为0
	@Log(value = "是否主要联系人")
	private Boolean isMainPerson;
	//删除标记：0未删除 1已删除
	@Log(value = "删除标记")
	private Boolean isDeleted;
	//创建者
	@Log(value = "创建者")
	private String createUserId;
	//创建时间
	@Log(value = "创建时间")
	private Timestamp createTime;

	/**
	 * 设置：客户ID
	 */
	public void setCustermerId(String custermerId) {
		this.custermerId = custermerId;
	}
	/**
	 * 获取：客户ID
	 */
	public String getCustermerId() {
		return custermerId;
	}
	/**
	 * 设置：联系人ID
	 */
	public void setContactPersonId(String contactPersonId) {
		this.contactPersonId = contactPersonId;
	}
	/**
	 * 获取：联系人ID
	 */
	public String getContactPersonId() {
		return contactPersonId;
	}
	/**
	 * 设置：姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：性别；1表示男； 2表示女
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别；1表示男； 2表示女
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * 设置：联系电话
	 */
	public void setContactPersonTel(String contactPersonTel) {
		this.contactPersonTel = contactPersonTel;
	}
	/**
	 * 获取：联系电话
	 */
	public String getContactPersonTel() {
		return contactPersonTel;
	}
	/**
	 * 设置：微信
	 */
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	/**
	 * 获取：微信
	 */
	public String getWechat() {
		return wechat;
	}
	/**
	 * 设置：QQ
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}
	/**
	 * 获取：QQ
	 */
	public String getQq() {
		return qq;
	}
	/**
	 * 设置：职位
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * 获取：职位
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * 设置：主要联系人；0不是；1是；默认为0
	 */
	public void setIsMainPerson(Boolean isMainPerson) {
		this.isMainPerson = isMainPerson;
	}
	/**
	 * 获取：主要联系人；0不是；1是；默认为0
	 */
	public Boolean getIsMainPerson() {
		return isMainPerson;
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
	public void setCreateTime(long createTime) {
		this.createTime = new Timestamp(createTime);
	}
	/**
	 * 获取：创建时间
	 */
	public Timestamp getCreateTime() {
		return createTime;
	}

	/**
	 * 设置：创建时间（方法重载，供Excel导出功能读取）
	 */
	public void setCreateTime(Timestamp timestamp) {
		this.createTime = timestamp;
	}
}
