package com.dagougou.tenblog.admin.controller;

import com.dagougou.tenblog.admin.entity.*;
import com.dagougou.tenblog.admin.service.ArticleService;
import com.dagougou.tenblog.admin.service.LabelsService;
import com.dagougou.tenblog.admin.service.SortsService;
import com.dagougou.tenblog.admin.util.StringUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Description: 文章相关的控制层
 * @Author stephen
 * @Date 2020/3/10
 * @Version 1.0
 **/
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;
    @Resource
    private LabelsService labelsService;
    @Resource
    private SortsService sortsService;
    /*
     * @Description //跳转到发表文章页面
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/post")
    public String toPost(){
        return "admin/article/article_post";
    }

    /*
     * @Description //发布新的博客文章
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping(value = "/publish",method = RequestMethod.GET)
    @ResponseBody
    public String postArt(@RequestParam(name = "userId" ,required = true) Integer userId
            ,@RequestParam(name = "articleTitle" , required = true) String articleTitle
            ,@RequestParam(name = "articleMdContent" , required = true)  String articleMdContent
            ,@RequestParam(name = "articleStatus" , required = true)  Integer articleStatus
            ,@RequestParam(name = "articleAllowComment" , required = true)  String articleAllowComment
            ,@RequestParam(value = "labelIds[]",required = true) String[] labelIds
            ,@RequestParam(name = "sortId" , required = true)  Integer sortId){

        Integer allow = articleAllowComment.equals("on") ? 1 : 0 ;
        //添加文章，并返回文章的id
        Article article = new Article();
        article.setUserId( (long)userId);
        article.setArticleAllowComment(allow);
        article.setArticleMdContent(articleMdContent);
        article.setArticleStatus(articleStatus);
        article.setArticleTitle(articleTitle);
        int id = articleService.postArticle(article);
        System.out.println(article.getArticleId());
        //根据文章id,添加文章的分类和标签
        if(id>0){
            //添加标签
            List<ArticleLabel> list = new ArrayList<>();
            for(int i = 0 ; i<labelIds.length;i++){
                if(StringUtil.isNotEmpty(labelIds[i])&&labelIds[i]!=""){
                    ArticleLabel articleLabel = new ArticleLabel();
                    articleLabel.setArticleId(article.getArticleId());
                    articleLabel.setLabelId((long)Integer.parseInt(labelIds[i]));
                    list.add(articleLabel);
                }
            }
            int i = 0;
            if(list.size()>0&&list!=null){
                i = articleService.addLabels(list);
            }
            //添加分类
            ArticleSort articleSort = new ArticleSort();
            articleSort.setArticleId(article.getArticleId());
            articleSort.setSortId((long)sortId);
            int s = articleService.addSorts(articleSort);
            if(i>0&&s>0){
                return "success";
            }
        }
        return "failure";
    }

    /*
     * @Description //返回所有文章列表信息
     * @Param []
     * @return java.util.List<com.dagougou.tenblog.admin.entity.Article>
     **/
    @RequestMapping("/list")
    @ResponseBody()
    public List<Article> articleList(){
        List<Article> list = articleService.getList();
        return list;
    }

    /*
     * @Description //根据文章id删除文章
     * @Param [articleId]
     * @return java.lang.String
     **/
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    @ResponseBody
    public String delArt(@RequestParam(name="articleId",required = true) String articleId){
       int i =  articleService.deleteArticle(Integer.parseInt(articleId));
       if(i>0){
           return "success";
       }
        return "failure";
    }

    /*
     * @Description //跳转到编辑页面
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/toEdit/{articleId}")
    public String toEdit(Model model,@PathVariable(name="articleId",required = true)String articleId){
        Article art = articleService.getArticleById(Integer.parseInt(articleId));
        List<Labels> artLabels = labelsService.getLabelsByArticleId(Integer.parseInt(articleId));
        Sorts artSort = sortsService.getSortByArticleId(Integer.parseInt(articleId));
        List<Sorts> allSorts = sortsService.getArticleSorts();
        List<Labels> allLabels = labelsService.getAllLabels();
        model.addAttribute("artLab",artLabels);
        model.addAttribute("sort",allSorts);
        model.addAttribute("label",allLabels);
        model.addAttribute("article",art);
        model.addAttribute("artSort",artSort);
        System.out.println(art.getArticleMdContent());
        return "admin/article/article_edit";
    }

    /*
     * @Description //修改文章信息
     * @Param [article]
     * @return java.lang.String
     **/
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    @ResponseBody
    public String uptArt(@RequestParam(name="articleId",required = true) String aId
            ,@RequestParam(name = "articleTitle" , required = true) String articleTitle
            ,@RequestParam(name = "articleMdContent" , required = true)  String articleMdContent
            ,@RequestParam(value = "labelIds[]") String[] labelIds
            ,@RequestParam(name = "sortId" )  Integer sortId){
        int articleId = Integer.parseInt(aId);
        Article article = new Article();
        article.setArticleId((long)articleId);
        article.setArticleMdContent(articleMdContent);
        article.setArticleTitle(articleTitle);
//        System.out.println(article);
//        for(int i = 0 ; i<labelIds.length;i++){
//            if(StringUtil.isNotEmpty(labelIds[i])){
//                System.out.println(labelIds[i]);
//            }else {
//                System.out.println("labelIds[i]");
//            }
//        }
//        System.out.println(sortId);
        int result = 0;
        //更新文章
        result =  articleService.updateArticle(article);
        if(result==0){
            return "failure";
        }
        //更新文章类型和标签
        if(labelIds.length>0&&labelIds!=null){
            result = articleService.updateArticleLabel(articleId,labelIds);
            if(result==0){
                return "failure";
            }
        }
        if(sortId!=null){
            result = articleService.updateArticleSort(articleId,sortId);
        }
        System.out.println(result);
            if(result>0){
                return "success";
            }
        return "failure";
    }
    /*
     * @Description //修改评论状态
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/uptComment")
    @ResponseBody
    public String updateCommentStatus(String articleId ,Integer articleAllowComment ){
        if(StringUtil.isNotEmpty(articleId)&&articleAllowComment!=null) {
            Article article = new Article();
            article.setArticleId((long) Integer.parseInt(articleId));
            article.setArticleAllowComment(articleAllowComment);
            int i = articleService.updateArticle(article);
            if (i > 0) {
                return "success";
            }
        }
        return "failure";
    }
    @RequestMapping("/uptStatus")
    @ResponseBody
    public String updateActiveStatus(String articleId ,Integer articleStatus){
        if(StringUtil.isNotEmpty(articleId)&&articleStatus!=null) {
            Article article = new Article();
            article.setArticleId((long) Integer.parseInt(articleId));
            article.setArticleStatus(articleStatus);
            int i = articleService.updateArticle(article);
            if (i > 0) {
                return "success";
            }
        }
        return "failure";
    }
}
