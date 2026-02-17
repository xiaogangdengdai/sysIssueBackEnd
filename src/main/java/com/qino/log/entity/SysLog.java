package com.qino.log.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 系统日志实体类
 */
@Data
@TableName("sys_log")
public class SysLog {
    
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 日志类型（INFO/WARN/ERROR等）
     */
    private String logType;
    
    /**
     * 日志标题
     */
    private String title;
    
    /**
     * 日志内容
     */
    private String content;
    
    /**
     * 操作人
     */
    private String operator;
    
    /**
     * 操作IP
     */
    private String ipAddress;
    
    /**
     * 请求方法
     */
    private String requestMethod;
    
    /**
     * 请求URL
     */
    private String requestUrl;
    
    /**
     * 请求参数
     */
    private String requestParams;
    
    /**
     * 响应结果
     */
    private String responseResult;
    
    /**
     * 执行时间（毫秒）
     */
    private Long executeTime;
    
    /**
     * 状态（0成功 1失败）
     */
    private Integer status;
    
    /**
     * 错误信息
     */
    private String errorMsg;
    
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    
    /**
     * 删除标记
     */
    @TableLogic
    private Integer deleted;
}
