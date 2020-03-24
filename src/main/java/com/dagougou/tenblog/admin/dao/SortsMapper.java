package com.dagougou.tenblog.admin.dao;

import com.dagougou.tenblog.admin.entity.Sorts;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SortsMapper {
    int deleteByPrimaryKey(Long sortId);

    int insert(Sorts record);

    int insertSelective(Sorts record);

    Sorts selectByPrimaryKey(Long sortId);

    List<Sorts> getSortsByParentId(Long parentId);

    //查找没有子分类的分类集
    List<Sorts> getSortsByState();

    int updateByPrimaryKeySelective(Sorts record);

    int updateByPrimaryKey(Sorts record);

    //根据文章id获得文章的分类
    Sorts getSortByArtId(Long articleId);

    //根据文章到分类
    int insertArtSort(@Param("articleId") Long articleId, @Param(("sortId")) Long sortId);
}