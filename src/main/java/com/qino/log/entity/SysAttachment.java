package com.qino.log.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 通用附件实体类
 */
@Data
@TableName("sys_attachment")
public class SysAttachment {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 关联目标类型（system_issue_log/sys_log）
     */
    private String targetType;

    /**
     * 关联目标ID
     */
    private String targetId;

    /**
     * 原始文件名
     */
    private String fileName;

    /**
     * 文件存储路径
     */
    private String filePath;

    /**
     * 文件大小（字节）
     */
    private Long fileSize;

    /**
     * 文件类型（MIME类型）
     */
    private String fileType;

    /**
     * 文件扩展名
     */
    private String fileExt;

    /**
     * 排序号
     */
    private Integer sortOrder;

    /**
     * 上传人
     */
    private String creator;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 删除标记（0未删除 1已删除）
     */
    @TableLogic
    private Integer deleted;
}
