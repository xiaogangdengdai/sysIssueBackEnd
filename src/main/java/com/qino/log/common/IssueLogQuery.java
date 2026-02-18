package com.qino.log.common;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 问题日志查询条件
 *
 * @author wangzhihao
 */
@Data
public class IssueLogQuery {
    private Integer pageNum = 1;
    private Integer pageSize = 10;

    /**
     * 类型：1.bug修复 2.新功能开发 3.原有功能改造 4.页面原型快速实现
     */
    private Integer type;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;
}
