package com.dagougou.tenblog.config;

import com.dagougou.tenblog.admin.entity.User;
import com.dagougou.tenblog.admin.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * @Description: 自定义的realm类，用于shiro从该类获取安全数据（如用户，角色，数据）
 * @Author stephen
 * @Date 2020/3/9
 * @Version 1.0
 **/
public class UserRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        System.out.println("执行授权方法=>doGetAuthorizationInfo");
        //认证完之后给用户进行授权
        //拿到当前登录对象
        User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
        //检查权限SimpleAuthorizationInfo
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //设置当前用户的权限
        info.addStringPermission(currentUser.getUserPer());
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
//        System.out.println("执行认证方法=>doGetAuthenticationInfo");
        //获得用户输入的token
        UsernamePasswordToken token = (UsernamePasswordToken) authToken;

        //根据token得到的用户名查询数据库
        User user = userService.getUserByInfo(token.getUsername());
        if(user==null){
            return null;
        }
        //若认证无误时，将用户保存到session里
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("loginUser",user);
        //用户名认证完以后进行密码认证
        return new SimpleAuthenticationInfo(user, user.getUserPassword(), "");
    }
}
