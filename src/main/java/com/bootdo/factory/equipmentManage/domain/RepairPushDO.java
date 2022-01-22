package com.bootdo.factory.equipmentManage.domain;

import java.io.Serializable;
import java.sql.Date;


/**
 * @author willice
 * @date 2020-02-19 17:15:02
 */
public class RepairPushDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //设备ID
    private String equipmentId;
    //设备类型
    private String equipmentType;
    //设备编码
    private String code;
    //设备名称
    private String name;
    //购买日期
    private Date purchaseDate;
    //推送信息ID
    private String pushInfoId;
    //最后维修日期
    private Date lastRepairDate;
    //状态
    private Integer state;

    /**
     * 获取设备ID：
     */
    public String getEquipmentId() {
        return equipmentId;
    }

    /**
     * 设置设备ID：
     */
    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    /**
     * 获取设备类型：
     */
    public String getEquipmentType() {
        return equipmentType;
    }

    /**
     * 设置设备类型：
     */
    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    /**
     * 获取设备编号：
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置设备编号：
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取设备名称：
     */
    public String getName() {
        return name;
    }

    /**
     * 设置设备名称：
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取购买日期：
     */
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * 设置购买日期：
     */
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     * 获取推送消息ID：
     */
    public String getPushInfoId() {
        return pushInfoId;
    }

    /**
     * 设置推送消息ID：
     */
    public void setPushInfoId(String pushInfoId) {
        this.pushInfoId = pushInfoId;
    }

    /**
     * 获取最后维保日期：
     */
    public Date getLastRepairDate() {
        return lastRepairDate;
    }

    /**
     * 设置最后维保日期：
     */
    public void setLastRepairDate(Date lastRepairDate) {
        this.lastRepairDate = lastRepairDate;
    }

    /**
     * 获取状态：
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置状态：
     */
    public void setState(Integer state) {
        this.state = state;
    }
}
