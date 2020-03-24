package com.dagougou.tenblog.admin.controller;

import com.dagougou.tenblog.admin.entity.User;
import com.dagougou.tenblog.admin.service.UserService;
import com.dagougou.tenblog.admin.util.FileHandleUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.server.UID;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 用户管理控制
 * @Author stephen
 * @Date 2020/3/9
 * @Version 1.0
 **/
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Resource
    private UserService userService;

    /*
     * @Description //查询用户列表
     * @Param []
     * @return java.util.List<com.dagougou.tenblog.admin.entity.User>
     **/
    @GetMapping("/list")
    public List<User> findAll(){
        return userService.getAllUsers();
    }
    /*
     * @Description //修改用户的状态
     * @Param [userId, userStatus]
     * @return java.lang.String
     **/
    @PostMapping("/status")
    public String updateStatus( String userId, boolean userStatus){
        System.out.println(userId);
        System.out.println(userStatus);
        int i = userService.uptStatus(userId, userStatus);
        if(i>0){
            return "success";
        }
        return "failure";
    }
    /*
     * @Description //删除用户信息
     * @Param [userId]
     * @return java.lang.String
     **/
    @GetMapping("/delete")
    public String delUser( String userId){
//        System.out.println(userId);
        int i = userService.deleteUser(userId);
        if(i>0){
            return "success";
        }
        return "failure";
    }
    /*
     * @Description //添加用户
     * @Param [record]
     * @return java.lang.String
     **/
    @GetMapping("/insert")
    public String insertUser(User record){
        System.out.println(record);
        int i = userService.addUserBySelect(record);
        if(i>0){
            return "success";
        }
        return "failure";
    }

    /*
     * @Description //文件上传处理接口(单文件)
     * @Param 接受的上传文件对象，请求对象
     * @return java.lang.String
     **/
    @RequestMapping("/doUpload")
    public Map<String,Integer> uploadHead(@RequestParam(value = "file", required = false)MultipartFile ufile, HttpServletRequest request ){
        Map<String,Integer> returnMap = new HashMap<>();
        //code为0代表上传成功，大于0则失败
        if(ufile.isEmpty()){
            returnMap.put("code",1);
            return returnMap;
        }
        try {
            //读取文件的字节码
            InputStream in =  ufile.getInputStream();
            byte[] bytes = ufile.getBytes();
            //获取文件的名称
            String fileName = ufile.getOriginalFilename();
            //设置文件存储位置
           String filePath = "/upload/imge/"+fileName;
            //创建file对象
            File dest = new File(filePath);
            //检查目录是否存在，不存在则新建一个
            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdir();
            }
            //保存文件,并把相对路径保存到数据库中
            FileUtils.writeByteArrayToFile(dest,bytes);
            returnMap.put("code",0);
        } catch (IOException e) {
            e.printStackTrace();
            returnMap.put("code",1);
        }
        return returnMap;
    }

    /*
     * @Description //编辑用户信息
     * @Param [record]
     * @return java.lang.String
     **/
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public Map<String,String>  uptUser(User user){
        Map<String,String> returnMap = new HashMap<>();
            user.setUserStatus(null);
            System.out.println(user);
            int i = userService.uptUserBySelect(user);
            if(i>0){
                returnMap.put("code","0");
                return returnMap;
            }
        returnMap.put("code","1");
        return returnMap;
    }
}
