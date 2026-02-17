package com.qino.log.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qino.log.common.IssueLogQuery;
import com.qino.log.entity.SystemIssueLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统问题日志Mapper
 */
@Mapper
public interface SystemIssueLogMapper extends BaseMapper<SystemIssueLog> {
    
    /**
     * 分页查询问题日志
     */
    IPage<SystemIssueLog> selectIssueLogPage(Page<SystemIssueLog> page, @Param("query") IssueLogQuery query);
}
