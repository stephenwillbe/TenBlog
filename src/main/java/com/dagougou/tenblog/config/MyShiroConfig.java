package com.dagougou.tenblog.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description: shiro用户认证和授权配置类
 * @Author stephen
 * @Date 2020/3/9
 * @Version 1.0
 **/
@Configuration
public class MyShiroConfig  {

    //shirofilterfactorybean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(securityManager);
        //添加shiro过滤器
        Map<String ,String >filterMap = new LinkedHashMap<>();
        //授权正常的情况下会跳转到认证，没授权的话跳到未授权页面
        filterMap.put("/ad","perms[user:admin]");
        filterMap.put("/us","perms[user:normal]");
        filterMap.put("/admin/*","authc");//所有url前有admin的都要认证才能通过，所以登陆的路径不能经过这个权限
        filterMap.put("/user/*","authc");
        filterMap.put("/sorts/*","authc");
        filterMap.put("/labels/*","authc");
        bean.setFilterChainDefinitionMap(filterMap);
        //设置登录页面
        bean.setLoginUrl("/toLog");
        //设置未授权的页面
        bean.setUnauthorizedUrl("/admin/noauthc");
        return bean;
    }
    //DefaultWebSecurityManager 管理subject
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联userRealm
        securityManager.setRealm( userRealm);
        return securityManager;
    }
    //创建Realm 对象
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    //整合shiroDialect:用来整合，shiro thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
