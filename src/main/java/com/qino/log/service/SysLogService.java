package com.qino.log.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qino.log.common.LogQuery;
import com.qino.log.entity.SysLog;

/**
 * 系统日志服务接口
 */
public interface SysLogService {
    
    /**
     * 分页查询日志
     */
    IPage<SysLog> page(LogQuery query);
    
    /**
     * 根据ID查询日志
     */
    SysLog getById(Long id);
    
    /**
     * 新增日志
     */
    boolean save(SysLog sysLog);
    
    /**
     * 更新日志
     */
    boolean updateById(SysLog sysLog);
    
    /**
     * 删除日志
     */
    boolean removeById(Long id);
    
    /**
     * 批量删除日志
     */
    boolean removeByIds(java.util.List<Long> ids);
}
