package com.dagougou.tenblog.admin.service;

import com.dagougou.tenblog.admin.entity.Log;

import java.util.List;

/**
 * @Description: 日志服务
 * @Author stephen
 * @Date 2020/3/26
 **/
public interface LogService {

    //获取最近的日志信息
    List<Log> getLogsRecent();
}
