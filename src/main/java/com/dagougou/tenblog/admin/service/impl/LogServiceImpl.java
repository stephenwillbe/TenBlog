package com.dagougou.tenblog.admin.service.impl;

import com.dagougou.tenblog.admin.dao.LogMapper;
import com.dagougou.tenblog.admin.entity.Log;
import com.dagougou.tenblog.admin.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 日志服务实现
 * @Author stephen
 * @Date 2020/3/26
 **/
@Service
public class LogServiceImpl implements LogService {
    @Resource
    private LogMapper logMapper;

    @Override
    public List<Log> getLogsRecent() {
        List<Log> logs = logMapper.selectByDateLimit(10);
        return logs;
    }
}
