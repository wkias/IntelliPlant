package com.bootdo.factory.equipmentManage.domain;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;


/**
 * @author willice
 * @date 2020-02-25 13:11:24
 */
public class CheckTasksDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //设备ID
    private String equipmentId;
    //点检任务ID
    private String checkTaskId;
    //设备类型
    private String equipmentType;
    //设备编码
    private String code;
    //设备名称
    private String name;
    //待检点日期
    private Date date;
    //截止时间
    private Time deadline;
    //点检人
    private String checkerId;
    //点检人姓名
    private String checkerName;
    //状态 0:待点检 1:异常 2:已点检
    private Integer state;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getDeadline() {
        return deadline;
    }

    public void setDeadline(Time deadline) {
        this.deadline = deadline;
    }

    public String getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(String checkerId) {
        this.checkerId = checkerId;
    }

    /**
     * 获取：设备ID
     */
    public String getEquipmentId() {
        return equipmentId;
    }

    /**
     * 设置：设备ID
     */
    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    /**
     * 获取：点检ID
     */
    public String getCheckTaskId() {
        return checkTaskId;
    }

    /**
     * 设置：点检ID
     */
    public void setCheckTaskId(String checkTaskId) {
        this.checkTaskId = checkTaskId;
    }

    /**
     * 获取：待点检日期
     */
    public Date getDate() {
        return date;
    }

    /**
     * 设置：待点检日期
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * 获取：状态 0:待点检 1:异常 2:已点检
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置：状态 0:待点检 1:异常 2:已点检
     */
    public void setState(Integer state) {
        this.state = state;
    }

    public String getCheckerName() {
        return checkerName;
    }

    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName;
    }
}
