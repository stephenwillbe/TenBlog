package com.dagougou.tenblog.admin.controller;

import com.dagougou.tenblog.admin.dao.SortsMapper;
import com.dagougou.tenblog.admin.entity.Sorts;
import com.dagougou.tenblog.admin.service.SortsService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 分类管理层
 * @Author stephen
 * @Date 2020/3/12
 **/
@Controller
@RequestMapping("/sorts")
public class SortsController {

    @Resource
    private SortsService sortsService;
    @Resource
    private SortsMapper sortsMapper;

    /*
     * @Description //返回父级分类
     * @Param []
     * @return java.util.List<com.dagougou.tenblog.admin.entity.Sorts>
     **/
    @RequestMapping("/list")
    @ResponseBody
    public List<Sorts> getPSorts(){
        List<Sorts> sorts = sortsMapper.getSortsByParentId((long) 1);
        return sorts;
    }
    //跳转到编辑二级类页面
    @RequestMapping("/check/{sortId}")
    public String checkSorts(@PathVariable("sortId") Long sortId, Model model){
        Sorts sorts = sortsMapper.selectByPrimaryKey(sortId);
        model.addAttribute("sorts",sorts);
        return "admin/sort/sorts_list";
    }
    //子分类数据
    @RequestMapping("/child/{sortId}")
    @ResponseBody
    public List<Sorts> sortList(@PathVariable("sortId")Long sortId){
        List<Sorts> sorts = sortsMapper.getSortsByParentId(sortId);
        return sorts;
    }
    /*
     * @Description //跳转到修改分类名称页
     * @Param [sortId]
     * @return java.lang.String
     **/
    @RequestMapping("/edit/{sortId}")
    public String editSort(@PathVariable("sortId") Long sortId,Model model){
        model.addAttribute("sortId",sortId);
        return "admin/sort/edit";
    }
    /*
     * @Description //根据id删除分类
     * @Param [sortId]
     * @return java.lang.String
     **/
    @Transactional
    @GetMapping("/del")
    @ResponseBody
    public String delSort(Long sortId){
        int i = sortsMapper.deleteByPrimaryKey(sortId);
        if(i>0){
            return "success";
        }
        return "failure";
    }
    /*
     * @Description //新增分类
     * @Param [sortName]
     * @return java.lang.String
     **/
    @Transactional
    @GetMapping("/insert")
    @ResponseBody
    public String insertSort(Sorts sorts){
        if(sorts.getSortName()!=null&&sorts.getSortName()!= ""){
            int i = sortsService.insertBySorts(sorts);
            if(i>0){
                return "success";
            }
        }
        return "failure";
    }
    /*
     * @Description //修改分类名称
     * @Param [sorts]
     * @return java.lang.String
     **/
    @Transactional
    @GetMapping("/editName")
    @ResponseBody
    public String uptSortName(Sorts sorts){
        int i = sortsService.uptSortsName(sorts);
        if(i>0){
            return "success";
        }
        return "failure";
    }

}
