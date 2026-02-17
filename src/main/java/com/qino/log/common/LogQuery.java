package com.qino.log.common;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 日志查询条件
 */
@Data
public class LogQuery {
    private Integer pageNum = 1;
    private Integer pageSize = 10;
    private String logType;
    private String title;
    private String operator;
    private Integer status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
