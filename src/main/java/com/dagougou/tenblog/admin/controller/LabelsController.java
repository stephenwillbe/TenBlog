package com.dagougou.tenblog.admin.controller;

import com.dagougou.tenblog.admin.service.LabelsService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description: 标签的控制层
 * @Author stephen
 * @Date 2020/3/14
 **/
@Controller
@RequestMapping("/labels")
public class LabelsController {

    @Resource
    private LabelsService labelsService;

    /*
     * @Description //获得layui格式化后的标签数据
     * @Param []
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @GetMapping("/data")
    @ResponseBody
    public List<Map<String,Object>> getLabelsFormatData(){
        List<Map<String, Object>> labels = labelsService.getLabels();
        return labels;
    }
    /*
     * @Description //跳转到添加页面
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/toAdd")
    public String toAddPage(){
        return "admin/label/add";
    }

    /*
     * @Description //新增标签
     * @Param [labelName]
     * @return java.lang.String
     **/
    @Transactional
    @RequestMapping("/insert")
    @ResponseBody
    public String insertLabel(String labelName){
        if(labelName!=null && labelName!=""){
            int i = labelsService.insertLabel(labelName);
            if(i>0){
                return "success";
            }
        }
        return "failure";
    }

    /*
     * @Description //单个或多个便签删除
     * @Param 标签id列表
     * @return java.lang.String
     **/
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    @ResponseBody
    public String delLabels(@RequestParam(value = "ids[]")Integer[] ids){
        if(ids!=null&&ids.length>0){
            int i = labelsService.deleteLabels(ids);
            if(i>0){
                return "success";
            }
        }
        return "failure";
    }
}
