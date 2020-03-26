package com.dagougou.tenblog;

import com.alibaba.fastjson.JSON;
import com.dagougou.tenblog.admin.dao.*;
import com.dagougou.tenblog.admin.entity.Article;
import com.dagougou.tenblog.admin.entity.Comments;
import com.dagougou.tenblog.admin.entity.Labels;
import com.dagougou.tenblog.admin.entity.Log;
import com.dagougou.tenblog.admin.service.LabelsService;
import com.dagougou.tenblog.admin.service.SortsService;
import com.dagougou.tenblog.admin.service.UserService;
import com.dagougou.tenblog.admin.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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

    @Resource
    private LogMapper logMapper;

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
    @Test
    void test10(){
        List<Comments> comments = commentsMapper.getCommentByContent("学习");
        for(Comments c : comments){
            System.out.println(c);
        }
    }
    @Test
    void test11(){
        Date startTime = DateUtil.StrToDate("2020-03-23", "yyyy-MM-dd");
        Date endTime = DateUtil.StrToDate("2020-03-25", "yyyy-MM-dd");
        List<Comments> comments = commentsMapper.getCommentByDate(startTime,endTime);
        for(Comments c : comments){
            System.out.println(c);
        }
    }
    @Test
    void test12(){
        List<Long> ids = new ArrayList<>();
        ids.add((long)668);
        ids.add((long)669);
        System.out.println(commentsMapper.deleteByIdList(ids));
    }
    @Test
    void test13(){
        List<Log> logs = logMapper.selectByDateLimit(2);
        for(Log l : logs){
            System.out.println(l);
        }
    }
    @Test
    void test14(){
        List<Article> articles = articleMapper.selectArticleByDateLimit(2, (long) 1);
        for(Article article : articles){
            System.out.println(article);
        }
    }
    @Test
    void test15(){
        List<Comments> comments = commentsMapper.selectCommentsByDateLimit(1);
        for (Comments comment : comments){
            System.out.println(comment);
        }
    }

}
