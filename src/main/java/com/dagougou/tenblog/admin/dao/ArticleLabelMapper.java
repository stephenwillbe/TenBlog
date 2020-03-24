package com.dagougou.tenblog.admin.dao;

import com.dagougou.tenblog.admin.entity.ArticleLabel;

import java.util.List;

public interface ArticleLabelMapper {
    int deleteByPrimaryKey(Integer tId);

    int insert(ArticleLabel record);

    int insertSelective(ArticleLabel record);

    //批量添加文章的标签
    int insertArticleLabel(List<ArticleLabel> list);
    //批量删除文章的标签
    int deleteArticleLabel(Integer articleId);

    ArticleLabel selectByPrimaryKey(Integer tId);

    int updateByPrimaryKeySelective(ArticleLabel record);

    int updateByPrimaryKey(ArticleLabel record);
}