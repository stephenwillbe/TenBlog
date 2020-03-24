package com.dagougou.tenblog.admin.controller;

import com.dagougou.tenblog.admin.entity.Labels;
import com.dagougou.tenblog.admin.entity.Sorts;
import com.dagougou.tenblog.admin.entity.User;
import com.dagougou.tenblog.admin.service.LabelsService;
import com.dagougou.tenblog.admin.service.SortsService;
import com.dagougou.tenblog.admin.service.UserService;
import com.dagougou.tenblog.admin.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description: 首页相关控制
 * @Author stephen
 * @Date 2020/3/9
 * @Version 1.0
 **/
@Controller
@RequestMapping("/admin")
public class HomeController {

    @Resource
    private UserService userService;
    @Resource
    private SortsService sortsService;
    @Resource
    private LabelsService labelsService;
    /*
     * @Description //跳转到后台首页
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/home")
    public String goHome(){
        return "admin/index";
    }

    /*
     * @Description //博客数据检测
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/monitor")
    public String welcome(){
        return "admin/monitor";
    }

    /*
     * @Description //用户管理
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/user")
    public String userList(){
        return "admin/user/user";
    }

    /*
     * @Description //跳转到添加用户
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/toAddUser")
    public String toAddUser(){
        return "admin/user/user-add";
    }
    /*
     * @Description //跳转到跟新用户
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/toUptUser/{userId}")
    public String toUptUser(@PathVariable("userId") String userId, Model model){
        if(StringUtil.isNotEmpty(userId)){
            long id = Long.parseLong(userId);
            User user = userService.getUserById(id);
            model.addAttribute("userInfo",user);
        }
        return "admin/user/user-upt";
    }

    /*
     * @Description //条转到发表文章页面
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/postArt")
    public String toPost(Model model){
       List<Sorts> allSorts = sortsService.getArticleSorts();
       List<Labels> allLabels = labelsService.getAllLabels();
        model.addAttribute("sort",allSorts);
        model.addAttribute("label",allLabels);
        return "admin/article/article_post";
    }

    /*
     * @Description //跳转到文章管理页面
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/artList")
    public String toList(){
        return "admin/article/article_list";
    }

    /*
     * @Description //跳转到分类管理
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/sorts")
    public String toSort(){
        return "admin/sort/sorts";
    }

    /*
     * @Description //跳转到评论管理
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/common")
    public String toCommon(){
        return "admin/common";
    }

    /*
     * @Description //跳转到标签管理
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/label")
    public String toLabel(){
        return "admin/label/label";
    }

    /*
     * @Description //跳转到评论管理
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/comment")
    public String toComment(){
        return "admin/comment/list";
    }
}
