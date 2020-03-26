package com.dagougou.tenblog.admin.dao;

import com.dagougou.tenblog.admin.entity.Article;
import com.dagougou.tenblog.admin.entity.UserArticleData;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Long articleId);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Long articleId);

    int updateByPrimaryKeySelective(Article record);

    List<Article> findAll();

    //查询最近7天的浏览量

    //根据用户的文章查询文章浏览总数,文章点赞总数,文章评论总数,文章点赞总数
    UserArticleData selectAllArticleData(Long userId);

    //根据用户id,获取最新的5篇文章
    List<Article> selectArticleByDateLimit(@Param("articleNum") Integer articleNum, @Param("userId") Long userId);
}