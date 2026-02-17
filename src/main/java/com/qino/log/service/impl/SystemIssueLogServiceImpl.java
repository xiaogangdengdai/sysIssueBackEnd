package com.qino.log.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qino.log.common.IssueLogQuery;
import com.qino.log.entity.SystemIssueLog;
import com.qino.log.mapper.SystemIssueLogMapper;
import com.qino.log.service.SysAttachmentService;
import com.qino.log.service.SystemIssueLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SystemIssueLogServiceImpl implements SystemIssueLogService {

    private final SystemIssueLogMapper mapper;

    @Resource
    private SysAttachmentService attachmentService;
    
    @Override
    public IPage<SystemIssueLog> page(IssueLogQuery query) {
        Page<SystemIssueLog> page = new Page<>(query.getPageNum(), query.getPageSize());
        return mapper.selectIssueLogPage(page, query);
    }
    
    @Override
    public SystemIssueLog getById(String id) {
        return mapper.selectById(id);
    }
    
    @Override
    public boolean save(SystemIssueLog log) {
        return mapper.insert(log) > 0;
    }
    
    @Override
    public boolean updateById(SystemIssueLog log) {
        return mapper.updateById(log) > 0;
    }
    
    @Override
    public boolean removeById(String id) {
        // 级联删除附件
        attachmentService.deleteByTarget("system_issue_log", id);
        return mapper.deleteById(id) > 0;
    }
    
    @Override
    public boolean removeByIds(List<String> ids) {
        // 级联删除附件
        for (String id : ids) {
            attachmentService.deleteByTarget("system_issue_log", id);
        }
        return mapper.deleteBatchIds(ids) > 0;
    }
}
