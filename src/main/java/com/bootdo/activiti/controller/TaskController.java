package com.bootdo.activiti.controller;

import com.bootdo.activiti.service.ActTaskService;
import com.bootdo.activiti.vo.ProcessVO;
import com.bootdo.activiti.vo.TaskVO;
import com.bootdo.common.domain.TaskDO;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.RoleDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.domain.UserRoleDO;
import com.bootdo.system.service.DeptService;
import com.bootdo.system.service.RoleService;
import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.IdentityLinkType;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.management.relation.Role;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@RequestMapping("activiti/task")
@RestController
public class TaskController {
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    FormService formService;
    @Autowired
    TaskService taskService;
    @Autowired
    ActTaskService actTaskService;
    @Autowired
    DeptService deptService;
    @Autowired
    RoleService roleService;
    @Autowired
    RuntimeService runtimeService;

    @GetMapping("goto")
    public ModelAndView gotoTask() {
        return new ModelAndView("act/task/gotoTask");
    }

    @GetMapping("/gotoList")
    PageUtils list(int offset, int limit) {
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery()
                .listPage(offset, limit);
        int count = (int) repositoryService.createProcessDefinitionQuery().count();
        List<Object> list = new ArrayList<>();
        for (ProcessDefinition processDefinition : processDefinitions) {
            list.add(new ProcessVO(processDefinition));
        }

        PageUtils pageUtils = new PageUtils(list, count);
        return pageUtils;
    }
    //发起流程的方法转移到了ProcessController的launchACt()   2020.03.04 xh
    @GetMapping("/form/{procDefId}")
    public void startForm(@PathVariable("procDefId") String procDefId, HttpServletResponse response) throws IOException {
        String formKey = actTaskService.getFormKey(procDefId, null);
        response.sendRedirect(formKey);
    }
    //流程中转的方法继续使用
    @GetMapping("/form/{procDefId}/{taskId}")
    public void form(@PathVariable("procDefId") String procDefId, @PathVariable("taskId") String taskId, HttpServletResponse response) throws IOException {
        // 获取流程XML上的表单KEY

        String formKey = actTaskService.getFormKey(procDefId, taskId);


        response.sendRedirect(formKey + "/" + taskId);
    }

    @GetMapping("/todo")
    ModelAndView todo() {
        return new ModelAndView("act/task/todoTask");
    }

    @GetMapping("/todoList")
    List<TaskVO> todoList() {
        UserDO user= ShiroUtils.getUser();
        String username=user.getUsername();
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(username).list();
        DeptDO dept=deptService.get(user.getDeptId());
        List<String> roles=new ArrayList<>();
        List<RoleDO> roleList=roleService.list(user.getUserId());
        for(RoleDO role:roleList){
            if("true".equals(role.getRoleSign())){
                roles.add(role.getRoleName());
            }
        }


        //求交集
        List<Task> allTasks=taskService.createTaskQuery().list();
        for(Task task: allTasks){
            List<IdentityLink> links=taskService.getIdentityLinksForTask(task.getId());
            boolean deptMatch=false;
            boolean roleMatch=false;
            for(IdentityLink link:links){
                if(link.getType().equals(IdentityLinkType.CANDIDATE)){
                    if(dept.getName().equals(link.getGroupId())){
                        deptMatch=true;
                    }else if(roles.contains(link.getGroupId())){
                        roleMatch=true;
                    }
                }
            }
            if(deptMatch&&roleMatch){
                tasks.add(task);
            }
        }


        System.out.println(tasks);



//        List<Task> deptTasks=taskService.createTaskQuery().taskCandidateGroup(dept.getName()).list();
//        List<Task> roleTasks=taskService.createTaskQuery().taskCandidateGroupIn(roles).list();根据用户组ID拿不到任务列表
     //   deptTasks.retainAll(roleTasks);
    //    tasks.addAll(deptTasks);
//        System.out.println(dept.getName());
//        List<IdentityLink> links=taskService.getIdentityLinksForTask("237511");
//        System.out.println(links);
        //求并集
//        List<String> groups=new ArrayList<>();
//        groups.add(dept.getName());
//        groups.addAll(roles);
//        taskService.createTaskQuery().taskCandidateGroupIn(groups).list();

        List<TaskVO> taskVOS = new ArrayList<>();
        for (Task task : tasks) {
            TaskVO taskVO = new TaskVO(task);
            taskVOS.add(taskVO);
        }
        return taskVOS;
    }


    /**
     * 读取带跟踪的图片
     */
    @RequestMapping(value = "/trace/photo/{procDefId}/{execId}")
    public void tracePhoto(@PathVariable("procDefId") String procDefId, @PathVariable("execId") String execId, HttpServletResponse response) throws Exception {
        InputStream imageStream = actTaskService.tracePhoto(procDefId, execId);

        // 输出资源内容到相应对象
        byte[] b = new byte[1024];
        int len;
        while ((len = imageStream.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }


}
