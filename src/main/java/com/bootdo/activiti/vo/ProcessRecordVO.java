package com.bootdo.activiti.vo;

import java.util.Date;

public class ProcessRecordVO {
    //表单ID
    private String businessKey;
    //操作人
    private String operator;
    //审批信息
    private String comment;
    //操作时间
    private Date endTime;
    //审批结果
    private String result;

    public String getBusinessKey() {
        return this.businessKey;
    }

    public void setBusinessKey(final String businessKey) {
        this.businessKey = businessKey;
    }

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(final String operator) {
        this.operator = operator;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(final Date endTime) {
        this.endTime = endTime;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(final String result) {
        this.result = result;
    }
}
