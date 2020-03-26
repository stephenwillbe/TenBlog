package com.dagougou.tenblog.admin.dao;

import com.dagougou.tenblog.admin.entity.Log;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LogMapper {
    int deleteByPrimaryKey(Long logId);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(Long logId);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);

    //获取最新的n条日记
    List<Log> selectByDateLimit(@Param("recordCount") Integer recordCount);
}