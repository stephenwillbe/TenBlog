package com.dagougou.tenblog.admin.service;

import com.dagougou.tenblog.admin.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 文章服务
 * @Author stephen
 * @Date 2020/3/10
 * @Version 1.0
 **/
public interface ArticleService {
    //新增文章，返回id
    int postArticle(Article article);
    //添加文章的标签
    int addLabels(List<ArticleLabel> list);
    //添加文章的类型
    int addSorts(ArticleSort articleSort);

    List<Article> getList();

    int deleteArticle(Integer articleId);

    Article getArticleById(Integer articleId);

    int updateArticle(Article article);

    //根据文章id修改文章类型
    int updateArticleSort(Integer articleId, Integer sortId);
    //根据文章id修改文章的标签
    int updateArticleLabel(Integer articleId,String[] labelIds);
    //获得文章的数据
    UserArticleData getAllArticleData(Long userId);
    //获取文章最近的数据
    List<Article> getArticleRecent(Long userId);
}
