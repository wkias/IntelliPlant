package com.bootdo.activiti.utils;

import org.activiti.engine.*;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricFormProperty;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.form.DefaultTaskFormHandler;
import org.activiti.engine.impl.form.FormPropertyHandler;
import org.activiti.engine.impl.persistence.entity.HistoricFormPropertyEntity;
import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Component
public class ActivitiUtils {

    @Autowired
    TaskService taskService;
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    FormService formService;
    @Autowired
    HistoryService historyService;
    /**
     * 根据taskId查找businessKey(支持已结束的流程)
     */
    public String getBusinessKeyByTaskId(String taskId) {
        TaskInfo task = taskService
                .createTaskQuery()
                .taskId(taskId)
                .singleResult();
        if(task!=null){
        ProcessInstance pi = runtimeService
                .createProcessInstanceQuery()
                .processInstanceId(task.getProcessInstanceId())
                .singleResult();
        return pi.getBusinessKey();
        }else{
            task=historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
            HistoricProcessInstance procIns=historyService.createHistoricProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
            return procIns.getBusinessKey();
        }
    }

    public Task getTaskByTaskId(String taskId) {
        Task task = taskService
                .createTaskQuery()
                .taskId(taskId)
                .singleResult();
        return task;
    }
    /**
     * 根据taskId获取该节点的下一节点的列表(支持已结束的流程)
     * */
    public List<PvmActivity> getNextNodesByTaskId(String taskId){
        String procDefId=null;
        TaskInfo task=taskService.createTaskQuery().taskId(taskId).singleResult();
        if(task==null){//历史节点
           task=historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
            procDefId=  task.getProcessDefinitionId();
        }else{
            procDefId=task.getProcessDefinitionId();
        }
        ProcessDefinitionEntity procDef=(ProcessDefinitionEntity) ((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(procDefId);
        //List<ActivityImpl> activities=procDef.getActivities();
        ActivityImpl activity=procDef.findActivity(task.getTaskDefinitionKey());
        List<PvmTransition> outTransitions=activity.getOutgoingTransitions();
        List<PvmActivity> nextNodes=new ArrayList<>();
        for(PvmTransition transition: outTransitions){
            nextNodes.add(transition.getDestination());
        }
        return nextNodes;
    }
    /**
     * 根据任务Id获取动态表单属性（审批信息的标签名）(支持已结束的流程)
     * */
    public List<String> getFormPropKeysByTaskId(String taskId){
        List<String> formKeys=new ArrayList<>();
        TaskInfo task=taskService.createTaskQuery().taskId(taskId).singleResult();
        if(task==null){//历史节点
           task=historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
        }
        ProcessDefinitionEntity procDef=(ProcessDefinitionEntity) ((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(task.getProcessDefinitionId());
//        if(task==null){//历史节点
//            List<HistoricDetail> hiDetails=historyService.createHistoricTaskInstanceQuery()
//            for(HistoricDetail detail:hiDetails){
//                if(detail.getTaskId().equals(taskId)){
//                    formKeys.add( ( (HistoricFormProperty)detail ).getPropertyId() );
//                }
//            }
//        }else{
//            TaskFormData formData=formService.getTaskFormData(taskId);
//            List<FormProperty> formProps=formData.getFormProperties();
//            for(FormProperty prop:formProps){
//                formKeys.add(prop.getName());
//            }
//        }
//        TaskDefinition
//        repositoryService.createProcessDefinitionQuery().processDefinitionId().list();
        TaskDefinition taskDef=procDef.getTaskDefinitions().get(task.getTaskDefinitionKey());
        DefaultTaskFormHandler taskFormHandler=(DefaultTaskFormHandler)taskDef.getTaskFormHandler();
        List<FormPropertyHandler> propHandlers=taskFormHandler.getFormPropertyHandlers();
        for(FormPropertyHandler propHandler:propHandlers){
            formKeys.add(propHandler.getName());
        }
        return formKeys;
    }
    /**
     * 根据TaskId判断下一节点是否有分支
     * */
    public boolean hasBranch(String taskId){

        List<PvmActivity> activities=getNextNodesByTaskId(taskId);
        boolean hasBranch=false;
        for(PvmActivity activity:activities){
            if("exclusiveGateway".equals(activity.getProperty("type"))){
                hasBranch=true;
            }
        }
        return  hasBranch;
    }
    /**
     * 任务提交后，根据表单的主键ID判断是否是最后的节点（根据现存的运行时流程实例）
     * */
    public boolean isLashTask(String businessKey){
        ProcessInstance procIns=runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(businessKey).singleResult();
        if(procIns==null){
            return true;
        }else{
            return false;
        }
    }
    /**
     * 根据BusinessKey获取流程实例Id(支持已结束的流程)
     * 供查询流程批注和附件
     * */
    public String getProcessInstanceIdByBusinessKey(String businessKey){
        ProcessInstance procIns=runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(businessKey).singleResult();
        if(procIns==null){//历史节点
            HistoricProcessInstance hiProcIns=historyService.createHistoricProcessInstanceQuery().processInstanceBusinessKey(businessKey).singleResult();
             return  hiProcIns.getId();
        }
        return procIns.getId();
    }
}
