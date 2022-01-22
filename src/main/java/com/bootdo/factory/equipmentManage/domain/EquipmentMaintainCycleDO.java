package com.bootdo.factory.equipmentManage.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


/**
 * @author zrj
 * @email 1992lcg@163.com
 * @date 2020-02-19 21:11:33
 */
public class EquipmentMaintainCycleDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //设备ID
    private String maintainCycleId;
    //设备类型
    private String equipmentType;
    //单位
    private String unit;
    //维保周期
    private Integer maintainCycle;
    //0表示未被删除，1表示已被删除，默认为0
    private Boolean isDeleted;
    //创建者
    private String createUserId;
    //创建时间
    private Timestamp createTime;

    /**
     * 获取：
     */
    public String getMaintainCycleId() {
        return maintainCycleId;
    }

    /**
     * 设置：
     */
    public void setMaintainCycleId(String maintainCycleId) {
        this.maintainCycleId = maintainCycleId;
    }

    /**
     * 获取：
     */
    public String getEquipmentType() {
        return equipmentType;
    }

    /**
     * 设置：
     */
    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    /**
     * 获取：
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设置：
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 获取：
     */
    public Integer getMaintainCycle() {
        return maintainCycle;
    }

    /**
     * 设置：
     */
    public void setMaintainCycle(Integer maintainCycle) {
        this.maintainCycle = maintainCycle;
    }

    /**
     * 获取：0表示未被删除，1表示已被删除，默认为0
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置：0表示未被删除，1表示已被删除，默认为0
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 获取：
     */
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置：
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取：
     */
    public Timestamp getCreateTime() {
        return createTime;
    }

    /**
     * 设置：
     */
    public void setCreateTime(long createTime) {
        this.createTime = new Timestamp(createTime);
    }
}
