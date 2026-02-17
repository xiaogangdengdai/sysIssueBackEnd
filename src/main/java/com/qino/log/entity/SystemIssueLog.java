package com.qino.log.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 系统问题日志实体类
 */
@Data
@TableName("system_issue_log")
public class SystemIssueLog {
    
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    
    /**
     * 问题描述
     */
    private String description;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 状态（1待处理 2处理中 3已解决）
     */
    private Integer status;
    
    /**
     * 创建人
     */
    private String creator;
    
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
}
