package com.dagougou.tenblog.admin.controller;

import com.dagougou.tenblog.admin.entity.Comments;
import com.dagougou.tenblog.admin.service.CommentsService;
import com.dagougou.tenblog.admin.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    /*
     * @Description //删除评论
     * @Param [commentId]
     * @return java.lang.String
     **/
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    @ResponseBody
    public String delComment(@RequestParam(name = "commentId" ,required = true) String commentId){
        System.out.println(commentId);
      int result = commentsService.deleteComment((long) Integer.parseInt(commentId));
      if(result>0){
          return "success";
      }
        return "failure";
    }
    /*
     * @Description //批量删除评论
     * @Param [commentIds]
     * @return java.lang.String
     **/
    @RequestMapping(value = "/delComments",method = RequestMethod.GET)
    @ResponseBody
    public String delComment(@RequestParam(value = "commentsId[]") String[] commentIds){
        int result = commentsService.deleteComments(commentIds);
        if(result>0){
            return "success";
        }
        return "failure";
    }
    /*
     * @Description //得到模糊评论内容或者日期的评论
     * @Param [content]
     * @return java.util.List<com.dagougou.tenblog.admin.entity.Comments>
     **/
    @RequestMapping(value = "/contOrDate",method = RequestMethod.GET)
    @ResponseBody
    public List<Comments> getContentOrDate(@RequestParam(name="content") String content ,@RequestParam(name = "startTime") String startTime ,@RequestParam(name = "endTime") String endTime){
        //判断评论内容--内容为空判断时间--都为空返回全部的评论
        List<Comments> comments = null;
        if(StringUtil.isNotEmpty(content)){
            comments = commentsService.getContentLike(content);
        }else {
           comments = commentsService.getDateLike(startTime,endTime);
        }
        return comments;
    }
}
