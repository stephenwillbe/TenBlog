package com.dagougou.tenblog.admin.service.impl;

import com.dagougou.tenblog.admin.dao.CommentsMapper;
import com.dagougou.tenblog.admin.entity.Comments;
import com.dagougou.tenblog.admin.service.CommentsService;
import com.dagougou.tenblog.admin.util.DateUtil;
import com.dagougou.tenblog.admin.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 评论服务实现
 * @Author stephen
 * @Date 2020/3/24
 **/
@Service
public class CommentsServiceImpl implements CommentsService {

    @Resource
    private CommentsMapper commentsMapper;

    @Override
    public List<Comments> getAll() {
        List<Comments> all = commentsMapper.findAll();
        return all;
    }

    @Override
    public int deleteComment(long commentId) {
        int result = commentsMapper.deleteByPrimaryKey(commentId);
        return result;
    }
    @Override
    public int deleteComments(String[] ids){
        List<Long> cIds = new ArrayList<>();
        for(int i = 0;i<ids.length;i++){
            if(StringUtil.isNotEmpty(ids[i])){
                cIds.add((long)Integer.parseInt(ids[i]));
            }
        }
        int result = commentsMapper.deleteByIdList(cIds);
        return result;
    }

    @Override
    public List<Comments> getContentLike(String content) {
        if(StringUtil.isNotEmpty(content)){
            List<Comments> comments = commentsMapper.getCommentByContent(content);
            return comments ;
        }
        return null;
    }

    @Override
    public List<Comments> getDateLike(String startTime, String endTime) {
            Date start = DateUtil.StrToDate(startTime,"yyyy-MM-dd");
            Date end = DateUtil.StrToDate(endTime,"yyyy-MM-dd");
            return commentsMapper.getCommentByDate(start,end);
    }
}
