package com.dagougou.tenblog.admin.service.impl;

import com.dagougou.tenblog.admin.dao.UserMapper;
import com.dagougou.tenblog.admin.entity.User;
import com.dagougou.tenblog.admin.service.UserService;
import com.dagougou.tenblog.admin.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: TODO
 * @Author stephen
 * @Date 2020/3/9
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /*
     * @Description //根据用户名和密码获取用户信息
     * @Param [userName, userPass]
     * @return com.dagougou.tenblog.admin.entity.User
     **/
    @Override
    public User getUserByInfo(String userName) {
        User user = null;
        if(StringUtil.isNotEmpty(userName)){
            user = userMapper.selectByInfo(userName);
        }
        return user;
    }

    /*
     * @Description //获得所有的用户信息
     * @Param []
     * @return java.util.List<com.dagougou.tenblog.admin.entity.User>
     **/
    @Override
    public List<User> getAllUsers() {
        List<User> all = userMapper.findAll();
        return all;
    }
    /*
     * @Description //修改用户状态
     * @Param [userId, userStatus]
     * @return int
     **/
    @Transactional
    @Override
    public int uptStatus(String userId, boolean userStatus) {
        if(StringUtil.isNotEmpty(userId)){
            long id = Long.parseLong(userId);
            User user = new User();
            user.setUserId(id);
            user.setUserStatus(!userStatus);
            int i = userMapper.updateByPrimaryKeySelective(user);
            return i;
        }
        return 0;
    }
    /*
     * @Description //删除用户
     * @Param [userId]
     * @return int
     **/
    @Transactional
    @Override
    public int deleteUser(String userId) {
        if(StringUtil.isNotEmpty(userId)){
            long id = Long.parseLong(userId);
            int i = userMapper.deleteByPrimaryKey(id);
            return i;
        }
        return 0;
    }
    /*
     * @Description //根据id查询用户
     * @Param [id]
     * @return com.dagougou.tenblog.admin.entity.User
     **/
    @Override
    public User getUserById(long id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    /*
     * @Description //更新用户信息
     * @Param [user]
     * @return int
     **/
    @Override
    public int uptUserBySelect(User user) {
        int i = userMapper.updateByPrimaryKeySelective(user);
        return i;
    }

    /*
     * @Description //添加用户
     * @Param [record]
     * @return int
     **/
    @Override
    public int addUserBySelect(User record) {
        int i = userMapper.insertSelective(record);
        return i;
    }
}
