package com.dagougou.tenblog;

import com.alibaba.fastjson.JSON;
import com.dagougou.tenblog.admin.dao.*;
import com.dagougou.tenblog.admin.entity.Article;
import com.dagougou.tenblog.admin.entity.Comments;
import com.dagougou.tenblog.admin.entity.Labels;
import com.dagougou.tenblog.admin.service.LabelsService;
import com.dagougou.tenblog.admin.service.SortsService;
import com.dagougou.tenblog.admin.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SpringBootTest
class TenblogApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private SortsService sortsService;

    @Resource
    private LabelsMapper labelsMapper;

    @Resource
    private SortsMapper sortsMapper;

    @Resource
    private LabelsService labelsService;

    @Resource
    private CommentsMapper commentsMapper;

    @Test
    void contextLoads() {
        System.out.println(userMapper.selectByPrimaryKey((long)1));
    }

    @Test
    void test2(){
//        System.out.println(userService.getUserByInfo("chen"));
        System.out.println(userService.uptStatus("1",true));
    }
    @Test
    void test3(){
        System.out.println(articleMapper.selectByPrimaryKey((long)101));
    }

    @Test
    void test4(){
        List<Article> all = articleMapper.findAll();
        for(Article article : all){
            System.out.println(article);
        }
    }
    @Test
    void test5(){
        List<Labels> labByArtId = labelsMapper.getLabByArtId((long) 999);
        for(Labels labels : labByArtId){
            System.out.println(labels);
        }
    }

    @Test
    void test6(){
        System.out.println(sortsMapper.getSortByArtId((long)1000));
    }

    @Test
    void test7(){
        List<Map<String, Object>> la = labelsService.getLabels();
        for(Map<String,Object> map : la){
            System.out.println(map);
        }
    }

    @Test
    void test8(){
        Article article = new Article();
        article.setArticleId((long)999);
        article.setArticleStatus(0);
        System.out.println(articleMapper.updateByPrimaryKeySelective(article));
    }
    @Test
    void test9(){
        List<Comments> all = commentsMapper.findAll();
        for(Comments c: all){
            System.out.println(c);
        }
    }
}
