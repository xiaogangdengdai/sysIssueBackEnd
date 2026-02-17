package com.qino.log.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qino.log.common.LogQuery;
import com.qino.log.entity.SysLog;
import com.qino.log.mapper.SysLogMapper;
import com.qino.log.service.SysAttachmentService;
import com.qino.log.service.SysLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 系统日志服务实现类
 */
@Service
@RequiredArgsConstructor
public class SysLogServiceImpl implements SysLogService {

    private final SysLogMapper sysLogMapper;
    private final SysAttachmentService attachmentService;
    
    @Override
    public IPage<SysLog> page(LogQuery query) {
        Page<SysLog> page = new Page<>(query.getPageNum(), query.getPageSize());
        return sysLogMapper.selectLogPage(page, query);
    }
    
    @Override
    public SysLog getById(Long id) {
        return sysLogMapper.selectById(id);
    }
    
    @Override
    public boolean save(SysLog sysLog) {
        return sysLogMapper.insert(sysLog) > 0;
    }
    
    @Override
    public boolean updateById(SysLog sysLog) {
        return sysLogMapper.updateById(sysLog) > 0;
    }
    
    @Override
    public boolean removeById(Long id) {
        // 级联删除附件
        attachmentService.deleteByTarget("sys_log", id.toString());
        return sysLogMapper.deleteById(id) > 0;
    }
    
    @Override
    public boolean removeByIds(List<Long> ids) {
        // 级联删除附件
        for (Long id : ids) {
            attachmentService.deleteByTarget("sys_log", id.toString());
        }
        return sysLogMapper.deleteBatchIds(ids) > 0;
    }
}
