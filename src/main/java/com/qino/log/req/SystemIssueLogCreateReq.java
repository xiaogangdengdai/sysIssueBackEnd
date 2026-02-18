package com.qino.log.req;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 系统问题日志创建请求
 *
 * @author wangzhihao
 */
@Data
public class SystemIssueLogCreateReq {

    /**
     * 类型：1.bug修复 2.新功能开发 3.原有功能改造 4.页面原型快速实现
     */
    @NotNull(message = "类型不能为空")
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
    @NotNull(message = "状态不能为空")
    private Integer status;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建人IP地址
     */
    private String creatorIp;
}
