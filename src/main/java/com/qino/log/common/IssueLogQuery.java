package com.qino.log.common;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 问题日志查询条件
 */
@Data
public class IssueLogQuery {
    private Integer pageNum = 1;
    private Integer pageSize = 10;
    private String description;
    private String creator;
    private Integer status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
