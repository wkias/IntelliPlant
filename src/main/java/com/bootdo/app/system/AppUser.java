package com.bootdo.app.system;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.config.Constant;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.system.controller.UserController;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.DeptService;
import com.bootdo.system.service.RoleService;
import com.bootdo.system.service.UserService;
import com.bootdo.system.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Controller
public class AppUser {  @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    DictService dictService;
    @Autowired
    UserController userController;
    @GetMapping("app/personal")
    @ResponseBody
    R personal() {
        UserDO userDO = userService.get(ShiroUtils.getUserId());
        Map<String,Object> map=new HashMap<>();
        map.put("user", userDO);
        map.put("hobbyList", dictService.getHobbyList(userDO));
        map.put("sexList", dictService.getSexList());
        return R.ok(map);
    }
    @Log("提交更改用户密码")
    @PostMapping("app/resetPwd")
    @ResponseBody
    R resetPwd(UserVO userVO) {
        if (Constant.DEMO_ACCOUNT.equals(ShiroUtils.getUser().getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        try {
            userVO.getUserDO().setUserId(ShiroUtils.getUserId());
            userService.resetPwd(userVO, ShiroUtils.getUser());
            return R.ok();
        } catch (Exception e) {
            return R.error(1, e.getMessage());
        }

    }
    @Log("获取联系人")
    @GetMapping("app/userTree")
    @ResponseBody
    Tree<DeptDO> userTree(){
         return userController.tree();
    }

    @Log("获取联系人信息")
    @PostMapping("app/getContacter")
    @ResponseBody
    UserDO getContacter(Long id){
        UserDO user=userService.get(id);
        return user;
    }
}
