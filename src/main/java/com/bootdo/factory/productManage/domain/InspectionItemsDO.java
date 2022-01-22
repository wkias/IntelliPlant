package com.bootdo.factory.productManage.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * @author willice
 * @email 2782546116@qq.com
 * @date 2020-03-26 12:19:21
 */
public class InspectionItemsDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //质检项目ID
    private String inspectionItemsId;
    //项目名称
    private String projectName;
    //值类型
    private String valueType;
    //值类型
    private String valueTypeName;
    //单位
    private String unit;
    //单位
    private String unitName;
    //范围阈值
    private String rangeThreshold;
    //说明
    private String description;
    //状态：0停用，1正常
    private String state;
    //状态
    private String stateName;
    //创建用户ID
    private Long createUserId;
    //创建时间
    private Date createTime;
    //是否删除
    private Boolean isDeleted;

    public String getValueTypeName() {
        return valueTypeName;
    }

    public void setValueTypeName(String valueTypeName) {
        this.valueTypeName = valueTypeName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    /**
     * 获取：质检项目ID
     */
    public String getInspectionItemsId() {
        return inspectionItemsId;
    }

    /**
     * 设置：质检项目ID
     */
    public void setInspectionItemsId(String inspectionItemsId) {
        this.inspectionItemsId = inspectionItemsId;
    }

    /**
     * 获取：项目名称
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * 设置：项目名称
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * 获取：值类型
     */
    public String getValueType() {
        return valueType;
    }

    /**
     * 设置：值类型
     */
    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    /**
     * 获取：单位
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设置：单位
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 获取：范围阈值
     */
    public String getRangeThreshold() {
        return rangeThreshold;
    }

    /**
     * 设置：范围阈值
     */
    public void setRangeThreshold(String rangeThreshold) {
        this.rangeThreshold = rangeThreshold;
    }

    /**
     * 获取：说明
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置：说明
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取：状态：0停用，1正常
     */
    public String getState() {
        return state;
    }

    /**
     * 设置：状态：0停用，1正常
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取：状态：0停用，1正常
     */
    public String getStateName() {
        return stateName;
    }

    /**
     * 设置：状态：0停用，1正常
     */
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    /**
     * 获取：创建用户ID
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置：创建用户ID
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置：创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：是否删除
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置：是否删除
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
