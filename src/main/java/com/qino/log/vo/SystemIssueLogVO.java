package com.qino.log.vo;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 系统问题日志VO
 *
 * @author wangzhihao
 */
@Data
public class SystemIssueLogVO {

    private String id;

    /**
     * 类型：1.bug修复 2.新功能开发 3.原有功能改造 4.页面原型快速实现
     */
    private Integer type;

    /**
     * SQL建表语句
     */
    private String createTableSql;

    /**
     * 新需求描述
     */
    private String newRequirement;

    /**
     * 改造前功能描述
     */
    private String beforeTransformation;

    /**
     * 改造后的目标
     */
    private String transformation;

    /**
     * 业务介绍
     */
    private String businessContext;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态：1:待处理, 2:处理中, 3:已完成, 4:处理失败
     */
    private Integer status;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建人IP地址
     */
    private String creatorIp;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 修改人
     */
    private String modifyUser;

    /**
     * 修改人IP地址
     */
    private String modifyIp;

    /**
     * 修改时间
     */
    private LocalDateTime modifyAt;
}
