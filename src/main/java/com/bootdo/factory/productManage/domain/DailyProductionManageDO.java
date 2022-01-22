package com.bootdo.factory.productManage.domain;

import com.bootdo.factory.domain.ProcessDefinitionDO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 
 * 
 * @author xgq
 * @email 1992lcg@163.com
 * @date 2020-04-04 14:47:59
 */
public class DailyProductionManageDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//生产日报id
	private String dailyProductionId;
	//生产日期
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dailyProductionDate;
	//备注
	private String remark;
	//删除标志
	private Integer isDeleted;
	//创建时间
	private Date createDate;
	//创建人
	private String createUserId;

	private List<DailyProductionContentDO> contentList;

	public List<DailyProductionContentDO> getContentList() {
		return contentList;
	}

	public void setContentList(List<DailyProductionContentDO> contentList) {
		this.contentList = contentList;
	}
	//	private com.bootdo.factory.domain.ProcessDefinitionDO processDefinitionDO;
//	private ProductDetailDO productDetailDO;

//	public ProcessDefinitionDO getProcessDefinitionDO() {
//		return processDefinitionDO;
//	}
//
//	public void setProcessDefinitionDO(ProcessDefinitionDO processDefinitionDO) {
//		this.processDefinitionDO = processDefinitionDO;
//	}
//
//	public ProductDetailDO getProductDetailDO() {
//		return productDetailDO;
//	}
//
//	public void setProductDetailDO(ProductDetailDO productDetailDO) {
//		this.productDetailDO = productDetailDO;
//	}

	/**
	 * 获取：生产日报id
	 */
	public String getDailyProductionId() {
		return dailyProductionId;
	}

	/**
	 * 设置：生产日报id
	 */
	public void setDailyProductionId(String dailyProductionId) {
		this.dailyProductionId = dailyProductionId;
	}

	/**
	 * 获取：生产日期
	 */
	public Date getDailyProductionDate() {
		return dailyProductionDate;
	}

	/**
	 * 设置：生产日期
	 */
	public void setDailyProductionDate(Date dailyProductionDate) {
		this.dailyProductionDate = dailyProductionDate;
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
	 * 获取：创建时间
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * 设置：创建时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 获取：创建人
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * 设置：创建人
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
}
