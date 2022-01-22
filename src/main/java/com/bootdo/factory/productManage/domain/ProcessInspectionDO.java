package com.bootdo.factory.productManage.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * 
 * 
 * @author zrj
 * @email 1992lcg@163.com
 * @date 2020-03-26 21:55:29
 */
public class ProcessInspectionDO implements Serializable {
	private static final long serialVersionUID = 1L;

    //工序
    private com.bootdo.factory.domain.ProcessDefinitionDO process;
    //质检项目
    private InspectionItemsDO items;
	//工序质检ID
	private String processInspectionId;
	//工序ID
	private String processId;
	//工序名称
	private String processName;
    //质检项目ID
    private String inspectionItemsId;
	//状态 0停用 1引用
	private String state;
	//工序质检结果
	private String result;
	//描述
	private String represent;
	//删除标记0未删除1已删除默认为0
	private Boolean isDeleted;
	//创建者ID
	private String createUserId;
	//创建日期
	private Timestamp createDate;
	private List<ProcessRelationshipDO> processRelationships;
	public List<ProcessRelationshipDO> getProcessRelationships() {
		return this.processRelationships;
	}

	public void setProcessRelationships(final List<ProcessRelationshipDO> processRelationships) {
		this.processRelationships = processRelationships;
	}


    public InspectionItemsDO getItems() {
        return this.items;
    }

    public void setItems(final InspectionItemsDO items) {
        this.items = items;
    }

    public com.bootdo.factory.domain.ProcessDefinitionDO getProcess() {
        return this.process;
    }

    public void setProcess(final com.bootdo.factory.domain.ProcessDefinitionDO process) {
        this.process = process;
    }

	/**
	 * 设置：工序质检ID
	 */
	public void setProcessInspectionId(String processInspectionId) {
		this.processInspectionId = processInspectionId;
	}
	/**
	 * 获取：工序质检ID
	 */
	public String getProcessInspectionId() {
		return processInspectionId;
	}
	/**
	 * 设置：工序ID
	 */
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	/**
	 * 获取：工序ID
	 */
	public String getProcessId() {
		return processId;
	}

	/**
	 * 设置：状态 0停用 1引用
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：状态 0停用 1引用
	 */
	public String getState() {
		return state;
	}
	/**
	 * 设置：工序质检结果
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * 获取：工序质检结果
	 */
	public String getResult() {
		return result;
	}
	/**
	 * 设置：描述
	 */
	public void setRepresent(String represent) {
		this.represent = represent;
	}
	/**
	 * 获取：描述
	 */
	public String getRepresent() {
		return represent;
	}
	/**
	 * 设置：删除标记0未删除1已删除默认为0
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	/**
	 * 获取：删除标记0未删除1已删除默认为0
	 */
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	/**
	 * 设置：创建者ID
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：创建者ID
	 */
	public String getCreateUserId() {
		return createUserId;
	}
	/**
	 * 设置：创建日期
	 */
	public void setCreateDate(long createDate) {
		this.createDate = new Timestamp (createDate);
	}
	/**
	 * 获取：创建日期
	 */
	public Timestamp getCreateDate() {
		return createDate;
	}
    /**
     * 设置：质检项目ID
     */
    public void setInspectionItemsId(String inspectionItemsId) {
        this.inspectionItemsId = inspectionItemsId;
    }
    /**
     * 获取：质检项目ID
     */
    public String getInspectionItemsId() {
        return inspectionItemsId;
    }
	/**
	 * 设置：工序名称
	 */
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	/**
	 * 获取：工序名称
	 */
	public String getProcessName() {
		return processName;
	}
}
