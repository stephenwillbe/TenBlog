package com.dagougou.tenblog.admin.controller;

import com.dagougou.tenblog.admin.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @Description: 登陆相关控制
 * @Author stephen
 * @Date 2020/3/9
 * @Version 1.0
 **/
@Controller
public class LandController {

    @Resource
    private RedisTemplate<String,Object> rt;
    @Resource
    private UserService userService;

    /*
     * @Description //跳转到登陆页面
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping({"/","/toLog"})
    public String login(){
       return "admin/login";
    }
    /*
     * @Description //进行登陆操作,将用户的用户名密码放到token中，并执行shiro的操作，
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/login")
    public String toLogin(String username, String password, Model model){
        //获得当前用户对象
        Subject currentUser = SecurityUtils.getSubject();
        //将用户名密码放到token中
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        //执行登陆方法
        try {
            currentUser.login(token);
            return "redirect:/admin/home";
        } catch (UnknownAccountException e) {//用户不存在
            model.addAttribute("msg","用户名错误");
            return "admin/login";
        }catch (IncorrectCredentialsException e) {//密码不存在
            model.addAttribute("msg","密码错误");
            return "admin/login";
        }
    }
    /*
     * @Description //执行注销操作
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/logout")
    public String logout(){
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        session.removeAttribute("loginUser");
        currentUser.logout();
        return "admin/login";
    }

}
