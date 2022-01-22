package com.bootdo.factory.equipmentManage.domain;

import com.bootdo.common.annotation.Log;

import java.io.Serializable;
import java.sql.Date;


/**
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-02-18 14:06:55
 */
public class RepairHistoryDO implements Serializable {


    //维修序号
    @Log(value = "维修序号")
    private String repairId;
    //设备编码
    @Log(value = "设备编码")
    private String code;
    //设备类型
    @Log(value = "设备类型")
    private String equipmentType;
    //设备名称
    @Log(value = "设备名称")
    private String name;
    //维修日期
    @Log(value = "维修日期")
    private Date repairDate;
    //状态：0待维保，1维保中，2已维保
    @Log(value = "状态")
    private String state;
    //维修人员
    @Log(value = "维修人员")
    private String repairPerson;
    //描述
    @Log(value = "描述")
    private String description;

    /**
     * 获取：维修序号
     */
    public String getRepairId() {
        return repairId;
    }

    /**
     * 设置：维修序号
     */
    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }

    /**
     * 获取：设备编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置：设备编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取：维修日期
     */
    public Date getRepairDate() {
        return repairDate;
    }

    /**
     * 设置：维修日期
     */
    public void setRepairDate(Date repairDate) {
        this.repairDate = repairDate;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：状态：0待维保，1维保中，2已维保
     */
    public String getState() {
        return state;
    }

    /**
     * 设置：状态：0待维保，1维保中，2已维保
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取：维修人员
     */
    public String getRepairPerson() {
        return repairPerson;
    }

    /**
     * 设置：维修人员
     */
    public void setRepairPerson(String repairPerson) {
        this.repairPerson = repairPerson;
    }

    /**
     * 获取：描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置：描述
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
