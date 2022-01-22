package com.bootdo.factory.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Casper
 * @email 1992lcg@163.com
 * @date 2020-03-24 11:16:31
 */
public class ProcessDefinitionDO implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String STATE_USE = "1";
    public static final String STATE_NOT_USE = "0";
    //工序定义id
    private String processId;
    //工序编号
    private String processCode;
    //工序名称
    private String processName;
    //状态；0关闭；1开启
    private String state;
    //描述
    private String description;
    //删除标记；默认值0；0：未删除；1：已删除
    private Boolean isDeleted;
    //创建者
    private String createUserId;
    //创建时间
    private Date createTime;

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }

    public String getProcessCode() {
        return processCode;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getProcessName() {
        return processName;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
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
}
