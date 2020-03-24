package com.dagougou.tenblog.admin.service;

import com.dagougou.tenblog.admin.entity.User;

import java.util.List;

/**
 * @Description: 用户服务
 * @Author stephen
 * @Date 2020/3/9
 * @Version 1.0
 **/
public interface UserService {

    public User getUserByInfo(String userName);

    public List<User> getAllUsers();

    int uptStatus(String userId, boolean userStatus);

    int deleteUser(String userId);

    User getUserById(long id);

    int uptUserBySelect(User user);

    int addUserBySelect(User record);
}
