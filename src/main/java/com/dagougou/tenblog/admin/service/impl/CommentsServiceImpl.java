package com.dagougou.tenblog.admin.service.impl;

import com.dagougou.tenblog.admin.dao.CommentsMapper;
import com.dagougou.tenblog.admin.entity.Comments;
import com.dagougou.tenblog.admin.service.CommentsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}
