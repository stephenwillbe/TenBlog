package com.dagougou.tenblog.admin.service.impl;

import com.dagougou.tenblog.admin.dao.LabelsMapper;
import com.dagougou.tenblog.admin.entity.Labels;
import com.dagougou.tenblog.admin.entity.Sorts;
import com.dagougou.tenblog.admin.service.LabelsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 标签服务
 * @Author stephen
 * @Date 2020/3/14
 **/
@Service
public class LabelsServiceImpl implements LabelsService {

    @Resource
    private LabelsMapper labelsMapper;

    //获得layui穿梭框数据格式 value:id , title:name , disabled:..
    @Override
    public List<Map<String,Object>> getLabels(){
        List<Labels> labels = labelsMapper.findAllLabels();
        List<Map<String,Object>> list = new ArrayList<>();
        for (Labels label : labels){
            Map<String,Object> map = new HashMap<>();
            map.put("value",label.getLabelId());
            map.put("title",label.getLabelName());
            if(label.getDisabled().equals(1)){
                map.put("disabled",true);
            }
            list.add(map);
        }
        return list;
    }

    @Override
    public List<Labels> getAllLabels() {
        List<Labels> allLabels = labelsMapper.findAllLabels();
        return allLabels;
    }

    //新增标签
    @Override
    public int insertLabel(String labelName) {
        Labels labels = new Labels();
        labels.setLabelName(labelName);
        labels.setDisabled(0);
        int i = labelsMapper.insertSelective(labels);
        return i;
    }

    //删除标签
    @Transactional
    @Override
    public int deleteLabels(Integer[] ids){
        int result = 0;
        for(int i = 0 ;i<ids.length;i++){
            result = labelsMapper.deleteByPrimaryKey((long) ids[i]);
            if (result<0){
                return 0;
            }
        }
        return result;
    }
    //根据文章的id获得文章的所有标签
    @Override
    public List<Labels> getLabelsByArticleId(Integer articleId){
        List<Labels> labByArtId = labelsMapper.getLabByArtId((long) articleId);
        return labByArtId;
    }


}
