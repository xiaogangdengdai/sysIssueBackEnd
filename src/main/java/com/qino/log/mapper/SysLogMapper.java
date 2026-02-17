package com.qino.log.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qino.log.common.LogQuery;
import com.qino.log.entity.SysLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统日志Mapper
 */
@Mapper
public interface SysLogMapper extends BaseMapper<SysLog> {
    
    /**
     * 分页查询日志
     */
    IPage<SysLog> selectLogPage(Page<SysLog> page, @Param("query") LogQuery query);
}
