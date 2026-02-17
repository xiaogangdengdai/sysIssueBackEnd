package com.qino.log.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qino.log.common.IssueLogQuery;
import com.qino.log.entity.SystemIssueLog;

/**
 * 系统问题日志服务接口
 */
public interface SystemIssueLogService {
    
    IPage<SystemIssueLog> page(IssueLogQuery query);
    
    SystemIssueLog getById(String id);
    
    boolean save(SystemIssueLog log);
    
    boolean updateById(SystemIssueLog log);
    
    boolean removeById(String id);
    
    boolean removeByIds(java.util.List<String> ids);
}
