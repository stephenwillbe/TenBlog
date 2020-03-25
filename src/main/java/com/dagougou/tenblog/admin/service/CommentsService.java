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

    int deleteComments(String[] ids);

    List<Comments> getContentLike(String content);

    List<Comments> getDateLike(String startTime, String endTime);
}
