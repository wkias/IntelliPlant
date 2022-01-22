package com.bootdo.app.system;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AppLogin {
    @Log("app登录")
    @PostMapping("app/login")
    @ResponseBody
    R appLogin(String username, String password, HttpServletRequest request) {
        password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            Map<String,Object> map=new HashMap<>();
            map.put("USERBEAN", ShiroUtils.getUser());
            return R.ok(map);
        } catch (AuthenticationException e) {
            return R.error("用户或密码错误");
        }
    }
}
