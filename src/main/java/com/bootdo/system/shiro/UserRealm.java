package com.bootdo.system.shiro;

import com.bootdo.common.config.ApplicationContextRegister;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.dao.UserDao;
import com.bootdo.system.domain.RoleDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.MenuService;
import com.bootdo.system.service.RoleService;
import com.bootdo.system.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.*;

public class UserRealm extends AuthorizingRealm {
/*	@Autowired
	UserDao userMapper;
	@Autowired
	MenuService menuService;*/

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        Long userId = ShiroUtils.getUserId();
        MenuService menuService = ApplicationContextRegister.getBean(MenuService.class);
        Set<String> perms = menuService.listPerms(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(perms);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        Map<String, Object> map = new HashMap<>(16);
        map.put("username", username);
        String password = new String((char[]) token.getCredentials());

        UserDao userMapper = ApplicationContextRegister.getBean(UserDao.class);
        // 查询用户信息
        UserDO user = userMapper.list(map).get(0);

        // 账号不存在
        if (user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }

        // 密码错误
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }

        // 账号锁定
        if (user.getStatus() == 0) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        RoleService roleService=ApplicationContextRegister.getBean(RoleService.class);
        List<RoleDO> roleDOs=roleService.list(user.getUserId());
        List<String> roleNames=new ArrayList<>();
        for(RoleDO role:roleDOs){
            if("true".equals(role.getRoleSign())){
                roleNames.add(role.getRoleName());
            }
        }
        user.setRoleNames(roleNames);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }

}
