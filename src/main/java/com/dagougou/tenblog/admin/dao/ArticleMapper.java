package com.dagougou.tenblog.admin.dao;

import com.dagougou.tenblog.admin.entity.Article;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Long articleId);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Long articleId);

    int updateByPrimaryKeySelective(Article record);

    List<Article> findAll();

}