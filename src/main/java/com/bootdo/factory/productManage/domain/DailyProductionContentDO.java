package com.bootdo.factory.productManage.domain;

import com.bootdo.factory.domain.ProcessDefinitionDO;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xgq
 * @email 1992lcg@163.com
 * @date 2020-04-04 14:47:59
 */
public class DailyProductionContentDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//生产日报内容id
	private String dailyProductionContentId;
	//生产日报id
	private String dailyProductionId;
	//工序定义id
	private String processId;
	//物品id
	private String productId;
	//数量
	private Integer count;

	private com.bootdo.factory.domain.ProcessDefinitionDO processDefinitionDO;
	private ProductDetailDO productDetailDO;

	public ProcessDefinitionDO getProcessDefinitionDO() {
		return processDefinitionDO;
	}

	public void setProcessDefinitionDO(ProcessDefinitionDO processDefinitionDO) {
		this.processDefinitionDO = processDefinitionDO;
	}

	public ProductDetailDO getProductDetailDO() {
		return productDetailDO;
	}

	public void setProductDetailDO(ProductDetailDO productDetailDO) {
		this.productDetailDO = productDetailDO;
	}

	/**
	 * 获取：生产日报内容id
	 */
	public String getDailyProductionContentId() {
		return dailyProductionContentId;
	}

	/**
	 * 设置：生产日报内容id
	 */
	public void setDailyProductionContentId(String dailyProductionContentId) {
		this.dailyProductionContentId = dailyProductionContentId;
	}

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
	 * 获取：工序定义id
	 */
	public String getProcessId() {
		return processId;
	}

	/**
	 * 设置：工序定义id
	 */
	public void setProcessId(String processId) {
		this.processId = processId;
	}

	/**
	 * 获取：物品id
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * 设置：物品id
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * 获取：数量
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * 设置：数量
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
}
