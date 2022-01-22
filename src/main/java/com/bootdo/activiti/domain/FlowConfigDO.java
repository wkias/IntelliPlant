package com.bootdo.activiti.domain;

public class FlowConfigDO {
    private Integer id;
    private Integer deptId;
    private String flowType;
    private String procdefId;

    public FlowConfigDO() {
    }

    public FlowConfigDO(final Integer id, final Integer deptId, final String flowType, final String procdefId) {
        this.id = id;
        this.deptId = deptId;
        this.flowType = flowType;
        this.procdefId = procdefId;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Integer getDeptId() {
        return this.deptId;
    }

    public void setDeptId(final Integer deptId) {
        this.deptId = deptId;
    }

    public String getFlowType() {
        return this.flowType;
    }

    public void setFlowType(final String flowType) {
        this.flowType = flowType;
    }

    public String getProcdefId() {
        return this.procdefId;
    }

    public void setProcdefId(final String procdefId) {
        this.procdefId = procdefId;
    }
}
