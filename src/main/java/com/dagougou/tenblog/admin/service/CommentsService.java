package com.dagougou.tenblog.admin.service;

import com.dagougou.tenblog.admin.entity.Comments;

import java.util.List;

/**
 * @Description: 评论服务
 * @Author stephen
 * @Date 2020/3/24
 **/
public interface CommentsService {
    //获得所有评论
    List<Comments> getAll();

    int deleteComment(long parseInt);
    //批量删除评论
    int deleteComments(String[] ids);
    //根据模糊内容查询评论
    List<Comments> getContentLike(String content);
    //查询模糊日期区间的评论
    List<Comments> getDateLike(String startTime, String endTime);
    //查询近几日的评论
    List<Comments> getCommentRecent();
}
