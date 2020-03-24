package com.dagougou.tenblog.admin.dao;

import com.dagougou.tenblog.admin.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> findAll();

    User selectByInfo(String userName);

}