package com.dagougou.tenblog.admin.service;

import com.dagougou.tenblog.admin.entity.Labels;

import java.util.List;
import java.util.Map;

/**
 * @Description: 标签服务
 * @Author stephen
 * @Date 2020/3/14
 **/
public interface LabelsService {

    //获得layui穿梭框数据格式 value:id , title:name , disabled:..
    List<Map<String,Object>> getLabels();

    List<Labels> getAllLabels();

    int insertLabel(String labelName);

    //删除标签
    int deleteLabels(Integer[] ids);

    List<Labels> getLabelsByArticleId(Integer articleId);
}
