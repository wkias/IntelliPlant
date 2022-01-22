package com.bootdo.factory.equipmentManage.domain;

import com.bootdo.common.annotation.Log;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author Casper
 * @email 1992lcg@163.com
 * @date 2020-03-01 15:20:46
 */
public class EquipmentCheckHistoryDO implements Serializable {

    @Log(value = "点检序号")
    private String checkHistoryId;
    //设备编码
    @Log(value = "设备编码")
    private String code;
    //设备名称
    @Log(value = "设备名称")
    private String name;
    //设备类型
    @Log(value = "设备类型")
    private String equipmentType;
    //状态：0待点检，1已点检，2异常
    @Log(value = "状态")
    private String state;
    //点检日期
    @Log(value = "点检日期")
    private java.sql.Date checkDate;
    //点检人
    @Log(value = "点检人")
    private String checkerId;
    //备注
    @Log(value = "备注")
    private String remarks;

    private String checkerName;

    private Boolean isDeleted;

    private String createUserId;

    private Date createTime;

    public void setCheckHistoryId(String checkHistoryId) {
        this.checkHistoryId = checkHistoryId;
    }

    public String getCheckHistoryId() {
        return checkHistoryId;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Date getCheckDate() {
        return checkDate;
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

    public void setCheckerId(String checkerId) {
        this.checkerId = checkerId;
    }

    public String getCheckerId() {
        return checkerId;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getCheckerName() {
        return checkerName;
    }

    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName;
    }
}
