package com.dagougou.tenblog.admin.controller;

import com.dagougou.tenblog.admin.entity.Comments;
import com.dagougou.tenblog.admin.service.CommentsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 评论管理控制层
 * @Author stephen
 * @Date 2020/3/24
 **/
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentsService commentsService;


    @RequestMapping("/list")
    @ResponseBody
    public List<Comments> getAllComment(){
        return commentsService.getAll();
    }
}
