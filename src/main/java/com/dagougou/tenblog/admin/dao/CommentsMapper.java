package com.dagougou.tenblog.admin.dao;

import com.dagougou.tenblog.admin.entity.Comments;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CommentsMapper {
    int deleteByPrimaryKey(Long commentId);

    int insert(Comments record);

    int insertSelective(Comments record);

    Comments selectByPrimaryKey(Long commentId);

    int updateByPrimaryKeySelective(Comments record);

    int updateByPrimaryKeyWithBLOBs(Comments record);

    int updateByPrimaryKey(Comments record);

    List<Comments> findAll();
    //模糊查询评论内容
    List<Comments> getCommentByContent(@Param("content") String content);
    //按日期区间查询评论
    List<Comments> getCommentByDate(@Param("startTime") Date startTime , @Param("endTime")  Date endTime);
    //批量删除评论
    int deleteByIdList(List<Long> list);
}