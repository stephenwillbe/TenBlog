package com.dagougou.tenblog.admin.service.impl;

import com.dagougou.tenblog.admin.dao.ArticleLabelMapper;
import com.dagougou.tenblog.admin.dao.ArticleMapper;
import com.dagougou.tenblog.admin.dao.ArticleSortMapper;
import com.dagougou.tenblog.admin.entity.Article;
import com.dagougou.tenblog.admin.entity.ArticleLabel;
import com.dagougou.tenblog.admin.entity.ArticleSort;
import com.dagougou.tenblog.admin.entity.UserArticleData;
import com.dagougou.tenblog.admin.service.ArticleService;
import com.dagougou.tenblog.admin.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 文章服务实现类
 * @Author stephen
 * @Date 2020/3/10
 * @Version 1.0
 **/
@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ArticleLabelMapper articleLabelMapper;
    @Resource
    private ArticleSortMapper articleSortMapper;

    @Transactional
    @Override
    public int postArticle(Article article) {
        int l = articleMapper.insertSelective(article);
        return l;
    }

    @Transactional
    @Override
    public int addLabels(List<ArticleLabel> list) {
        int i = 0;
        if(list!=null){
            i = articleLabelMapper.insertArticleLabel(list);
        }
        return i;
    }
    @Transactional
    @Override
    public int addSorts(ArticleSort articleSort){
        int i = articleSortMapper.insertSelective(articleSort);
        return i;
    }

    @Override
    public List<Article> getList(){
        return articleMapper.findAll();
    }
    //根据文章id删除文章类型
    @Override
    public int deleteArticle(Integer parseInt) {
        int i = articleSortMapper.deleteByPrimaryKey(parseInt);
        return i;
    }
    //获得文章信息
    @Override
    public Article getArticleById(Integer articleId) {
        Article article = articleMapper.selectByPrimaryKey((long)articleId);
        return article;
    }
    //更新文章
    @Transactional
    @Override
    public int updateArticle(Article article) {
        int i = articleMapper.updateByPrimaryKeySelective(article);
        return i;
    }
    //更新类型
    @Transactional
    @Override
    public int updateArticleSort(Integer articleId, Integer sortId) {
        ArticleSort articleSort = new ArticleSort();
        articleSort.setArticleId((long)articleId);
        articleSort.setSortId((long)sortId);
        int i = articleSortMapper.updateByPrimaryKey(articleSort);
        return i;
    }
    //更新标签
    @Transactional
    @Override
    public int updateArticleLabel(Integer articleId, String[] labelIds) {
        int result = 0;
        //先删除，再添加标签
        result = articleLabelMapper.deleteArticleLabel(articleId);
        List<ArticleLabel> list = new ArrayList<>();
        for(int j = 0 ;j<labelIds.length;j++){
            if(StringUtil.isNotEmpty(labelIds[j])&&labelIds[j]!=""){
                ArticleLabel articleLabel = new ArticleLabel();
                articleLabel.setArticleId((long)articleId);
                articleLabel.setLabelId((long)Integer.parseInt(labelIds[j]));
                list.add(articleLabel);
            }
        }
        if(list!=null&&list.size()>0){
            result = articleLabelMapper.insertArticleLabel(list);
        }else{
            result = -1;
        }
        return result;
    }

    @Override
    public UserArticleData getAllArticleData(Long userId) {
        if(userId!=null){
            UserArticleData userArticleData = articleMapper.selectAllArticleData(userId);
            return userArticleData;
        }
        return null;
    }

    //获取最近的文章数据
    @Override
    public List<Article> getArticleRecent(Long userId) {
        if(userId!=null){
            List<Article> articles = articleMapper.selectArticleByDateLimit(5, userId);
             return articles;
        }
        return null;
    }
}
