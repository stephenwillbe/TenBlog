package com.dagougou.tenblog.admin.dao;

import com.dagougou.tenblog.admin.entity.Labels;

import java.util.List;

public interface LabelsMapper {
    int deleteByPrimaryKey(Long labelId);

    int insert(Labels record);

    int insertSelective(Labels record);

    Labels selectByPrimaryKey(Long labelId);

    int updateByPrimaryKeySelective(Labels record);

    int updateByPrimaryKeyWithBLOBs(Labels record);

    int updateByPrimaryKey(Labels record);

    List<Labels> getLabByArtId(Long articleId);

    List<Labels> findAllLabels();
}