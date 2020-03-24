package com.dagougou.tenblog.admin.service.impl;

import com.dagougou.tenblog.admin.dao.SortsMapper;
import com.dagougou.tenblog.admin.entity.Sorts;
import com.dagougou.tenblog.admin.service.SortsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 分类服务
 * @Author stephen
 * @Date 2020/3/12
 **/
@Service
public class SortsServiceImpl implements SortsService {

    @Resource
    private SortsMapper sortsMapper;

    @Override
    public Map<String,Object> getAllSortsTree(){
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> fa = new HashMap<>();
        fa.put("id",1);
        fa.put("name","博客分类");
        fa.put("des","");
        List<Sorts> sorts = sortsMapper.getSortsByParentId((long) 1);
        for (Sorts sort :sorts){
            Map<String,Object> map = new HashMap<>();
            map.put("id",sort.getSortId());
            map.put("name",sort.getSortName());
            map.put("des",sort.getSortDescription());
            if(sort.getSortState().equals(1)){
                map.put("children",getSortsNode(sort.getSortId()));
            }
            list.add(map);
        }
        fa.put("children",list);
        return fa;
    }

    @Override
    public List<Sorts> getArticleSorts() {
        List<Sorts> sortsByState = sortsMapper.getSortsByState();
        return sortsByState;
    }

    /*
     * @Description //根据分类信息新增分类
     * @Param [sortName, sortId]
     * @return int
     **/
    @Override
    public int insertBySorts(Sorts sorts) {
        int i = sortsMapper.insertSelective(sorts);
        return i;
    }

    //修改分类名称
    @Override
    public int uptSortsName(Sorts sorts) {
        int i = sortsMapper.updateByPrimaryKeySelective(sorts);
        return i;
    }

    //获得文章的类型
    @Override
    public Sorts getSortByArticleId(Integer articleId) {
        Sorts sortByArtId = sortsMapper.getSortByArtId((long)articleId);
        return sortByArtId;
    }

    //获得父类的所有子节点
    public List<Map<String,Object>> getSortsNode(Long parentId){
        List<Map<String,Object>> list = new ArrayList<>();
        List<Sorts> sorts = sortsMapper.getSortsByParentId(parentId);
        for(Sorts sort : sorts){
           Map<String,Object> map = new HashMap<>();
           map.put("id",sort.getSortId());
           map.put("name",sort.getSortName());
           map.put("des",sort.getSortDescription());
           if(sort.getSortState().equals(1)){
               map.put("children",getSortsNode(sort.getSortId()));
           }
           list.add(map);
        }
        return list;
    }
}
