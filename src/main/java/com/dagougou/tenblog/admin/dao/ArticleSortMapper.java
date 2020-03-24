package com.dagougou.tenblog.admin.dao;

import com.dagougou.tenblog.admin.entity.ArticleSort;

public interface ArticleSortMapper {
    int deleteByPrimaryKey(Integer tId);

    int insert(ArticleSort record);

    int insertSelective(ArticleSort record);

    ArticleSort selectByPrimaryKey(Integer tId);

    int updateByPrimaryKeySelective(ArticleSort record);

    int updateByPrimaryKey(ArticleSort record);
}